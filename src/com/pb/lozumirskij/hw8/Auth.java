package com.pb.lozumirskij.hw8;

import java.util.regex.Pattern;

public class Auth {

    private String login;
    private String password;

    public void signUp (String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException {

        if (Pattern.matches("[a-zA-Z0-9]{5,20}",login)){
            this.login = login;
        }
        else {
            throw new WrongLoginException("login failed");
        }
        if (Pattern.matches("[a-zA-z0-9_]{5,}", password) && password.equals(confirmPassword)){
            this.password = password;
        }
        else {
            throw new WrongPasswordException("password failed");
        }
    }

    public void signIn (String login, String password) throws WrongLoginException {
        if(login.equals(this.login) && password.equals(this.password)){

            System.out.println("congrats you in system");
        }
        else{
            throw new WrongLoginException("login or password failed");
        }

    }
}
