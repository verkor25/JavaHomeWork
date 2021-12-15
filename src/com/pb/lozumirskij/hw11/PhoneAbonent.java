package com.pb.lozumirskij.hw11;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PhoneAbonent implements Serializable {

    private final static long serialVersionUID = 22;

    private String fullName;
    private LocalDate born;
    private List<PhoneNumber> phoneNumbers;
    private String address;
    private LocalDateTime modification;

    public PhoneAbonent() {
    }

    public PhoneAbonent(String fullName, LocalDate born, List<PhoneNumber> phoneNumbers, String address) {
        this.fullName = fullName;
        this.born = born;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.modification = LocalDateTime.now();
    }

    public PhoneAbonent(String fullName, LocalDate born, List<PhoneNumber> phoneNumbers, String address, LocalDateTime modification) {
        this.fullName = fullName;
        this.born = born;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.modification = modification;
    }

    @Override
    public String toString() {
        return "PhoneAbonent{" +
                "fullName='" + fullName + '\'' +
                ", born=" + born +
                ", phoneNumbers=" + phoneNumbers +
                ", address='" + address + '\'' +
                ", modification=" + modification +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getModification() {
        return modification;
    }

    public void setModification(LocalDateTime modification) {
        this.modification = modification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneAbonent phoneAbonent = (PhoneAbonent) o;
        return Objects.equals(fullName, phoneAbonent.fullName) && Objects.equals(born, phoneAbonent.born) &&
                Objects.equals(phoneNumbers, phoneAbonent.phoneNumbers) && Objects.equals(address, phoneAbonent.address) &&
                Objects.equals(modification, phoneAbonent.modification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, born, phoneNumbers, address, modification);
    }


}


