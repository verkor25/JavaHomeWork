package com.pb.lozumirskij.hw6;

import java.util.Objects;

public class Dog extends Animal{
    String hunt;

    public Dog(String food, String location, String hunt) {
        super(food, location);
        this.hunt = hunt;
    }

    @Override
    public void makeNoise(){
        System.out.println("Dog make noise");
    }

    @Override
    public void eat(){
        System.out.println("Dog is eat");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "[" + "hunt= '" + hunt + "'" + "] "
                + "[" + "food= '" + getFood()+ "'" + "] "
                + "[" + "location= '" + getLocation() + "'" + "]"
                +"}" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(this.hunt, dog.hunt)
                && Objects.equals(this.getFood(), dog.getFood())
                && Objects.equals(this.getLocation(), dog.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hunt, getFood(), getLocation());
    }
}
