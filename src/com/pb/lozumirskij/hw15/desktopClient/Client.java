package com.pb.lozumirskij.hw15.desktopClient;

import com.pb.lozumirskij.hw15.transport.Connection;
import com.pb.lozumirskij.hw15.transport.Message;
import com.pb.lozumirskij.hw15.transport.MessageType;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Connection connection;
    private static ModelClient model;
    private static ViewClient gui;
    private volatile boolean isConnect = false;

    public boolean isConnect() {

        return isConnect;
    }

    public void setConnect(boolean connect) {

        isConnect = connect;
    }

    /**
     * start user client
     */
    public static void main(String[] args) {
        Client client = new Client();
        model = new ModelClient();
        gui = new ViewClient(client);
        gui.initFrameClient();
        while (true) {
            if (client.isConnect()) {
                client.userRegistration();
                client.receiveMessage();
                client.setConnect(false);
            }
        }
    }

    /***
     * connect user to server
     */
    protected void serverConnect() {
        if (!isConnect) {
            while (true) {
                try {
                    String addressServer = gui.getServerAddressFromOptionPane();
                    int port = gui.getPortServerFromOptionPane();
                    Socket socket = new Socket(addressServer, port);
                    connection = new Connection(socket);
                    isConnect = true;
                    gui.addMessage("service message: you connect to server\n");
                    break;
                } catch (Exception e) {
                    gui.errorDialogWindow("ERROR! invalid address server or port, try reconnect");
                    break;
                }
            }
        } else gui.errorDialogWindow("you connected yet");
    }

    /**
     * registration user name
     */
    protected void userRegistration() {
        while (true) {
            try {
                Message message = connection.receive();
                if (message.getTypeMessage() == MessageType.REQUEST_NAME_USER) {
                    String nameUser = gui.getNameUser();
                    connection.send(new Message(MessageType.USER_NAME, nameUser));
                }
                if (message.getTypeMessage() == MessageType.NAME_USED) {
                    gui.errorDialogWindow("this name is used, select other name\n");
                    String nameUser = gui.getNameUser();
                    connection.send(new Message(MessageType.USER_NAME, nameUser));
                }
                if (message.getTypeMessage() == MessageType.NAME_ACCEPTED) {
                    gui.addMessage("access name\n");
                    model.setUsers(message.getListUsers());
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                gui.errorDialogWindow("ERROR! name registration fault, try reconnect");
                try {
                    connection.close();
                    isConnect = false;
                    break;
                } catch (IOException ex) {
                    gui.errorDialogWindow("ERROR! connection close fault");
                }
            }

        }
    }

    /**
     * send message to another users
     * @param text is message from user to another users
     */
    protected void sendMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT_MESSAGE, text));
        } catch (Exception e) {
            gui.errorDialogWindow("ERROR! you message don't send");
        }
    }

    /**
     * receiving messages from the server and other users
     */
    protected void receiveMessage() {
        while (isConnect) {
            try {
                Message message = connection.receive();
                if (message.getTypeMessage() == MessageType.TEXT_MESSAGE) {
                    gui.addMessage(message.getTextMessage());
                }

                if (message.getTypeMessage() == MessageType.USER_ADDED) {
                    model.addUser(message.getTextMessage());
                    gui.refreshListUsers(model.getUsers());
                    gui.addMessage(String.format("service message: user %s connect to chat\n", message.getTextMessage()));
                }
                if (message.getTypeMessage() == MessageType.REMOVED_USER) {
                    model.removeUser(message.getTextMessage());
                    gui.refreshListUsers(model.getUsers());
                    gui.addMessage(String.format("service message: user %s left chat\n", message.getTextMessage()));
                }
            } catch (Exception e) {
                gui.errorDialogWindow("ERROR! message from server fault");
                setConnect(false);
                gui.refreshListUsers(model.getUsers());
                break;
            }
        }
    }

    /**
     * disconnect user from chat
     */
    protected void disableClient() {
        try {
            if (isConnect) {
                connection.send(new Message(MessageType.DISABLE_USER));
                model.getUsers().clear();
                isConnect = false;
                gui.refreshListUsers(model.getUsers());
            } else gui.errorDialogWindow("you disconnect yet\n");
        } catch (Exception e) {
            gui.errorDialogWindow("service message: disconnecting fault");
        }
    }
}
