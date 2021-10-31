package com.pb.lozumirskij.hw4;

import java.util.Arrays;
import java.util.Scanner;

public class Anagram {


    /** in method delete punctuation marks and with space and convert all symbols to low
     * @param s input string
     */
    public String toPreparedString (String s) {

        StringBuilder builder = new StringBuilder();
        char [] chars = s.toCharArray();

        for (char c : chars) {
            if(Character.isLetter(c)){
                builder.append(c);
            }
        }
        return  builder.toString().toLowerCase();
    }

    public boolean isAnagram(String s1, String s2){

        Anagram anagram = new Anagram();
        char [] chars1 = anagram.toPreparedString(s1).toCharArray();
        char [] chars2 = anagram.toPreparedString(s2).toCharArray();

        if( chars1.length != chars2.length){
            return false;
        } else {
            Arrays.sort(chars1);
            Arrays.sort(chars2);
            return Arrays.equals(chars1, chars2);
        }

    }
    public static void main(String[] args) {

        Anagram anagram = new Anagram();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter first string");
        String firstString = scanner.nextLine();
        System.out.println("enter second string");
        String secondString = scanner.nextLine();

        System.out.println("is strings is anagram? " + anagram.isAnagram(firstString, secondString));
    }


}
