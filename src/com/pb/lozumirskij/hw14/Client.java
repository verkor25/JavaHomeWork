package com.pb.lozumirskij.hw14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverIp = "localhost";
        int serverPort = 9100;

        System.out.println("Client started");
        System.out.println("Connect to server " + serverIp + ":" + serverPort);

        try (Socket socket = new Socket(serverIp, serverPort);
        BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){

            while (true) {
                StringBuilder userMessage = new StringBuilder(fromConsole.readLine());
                out.println(userMessage);
                StringBuilder serverMessage = new StringBuilder(in.readLine());
                System.out.println(serverMessage);

                if("stop".equalsIgnoreCase(userMessage.toString())){break;}
            }
        } catch (IOException e) {
            System.err.println("IO exception in client");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
