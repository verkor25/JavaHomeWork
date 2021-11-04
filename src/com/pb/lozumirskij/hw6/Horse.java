package com.pb.lozumirskij.hw6;

import java.util.Objects;

public class Horse extends Animal{
    String toRide;

    @Override
    public void makeNoise(){
        System.out.println("Horse make noise");
    }

    @Override
    public void eat(){
        System.out.println("Horse is eat");
    }

    @Override
    public String toString(){
        return "Horse{" +
                "[" + "toRide= '" + toRide + "'" + "] "
                + "[" + "food= '" + getFood()+ "'" + "] "
                + "[" + "location= '" + getLocation() + "'" + "]"
                +"}" ;
    }

    public Horse(String food, String location, String toRide) {
        super(food, location);
        this.toRide = toRide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Objects.equals(this.toRide, horse.toRide)
                && Objects.equals(this.getFood(), horse.getFood())
                && Objects.equals(this.getLocation(), horse.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toRide, getFood(), getLocation());
    }
}
