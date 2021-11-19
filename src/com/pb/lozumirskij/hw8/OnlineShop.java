package com.pb.lozumirskij.hw8;

import java.util.Scanner;

public class OnlineShop {

    public static void main(String[] args) {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("you need sign up in system");
        System.out.println("sign up login:");
        String userLogin = scanner1.nextLine();
        System.out.println("sign up password:");
        String userPassword = scanner1.nextLine();
        System.out.println("return password:");
        String passwordConfirm = scanner1.nextLine();

        Auth auth = new Auth();
        try {
            auth.signUp(userLogin, userPassword, passwordConfirm);
        }
        catch (WrongLoginException | WrongPasswordException exception){
            exception.printStackTrace();
            System.exit(2);
        }


        System.out.println("-------------------------------");
        System.out.println("you need sign in system? input Y/N");
        if (scanner1.nextLine().equals("Y")){
            System.out.println("sign in login:");
            String userLogin2 = scanner1.nextLine();
            System.out.println("sign in password:");
            String userPassword2 = scanner1.nextLine();

            try{
                auth.signIn(userLogin2, userPassword2);
            }
            catch (WrongLoginException exception) {
                System.out.println("your ip was blocked");
                exception.printStackTrace();
            }
        }
        else {
            System.exit(1);
        }
    }
}
