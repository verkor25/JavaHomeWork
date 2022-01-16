package com.pb.lozumirskij.hw15.desktopClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

public class ViewClient {
    private final Client client;
    private JFrame frame = new JFrame("chat");
    private JTextArea messages = new JTextArea(30, 20);
    private JTextArea users = new JTextArea(30, 15);
    private JPanel panel = new JPanel();
    private JTextField textField = new JTextField(40);
    private JButton buttonDisable = new JButton("disconnect");
    private JButton buttonConnect = new JButton("connect");

    public ViewClient(Client client) {
        this.client = client;
    }

    /**
     * initial graphical interface for users
     */
    public void initFrameClient() {
        messages.setEditable(false);
        users.setEditable(false);
        frame.add(new JScrollPane(messages), BorderLayout.CENTER);
        frame.add(new JScrollPane(users), BorderLayout.EAST);
        panel.add(textField);
        panel.add(buttonConnect);
        panel.add(buttonDisable);
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (client.isConnect()) {
                    client.disableClient();
                }
                System.exit(0);
            }
        });
        frame.setVisible(true);
        buttonDisable.addActionListener(e -> client.disableClient());
        buttonConnect.addActionListener(e -> client.serverConnect());
        textField.addActionListener(e -> {
            client.sendMessage(textField.getText());
            textField.setText("");
        });
    }

    public void addMessage(String text) {

        messages.append(text);
    }

    public void refreshListUsers(Set<String> listUsers) {
        users.setText("");
        if (client.isConnect()) {
            StringBuilder text = new StringBuilder("user list:\n");
            for (String user : listUsers) {
                text.append(user + "\n");
            }
            users.append(text.toString());
        }
    }

    public String getServerAddressFromOptionPane() {
        while (true) {
            String addressServer = JOptionPane.showInputDialog(
                    frame, "enter server address",
                    "entering the server address",
                    JOptionPane.QUESTION_MESSAGE
            );
            return addressServer.trim();
        }
    }

    public int getPortServerFromOptionPane() {
        while (true) {
            String port = JOptionPane.showInputDialog(
                    frame, "enter server port:",
                    "entering the port of server",
                    JOptionPane.QUESTION_MESSAGE
            );
            try {
                return Integer.parseInt(port.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        frame, "port of server is incorrect, try again",
                        "ERROR! server port entering fault", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public String getNameUser() {
        String name =  JOptionPane.showInputDialog(
                frame, "enter user name:",
                "entering user name",
                JOptionPane.QUESTION_MESSAGE
        );
        frame.setTitle("chat  ---  "+name);
        return name;
    }

    public void errorDialogWindow(String text) {
        JOptionPane.showMessageDialog(
                frame, text,
                "ERROR", JOptionPane.ERROR_MESSAGE
        );
    }
}
