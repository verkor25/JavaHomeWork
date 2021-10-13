package com.pb.lozumirskij.hw2;

import java.util.Scanner;

public class Interval {
    public static void main(String[] args) {

        int i;

        Scanner scanner = new Scanner(System.in);

        System.out.println("enter integer");
        i = scanner.nextInt();

        if(i >= 0 && i<= 14){
            System.out.println("your number in range [0 -14]");
        }else if(i >= 15 && i<= 35){
            System.out.println("your number in range [15 - 35]");
        }else if(i >= 36 && i<= 50){
            System.out.println("your number in range [36 - 50]");
        }else if(i >= 51 && i<= 100){
            System.out.println("your number in range [51 - 100]");
        }else{
            System.out.println("your number not included any ranges");
        }

    }
}
