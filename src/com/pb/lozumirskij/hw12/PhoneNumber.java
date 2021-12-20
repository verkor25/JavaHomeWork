package com.pb.lozumirskij.hw12;

import java.util.Objects;
import java.util.regex.Pattern;

public class PhoneNumber {

    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public PhoneNumber(String countryNumber, String operatorNumber, String userNumber) throws ErrorPhoneNumber {
        if(Pattern.matches("([0-9]{2})", countryNumber) &&
                Pattern.matches("([0-9]{3})", operatorNumber) &&
                Pattern.matches("([0-9]{7})", userNumber)) {
            number = new StringBuilder().append("+").append(countryNumber).append("(").append(operatorNumber).append(")").append(userNumber).toString();
        }
        else {
            throw  new ErrorPhoneNumber("invalid phone number");
        }

    }

    public void setNumber(String number) throws ErrorPhoneNumber {
        if(Pattern.matches("(([+,0-9]{3})([(]([0-9]{3})[)])([0-9]{7}))", "number")) {
            this.number = number;
        }
        else {
            throw new ErrorPhoneNumber("invalid phone number");
        }
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" + "number='" + number + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
