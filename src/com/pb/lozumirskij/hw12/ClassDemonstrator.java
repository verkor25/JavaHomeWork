package com.pb.lozumirskij.hw12;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassDemonstrator {

    public static void main(String[] args) throws ErrorPhoneNumber {

        PhoneNumber number1 = new PhoneNumber("23", "089", "1234567");
        PhoneNumber number2 = new PhoneNumber("38", "022", "3456789");
        List<PhoneNumber> numbers1 = new ArrayList<>();
        numbers1.add(number1);
        numbers1.add(number2);

        PhoneCustomer phoneAbonent1 = new PhoneCustomer("Bill Murray",
                LocalDate.of(1982, 10, 22),
                numbers1, "Uta");

        PhoneNumber number3 = new PhoneNumber("32", "098", "4567890");
        PhoneNumber number4 = new PhoneNumber("11", "064", "5678901");
        List<PhoneNumber> numbers2 = new ArrayList<>();
        numbers2.add(number3);
        numbers2.add(number4);

        PhoneCustomer phoneAbonent2 = new PhoneCustomer("David Dickson",
                LocalDate.of(1995, 06, 26),
                numbers2, "Florida");

        PhoneNumber number5 = new PhoneNumber("31", "022", "5678901");
        List<PhoneNumber> numbers3 = new ArrayList<>();
        numbers3.add(number5);

        PhoneCustomer phoneAbonent3 = new PhoneCustomer("Chester Rodney",
                LocalDate.of(1968, 02, 14),
                numbers3, "Texas");

        PhoneBook book = new PhoneBook();
        book.add(phoneAbonent1);
        book.add(phoneAbonent3);
        book.add(phoneAbonent2);

        System.out.println("3 customers in phonebook --------------------------------------------------------------------");
        System.out.println(book);

        System.out.println("add customer");
        PhoneCustomer phoneAbonent9 = new PhoneCustomer("Arnold Loder",
                LocalDate.of(1964, 9, 22),
                numbers3, "Oregon");
        book.add(phoneAbonent9);
        System.out.println(book);
        System.out.println("--------------------------------------------------------------------------------------------");

        System.out.println("search Jon Doe: " + book.searchByName("Jon Doe"));
        System.out.println("search Chester Rodney is " + book.searchByName("Chester Rodney") + "  element in book");
        System.out.println("deleted "+ book.deleteByName("Chester Rodney") + " element");
        System.out.println(book);
        book.get(1).setFullName("Steve White");
        book.get(1).setBorn(LocalDate.of(1978, 8, 11));
        System.out.println(book);
        System.out.println("changed and deleted abonents in phonebook --------------------------------------------------");

        book.add(phoneAbonent3);
        System.out.println(book);
        book.get(3).setFullName("Chester Rodney");
        book.get(3).setBorn(LocalDate.of(1995, 7, 16));

        PhoneNumber number6 = new PhoneNumber("12", "032", "6789012");
        List<PhoneNumber> numbers4 = new ArrayList<>();
        numbers4.add(number6);
        numbers4.add(number5);
        PhoneCustomer phoneAbonent10 = new PhoneCustomer("Bill Murray",
                LocalDate.of(1994, 10, 22),
                numbers4, "Texas");
        book.add(phoneAbonent10);
        System.out.println(book);

        System.out.println("Sorted book --------------------------------------------------------------------------------");
        book.sortNameAndBorn();
        System.out.println(book);

        System.out.println("Create JSON with abonents");
        book.createJSON("phoneBook.json");

        System.out.println("read JSON with abonents");
        System.out.println(book.readJSON("phoneBook.json"));
    }
}
