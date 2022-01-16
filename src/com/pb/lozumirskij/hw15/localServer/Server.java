package com.pb.lozumirskij.hw15.localServer;

import com.pb.lozumirskij.hw15.transport.Connection;
import com.pb.lozumirskij.hw15.transport.Message;
import com.pb.lozumirskij.hw15.transport.MessageType;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Server {
    private ServerSocket serverSocket;
    private static ViewServer gui;
    private static ModelServer model;
    private static volatile boolean isServerStart = false;

    /**
     * server start
     * @param port server start port
     */
    protected void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            isServerStart = true;
            gui.refreshDialogWindowServer("server is running\n");
        } catch (Exception e) {
            gui.refreshDialogWindowServer("ERROR! server start is fault\n");
        }
    }

    /**
     * server stop
     */
    protected void stopServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                for (Map.Entry<String, Connection> user : model.getAllUsers().entrySet()) {
                    user.getValue().close();
                }
                serverSocket.close();
                model.getAllUsers().clear();
                gui.refreshDialogWindowServer("server is stop\n");
            } else gui.refreshDialogWindowServer("server not running\n");
        } catch (Exception e) {
            gui.refreshDialogWindowServer("ERROR! failed in stop server\n");
        }
    }

    /**
     * accepts new connection from users
     */
    protected void acceptServer() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            } catch (Exception e) {
                gui.refreshDialogWindowServer("lost connection to server\n");
                break;
            }
        }
    }

    /**
     * send message to all users
     * @param message
     */
    protected void sendMessageAllUsers(Message message) {
        for (Map.Entry<String, Connection> user : model.getAllUsers().entrySet()) {
            try {
                user.getValue().send(message);
            } catch (Exception e) {
                gui.refreshDialogWindowServer("Ошибка отправки сообщения всем пользователям!\n");
            }
        }
    }

    /**
     * start server client
     */
    public static void main(String[] args) {
        Server server = new Server();
        gui = new ViewServer(server);
        model = new ModelServer();
        gui.initFrameServer();
        while (true) {
            if (isServerStart) {
                server.acceptServer();
                isServerStart = false;
            }
        }
    }

    /**
     * launch new thread connection with users to server
     * server accepts a new socket connection
     */
    private class ServerThread extends Thread {
        private Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        /**
         * request name user and adding name to map
         * @param connection
         * @return
         */
        private String requestAndAddingUser(Connection connection) {
            while (true) {
                try {
                    connection.send(new Message(MessageType.REQUEST_NAME_USER));
                    Message responseMessage = connection.receive();
                    String userName = responseMessage.getTextMessage();
                    if (responseMessage.getTypeMessage() == MessageType.USER_NAME && userName != null
                            && !userName.isEmpty() && !model.getAllUsers().containsKey(userName)) {
                        model.addUser(userName, connection);
                        Set<String> listUsers = new HashSet<>();
                        for (Map.Entry<String, Connection> users : model.getAllUsers().entrySet()) {
                            listUsers.add(users.getKey());
                        }
                        connection.send(new Message(MessageType.NAME_ACCEPTED, listUsers));
                        sendMessageAllUsers(new Message(MessageType.USER_ADDED, userName));
                        return userName;
                    }
                    else connection.send(new Message(MessageType.NAME_USED));
                } catch (Exception e) {
                    gui.refreshDialogWindowServer("ERROR! this name is already in use\n");
                }
            }
        }

        /**
         * exchange messages between users
         * @param connection
         * @param userName
         */
        private void messagingBetweenUsers(Connection connection, String userName) {
            while (true) {
                try {
                    Message message = connection.receive();
                    if (message.getTypeMessage() == MessageType.TEXT_MESSAGE) {
                        String textMessage = String.format("%s: %s\n", userName, message.getTextMessage());
                        sendMessageAllUsers(new Message(MessageType.TEXT_MESSAGE, textMessage));
                    }
                    if (message.getTypeMessage() == MessageType.DISABLE_USER) {
                        sendMessageAllUsers(new Message(MessageType.REMOVED_USER, userName));
                        model.removeUser(userName);
                        connection.close();
                        gui.refreshDialogWindowServer(String.format("remote access user %s disconnected\n", socket.getRemoteSocketAddress()));
                        break;
                    }
                } catch (Exception e) {
                    gui.refreshDialogWindowServer(String.format("ERROR! fault in send message from user %s, or user disconnected\n", userName));
                    break;
                }
            }
        }

        /**
         * get connection using accepted socket from client
         * request a name, register, start a messaging cycle between users
         */
        @Override
        public void run() {
            gui.refreshDialogWindowServer(String.format("connect new user with socket address - %s.\n", socket.getRemoteSocketAddress()));
            String nameUser = null;
            try {
                Connection connection = new Connection(socket);
                nameUser = requestAndAddingUser(connection);
                messagingBetweenUsers(connection, nameUser);
            } catch (Exception e) {
                gui.refreshDialogWindowServer(String.format("ERROR! fault in send message from user %s\n", nameUser));
            }
        }
    }
}