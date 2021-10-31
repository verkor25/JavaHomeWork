package com.pb.lozumirskij.hw5;

public class Book {

    private String title;
    private String author;
    private String year;

    public Book (String title, String author, String year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }
}
