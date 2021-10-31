package com.pb.lozumirskij.hw5;

public class Reader {

    private String fullName;
    private String cardNumber;
    private String faculty;
    private String dateBirth;
    private String phone;

    public Reader(String fullName, String cardNumber, String faculty, String dateBirth, String phone) {
        this.fullName = fullName;
        this.cardNumber = cardNumber;
        this.faculty = faculty;
        this.dateBirth = dateBirth;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public String getPhone() {
        return phone;
    }

    public String takeBook (int numberBook){
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append(" взял ").append(numberBook).append(" книг ");
        return builder.toString();
    }

    public String takeBook (String ... bookTitle){
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append(" взял книги: ");
        for (String s: bookTitle) {
            builder.append(s).append(", ");
        }
        return builder.delete(builder.length()-2, builder.length()).toString();
    }

    public String takeBook (Book ... books ){
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append(" взял книги: ");
        for (Book book: books) {
             builder.append(book.getTitle()).append(" (").append(book.getAuthor()).append(" ").append(book.getYear())
                     .append(")").append(", ");
        }
        return builder.delete(builder.length()-2, builder.length()).toString();
    }

    public String returnBook (int numberBook){
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append(" вернул ").append(numberBook).append(" книг ");
        return builder.toString();
    }

    public String returnBook (String ... bookTitle){
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append(" вернул книги: ");
        for (String s: bookTitle) {
            builder.append(s).append(", ");
        }
        return builder.delete(builder.length()-2, builder.length()).toString();
    }

    public String returnBook (Book ... books ){
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append(" вернул книги: ");
        for (Book book: books) {
            builder.append(book.getTitle()).append(" (").append(book.getAuthor()).append(" ").append(book.getYear())
                    .append(")").append(", ");
        }
        return builder.delete(builder.length()-2, builder.length()).toString();
    }
}
