package com.pb.lozumirskij.hw6;

public class Animal {

    private String food;
    private String location;

    public Animal(String food, String location) {
        this.food = food;
        this.location = location;
    }

    public String getFood() {
        return food;
    }

    public String getLocation() {
        return location;
    }

    public void makeNoise (){
        System.out.println("some animal make noise");
    }

    public void eat (){
        System.out.println("some animal is eat");
    }

    public void sleep (){
        System.out.println("some animal is sleep");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "food='" + food + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
