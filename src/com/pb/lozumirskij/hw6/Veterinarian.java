package com.pb.lozumirskij.hw6;

public class Veterinarian {

    public void treatAnimal(Animal animal){
        String multiLines = "animal: " + animal.getClass().getSimpleName()
                + System.lineSeparator() + "food: " + animal.getFood()
                + System.lineSeparator() + "location: " + animal.getLocation();
        System.out.println(multiLines);
    }

}
