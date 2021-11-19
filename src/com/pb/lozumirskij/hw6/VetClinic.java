package com.pb.lozumirskij.hw6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VetClinic {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Animal [] animals = {new Dog("meat", "courtyard", "duck"),
                            new Cat("fish", "house", "bed"),
                            new Horse("hay", "stable", "fast")};

        Class cl = Class.forName("com.pb.lozumirskij.hw6.Veterinarian");
        Object vet = cl.getConstructor().newInstance();
        Method treatAnimal = cl.getMethod("treatAnimal", Animal.class);

        for (Animal animal: animals) {
            //Veterinarian.class.newInstance().treatAnimal(animal);
            treatAnimal.invoke(vet, animal);

            /*if (o instanceof Veterinarian) {
                ((Veterinarian) o).treatAnimal(animal);
            }*/
            System.out.println("------------");
        }
    }
}
