package com.pb.lozumirskij.hw3;

import java.util.Scanner;

public class Array {

    public static void main(String[] args) {

        int [] arr = new int[10];

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter 10 integer numbers:");

        for(int i = 0; i < arr.length; i++){
            arr[i] = scanner.nextInt();
        }

        System.out.println("print array");
        for (int i: arr) {
            System.out.print("[" + i + "]");
        }
        System.out.println("\n");

        int sum = 0;
        for (int i: arr) {
            sum+=i;
        }
        System.out.println("sum array: = " + sum);

        int counter = 0;
        for (int i: arr) {
            if (i >0) {
                counter++;
            }
        }
        System.out.println("count of positive elements in array: " + counter);

        boolean flag;

        do {
            int tempValue;
            flag = false;
            for(int i=0; i<arr.length-1; i++){
                if(arr[i] > arr[i+1]){
                    tempValue = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = tempValue;
                    flag = true;
                }
            }
        } while (flag);

        System.out.println("print sorted array");
        for (int i: arr) {
            System.out.print("[" + i + "]");
        }

    }
}
