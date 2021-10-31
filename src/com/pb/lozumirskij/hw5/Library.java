package com.pb.lozumirskij.hw5;

public class Library {

    public void printAllBooks(Book [] books){
        for(Book book: books){
            System.out.println("[" + book.getAuthor() + "] "
                    + "[" + book.getTitle() + "] "
                    + "[" + book.getYear() + "]");
        }
    }

    public void printAllReaders(Reader [] readers){
        for (Reader reader: readers) {
            System.out.println("[" + reader.getFullName() + "] "
                    + "["+ reader.getCardNumber() + "] "
                    + "[" + reader.getFaculty() + "] "
                    + "[" + reader.getDateBirth() + "] "
                    + "[" + reader.getPhone() + "]");
        }
    }

    public static void main (String[] args){

        Book [] books = {new Book("Приключения", "Иванов И.И", "2020"),
                new Book("Словарь", "Петров П.П.", "2018"),
                new Book("Энциклопедия", "Сидоров С.С.", "2015")};

        Reader [] readers = {new Reader("Антонов А.А.", "3105", "психология", "12.11.1986", "055-200-30-40"),
                new Reader("Семенов С.С.", "5622", "зоология", "06.07.1993", "068-123-44-23"),
                new Reader("Беленов Б.Б.", "8264", "экология", "21.03.2001", "069-562-33-14")};

        Reader reader = new Reader("Кленов К.К.", "2332", "медицина", "2001", "052-369-25-14");

        Library library = new Library();
        System.out.println("print all readers");
        library.printAllReaders(readers);
        System.out.println("print all books");
        library.printAllBooks(books);

        System.out.println("demonstration methods takeBook");
        System.out.println(reader.takeBook(5));
        System.out.println(reader.takeBook("Приключения", "Энциклопедия", "Словарь"));
        System.out.println(reader.takeBook(books));

        System.out.println("demonstration methods returnBook");
        System.out.println(reader.returnBook(3));
        System.out.println(reader.returnBook("Энциклопедия", "Словарь", "Приключения"));
        System.out.println(reader.returnBook(books));
    }
}
