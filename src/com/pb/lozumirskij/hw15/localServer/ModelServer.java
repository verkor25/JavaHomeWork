package com.pb.lozumirskij.hw15.localServer;

import com.pb.lozumirskij.hw15.transport.Connection;

import java.util.HashMap;
import java.util.Map;

public class ModelServer {

    private Map<String, Connection> allUsers = new HashMap<>();


    public Map<String, Connection> getAllUsers() {

        return allUsers;
    }

    public void addUser(String nameUser, Connection connection) {

        allUsers.put(nameUser, connection);
    }

    public void removeUser(String nameUser) {

        allUsers.remove(nameUser);
    }

}