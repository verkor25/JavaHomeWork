package com.pb.lozumirskij.hw13;

import java.util.ArrayList;
import java.util.List;

public class Trade {

    public static void main(String[] args) {
        final List lock = new ArrayList<Integer>();
        int tradeVolume = 12;
        Thread thread1 = new Producer(lock, ThreadColors.ANSI_ORANGE, tradeVolume);
        Thread thread2 = new Consumer(lock, ThreadColors.ANSI_BLUE, tradeVolume);
        thread2.start();
        thread1.start();
    }
}
