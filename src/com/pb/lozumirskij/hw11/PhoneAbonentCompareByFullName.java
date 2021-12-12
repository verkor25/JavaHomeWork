package com.pb.lozumirskij.hw11;

import java.util.Comparator;

public class PhoneAbonentCompareByFullName implements Comparator <PhoneAbonent> {
    @Override
    public int compare(PhoneAbonent o1, PhoneAbonent o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }
}
