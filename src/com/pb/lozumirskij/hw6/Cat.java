package com.pb.lozumirskij.hw6;

import java.util.Objects;

public class Cat extends Animal{
    String hide;

    public Cat(String food, String location, String hide) {
        super(food, location);
        this.hide = hide;
    }

    @Override
    public void makeNoise(){
        System.out.println("Cat make noise");
    }

    @Override
    public void eat(){
        System.out.println("Cat is eat");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "[" + "hide= '" + hide + "'" + "] "
                + "[" + "food= '" + getFood()+ "'" + "] "
                + "[" + "location= '" + getLocation() + "'" + "]"
                +"}" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(this.hide, cat.hide)
                && Objects.equals(this.getFood(), cat.getFood())
                && Objects.equals(this.getLocation(), cat.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hide, getFood(), getLocation());
    }
}
