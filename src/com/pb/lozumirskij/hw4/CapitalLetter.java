package com.pb.lozumirskij.hw4;

import java.util.Scanner;

public class CapitalLetter {

    public static String toUpperFirstSymbol(String s){
        StringBuilder builder = new StringBuilder();
        if (!s.equals(null)){
            char [] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if(i == 0 && !Character.isWhitespace(chars[i])){
                    builder.append(Character.toUpperCase(chars[i]));
                }
                else if (i > 0 && Character.isWhitespace(chars[i-1])){
                    builder.append(Character.toUpperCase(chars[i]));
                }
                else {
                    builder.append(chars[i]);
                }
            }
        }
        return builder.toString();
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter String");
        String userString = scanner.nextLine();

        System.out.println("result string is: " + toUpperFirstSymbol(userString));
    }
}
