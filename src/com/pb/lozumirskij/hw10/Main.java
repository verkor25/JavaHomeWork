package com.pb.lozumirskij.hw10;

public class Main {

    public static void main(String[] args) {

        System.out.println("numBox1 ---------------------------------");
        NumBox <Integer> numBox1 = new NumBox<>(6);
        System.out.println("count elements before add: " + numBox1.length());
        try {
            numBox1.add(5);
            numBox1.add(56);
            numBox1.add(new Integer(78));
            numBox1.add(123);
            numBox1.add(17);
            numBox1.add(new Integer(22));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("count elements after add: " + numBox1.length());
        try {
            int needElement = 2;
            System.out.println(needElement + " element: " + numBox1.get(needElement));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("sum: " + numBox1.sum());
        System.out.println("average: " + numBox1.average());
        System.out.println("max element: " + numBox1.max());

        System.out.println("numBox2 ---------------------------------");

        NumBox <Float> numBox2 = new NumBox<>(5);
        try {
            numBox2.add(5.0f);
            numBox2.add(56.69f);
            numBox2.add(new Float(78.11));
            numBox2.add(123.54f);
            numBox2.add(17.36985f);
            numBox2.add(new Float(22.4));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("count elements: " + numBox2.length());
        try {
            int needElement = 7;
            System.out.println(needElement + " element: " + numBox2.get(needElement));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("sum: " + numBox2.sum());
        System.out.println("average: " + numBox2.average());
        System.out.println("max element: " + numBox2.max());


        System.out.println("numBox3 ---------------------------------");

        NumBox <Number> numBox3 = new NumBox<>(10);
        try {
            numBox3.add(5);
            numBox3.add(12.26);
            numBox3.add(new Float(78));
            numBox3.add(new Integer(123));
            numBox3.add((short) 56);
            numBox3.add(null);
            numBox3.add(new Double(123.6));
            numBox3.add(new Float(123.68));
            numBox3.add(new Short((short) 81));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("count elements: " + numBox3.length());
        try {
            int needElement = 8;
            System.out.println(needElement + " element: " + numBox3.get(needElement));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("sum: " + numBox3.sum());
        System.out.println("average: " + numBox3.average());
        System.out.println("max element: " + numBox3.max());
    }
}
