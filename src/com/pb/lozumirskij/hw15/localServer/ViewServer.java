package com.pb.lozumirskij.hw15.localServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ViewServer {
    private JFrame frame = new JFrame("server start");
    private JTextArea dialogWindow = new JTextArea(10, 40);
    private JButton buttonStartServer = new JButton("start server");
    private JButton buttonStopServer = new JButton("stop server");
    private JPanel panelButtons = new JPanel();
    private final Server server;

    public ViewServer(Server server) {

        this.server = server;
    }

    /**
     * initial graphical interface for server
     */
    protected void initFrameServer() {
        dialogWindow.setEditable(false);
        dialogWindow.setLineWrap(true);
        frame.add(new JScrollPane(dialogWindow), BorderLayout.CENTER);
        panelButtons.add(buttonStartServer);
        panelButtons.add(buttonStopServer);
        frame.add(panelButtons, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                server.stopServer();
                System.exit(0);
            }
        });
        frame.setVisible(true);

        buttonStartServer.addActionListener(e -> {
            int port = getPortFromOptionPane();
            server.startServer(port);
        });
        buttonStopServer.addActionListener(e -> server.stopServer());
    }

    public void refreshDialogWindowServer(String serviceMessage) {

        dialogWindow.append(serviceMessage);
    }

    protected int getPortFromOptionPane() {
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
}