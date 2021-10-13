package com.pb.lozumirskij.hw2;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        int operand1;
        int operand2;
        String sign;

        Scanner scanner = new Scanner(System.in);

        System.out.println("add first number");
        operand1 = scanner.nextInt();

        System.out.println("add second number");
        operand2 = scanner.nextInt();

        System.out.println("add arithmetic operation");
        sign = scanner.next();

        switch (sign){
            case "+" :
                System.out.println(operand1 + operand2);
                break;
            case "-" :
                System.out.println(operand1 - operand2);
                break;
            case "/" :
                if(operand2 == 0) {
                    System.out.println("error, you can't divide by zero, try again");
                }
                else {
                    System.out.println(operand1 / operand2);
                }
                break;
            case "*" :
                System.out.println(operand1 * operand2);
                break;
            default:
                System.out.println("error operation");
        }
    }
}
