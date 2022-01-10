package com.pb.lozumirskij.hw14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServerMulti {
    static class Handler implements Runnable{
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String nameClient;

        public Handler(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }

        public void setNameClient(String nameClient) {
            this.nameClient = nameClient;}

        @Override
        public void run() {
            StringBuilder clientMessage;
            try {
                while (in != null) {
                    clientMessage = new StringBuilder(in.readLine());
                    out.println("Server: " + LocalDateTime.now() + " " + clientMessage);
                    System.out.println(nameClient + ": " + clientMessage);
                    if ("stop".equalsIgnoreCase(clientMessage.toString())) {
                        socket.close();
                        break;}
                }
            } catch (IOException e) {
                System.err.println("IO exception in handler");
                e.printStackTrace();
                System.exit(-1);
            }
            finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                    System.out.println(nameClient + " is disconnected");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int port  = 9100;
        int baseTreads = Thread.activeCount();

        try (ServerSocket serverSocket = new ServerSocket(port)){
            int count = 1;
            System.out.println("Start multi echo server");
            System.out.println("Wait connection");

            System.out.println("If you want stop server write \"exit\"");
            Thread stopServer = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                if("exit".equalsIgnoreCase(scanner.next())){
                    System.exit(0);
                }
            });
            stopServer.start();

            try{
                while (true) {
                    Socket socket = serverSocket.accept();
                    Handler client = new Handler(socket);
                    String nameClient = "Client"+count;
                    client.setNameClient(nameClient);

                    Thread thread = new Thread(client);
                    thread.start();

                    System.out.println(nameClient + " connected in address: " + socket.getRemoteSocketAddress());
                    System.out.println("wait message from " + nameClient);
                    count++;

                    if (Thread.activeCount() <= baseTreads) {break;}
                }

            } catch (IOException e) {
                System.err.println("IO exception in server");
                e.printStackTrace();
                System.exit(-1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
