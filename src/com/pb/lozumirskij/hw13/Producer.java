package com.pb.lozumirskij.hw13;

import java.util.List;
import java.util.Random;

public class Producer extends Thread {

    private final List lock;
    private final String color;
    private final int tradeVolume;

    public Producer(List lock, String color, int tradeVolume) {
        this.lock = lock;
        this.color = color;
        this.tradeVolume = tradeVolume;
        this.setName("Producer");
    }

    @Override
    public void run() {
        synchronized (lock) {
                for(int i = 1; i < tradeVolume+1; i++){
                    while (lock.size() == 5){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    /*try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    lock.add(new Random().nextInt(50));
                        System.out.println(color + "Producer add product " + lock.get(lock.size()-1) + " to position " + (lock.size()-1));
                        System.out.println("++Products in repository is: " + lock.size());
                        lock.notify();
                }
        }
    }
}
