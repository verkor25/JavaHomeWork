package com.pb.lozumirskij.hw11;

import java.util.Comparator;

public class PhoneAbonentCompareByBorn implements Comparator <PhoneAbonent> {

    @Override
    public int compare(PhoneAbonent o1, PhoneAbonent o2) {
        return o1.getBorn().compareTo(o2.getBorn());
    }
}
