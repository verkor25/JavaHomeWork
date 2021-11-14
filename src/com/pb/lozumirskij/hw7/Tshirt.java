package com.pb.lozumirskij.hw7;

public class Tshirt extends Clothes implements ManClothes, WomenClothes{

    public Tshirt(Size size, int cost, String color) {
        super(size, cost, color);
    }

    @Override
    public void dressMan() {
        System.out.println(this);
    }

    @Override
    public void dressWomen() {
        System.out.println(this);
    }
}
