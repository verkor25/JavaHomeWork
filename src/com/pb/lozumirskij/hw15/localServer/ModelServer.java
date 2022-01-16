package com.pb.lozumirskij.hw15.localServer;

import com.pb.lozumirskij.hw15.transport.Connection;

import java.util.HashMap;
import java.util.Map;

public class ModelServer {

    private Map<String, Connection> allUsers = new HashMap<>();


    protected Map<String, Connection> getAllUsers() {

        return allUsers;
    }

    protected void addUser(String nameUser, Connection connection) {

        allUsers.put(nameUser, connection);
    }

    protected void removeUser(String nameUser) {

        allUsers.remove(nameUser);
    }

}