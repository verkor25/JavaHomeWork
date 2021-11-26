package com.pb.lozumirskij.hw10;

public class NumBox <T extends Number>{

    private T[] arr;

    public NumBox(int sizeMassive) {
        this.arr = (T[]) new Number[sizeMassive];
    }

    public int length(){
        int length = 0;
        if (arr != null){
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] != null) {
                    length = i+1;
                }
            }
        }
        return length;
    }

    public void add(T num) throws Exception {
        int lengthMassive = length();
        int sizeMassive = arr.length;

        if(lengthMassive < sizeMassive && num != null){
            arr[lengthMassive] = num;
        }
        else {
            throw new Exception("array is full, can't add more " + sizeMassive + " elements");
        }
    }

    public T get(int index) throws Exception {
        if (arr.length >= index){
            return arr[index];
        }
        else {
            throw new Exception("array is less then " + index + " element");
        }
    }

    public double sum() {
        double sum = 0;
        int lengthMassive = length();
        if (lengthMassive > 0) {
            for (int i = 0; i < lengthMassive; i++) {
                sum += arr[i].doubleValue();
            }
        }
        return sum;
    }

    public double average(){
        double average = 0;
        if (length() > 0) {
            average = sum()/length();
        }
        return average;
    }

    public T max() {
        T max = null;
        if (length() > 0) {
            max = arr[0];
            int lengthMassive = length();
            for (int i = 0; i < lengthMassive; i++) {
                if((arr[i] instanceof Integer && arr[i].intValue() > max.intValue()) ||
                        (arr[i] instanceof Float && arr[i].floatValue() > max.floatValue()) ||
                        (arr[i] instanceof Double && arr[i].doubleValue() > max.doubleValue())) {
                    max = arr[i];
                }
            }
        }
        return max;
    }
}
