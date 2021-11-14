package com.pb.lozumirskij.hw7;

public class Pants extends Clothes implements  ManClothes, WomenClothes{

    public Pants(Size size, int cost, String color) {
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
