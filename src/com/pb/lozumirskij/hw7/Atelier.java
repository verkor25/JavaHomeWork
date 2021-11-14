package com.pb.lozumirskij.hw7;

public class Atelier {

    public static void dressMan(Clothes[] clothes) {
        for (Clothes cl: clothes) {
            if (cl instanceof ManClothes) {
                ((ManClothes) cl).dressMan();
            }
        }
    }

    public static void dressWomen(Clothes[] clothes) {
        for(Clothes cl: clothes) {
            if (cl instanceof WomenClothes) {
                ((WomenClothes) cl).dressWomen();
            }
        }
    }

    public static void main(String[] args) {

        Clothes [] clothes = {new Tshirt(Clothes.Size.S, 25, "blue"),
                new Tshirt(Clothes.Size.M, 28, "white"),
                new Pants(Clothes.Size.XXS, 15, "red"),
                new Pants(Clothes.Size.L, 32, "green"),
                new Skirt(Clothes.Size.XS, 56, "pink"),
                new Skirt(Clothes.Size.M, 34, "grey"),
                new Tie(Clothes.Size.S, 63, "purple"),
                new Tie(Clothes.Size.XXS, 21, "red")};

        dressMan(clothes);

        System.out.println("------------------------------");

        dressWomen(clothes);



    }
}
