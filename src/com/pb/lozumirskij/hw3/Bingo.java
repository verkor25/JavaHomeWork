package com.pb.lozumirskij.hw3;

import java.util.Random;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Bingo {
    public static void main(String[] args) {

        int counter = 1;

        Random random = new Random();
        int target = random.nextInt(101);
        int userNumber;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("enter any positive integer from 0 to 100 or \"exit\"");
            String userResponse = scanner.next();
            if(userResponse.equals("exit")) {
                break;
            }
            else{
                userNumber = parseInt(userResponse);
            }

            if (userNumber > target) {
                System.out.println("you number is more");
            } else if (userNumber < target) {
                System.out.println("you number is less");
            } else {
                System.out.println("you guessed from " + counter + " try");
            }

            counter++;
            System.out.println("\n");
        } while (userNumber != target);

    }
}
