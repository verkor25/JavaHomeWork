package com.pb.lozumirskij.hw2;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        int operator1;
        int operator2;
        String operation;



        Scanner scanner = new Scanner(System.in);

        System.out.println("add first number");
        operator1 = scanner.nextInt();

        System.out.println("add second number");
        operator2 = scanner.nextInt();

        System.out.println("add operation");
        operation = scanner.next();

        switch (operation){
            case "+" :
                System.out.println(operator1 + operator2);
                break;
            case "-" :
                System.out.println(operator1 - operator2);
                break;
            case "/" :
                if(operator2 == 0) {
                    System.out.println("you can't divide by zero");
                }
                else {
                    System.out.println(operator1 / operator2);
                }
                break;
            case "*" :
                System.out.println(operator1 * operator2);
                break;
            default:
                System.out.println("error operation");
        }
    }
}
