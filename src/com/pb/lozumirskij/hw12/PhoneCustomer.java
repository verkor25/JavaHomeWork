package com.pb.lozumirskij.hw12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PhoneCustomer {

    private String fullName;
    private LocalDate born;
    private List<PhoneNumber> phoneNumbers;
    private String address;
    private LocalDateTime modification;

    public PhoneCustomer(String fullName, LocalDate born, List<PhoneNumber> phoneNumbers, String address) {
        this.fullName = fullName;
        this.born = born;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.modification = LocalDateTime.now();
    }

    public PhoneCustomer(String fullName, LocalDate born, List<PhoneNumber> phoneNumbers, String address, LocalDateTime modification) {
        this.fullName = fullName;
        this.born = born;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.modification = modification;
    }

    @Override
    public String toString() {
        return "PhoneCustomer{" +
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
        PhoneCustomer phoneCustomer = (PhoneCustomer) o;
        return Objects.equals(fullName, phoneCustomer.fullName) && Objects.equals(born, phoneCustomer.born) &&
                Objects.equals(phoneNumbers, phoneCustomer.phoneNumbers) && Objects.equals(address, phoneCustomer.address) &&
                Objects.equals(modification, phoneCustomer.modification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, born, phoneNumbers, address, modification);
    }


}


