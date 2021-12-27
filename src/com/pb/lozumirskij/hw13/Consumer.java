package com.pb.lozumirskij.hw13;

import java.util.List;

public class Consumer extends Thread {

    private final List lock;
    private final String color;
    private final int tradeVolume;

    public Consumer(List lock, String color, int tradeVolume) {
        this.lock = lock;
        this.color = color;
        this.tradeVolume = tradeVolume;
        this.setName("Consumer");
    }

    @Override
    public void run() {
        synchronized (lock) {
            int counter=0;
            while (lock.size() < 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int capacity = lock.size();
                for (int i = 0; i < capacity; i++) {
                    Integer element = (Integer) lock.get(0);
                    lock.remove(0);
                    System.out.println(color + "Consumer get product " + element + " from position " + i);
                    System.out.println("--Products in repository is: " + lock.size());
                    counter++;
                    /*try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
                lock.notify();
                if(counter == tradeVolume){System.exit(0);}
            }
        }
    }
}
