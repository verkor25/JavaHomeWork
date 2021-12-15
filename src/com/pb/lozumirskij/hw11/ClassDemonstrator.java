package com.pb.lozumirskij.hw11;

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

        PhoneAbonent phoneAbonent1 = new PhoneAbonent("Bill Murray",
                LocalDate.of(1982, 10, 22),
                numbers1, "Uta");

        PhoneNumber number3 = new PhoneNumber("32", "098", "4567890");
        PhoneNumber number4 = new PhoneNumber("11", "064", "5678901");
        List<PhoneNumber> numbers2 = new ArrayList<>();
        numbers2.add(number3);
        numbers2.add(number4);

        PhoneAbonent phoneAbonent2 = new PhoneAbonent("David Dickson",
                LocalDate.of(1995, 06, 26),
                numbers2, "Florida");

        PhoneNumber number5 = new PhoneNumber("31", "022", "5678901");
        List<PhoneNumber> numbers3 = new ArrayList<>();
        numbers3.add(number5);

        PhoneAbonent phoneAbonent3 = new PhoneAbonent("Chester Rodney",
                LocalDate.of(1968, 02, 14),
                numbers3, "Texas");

        PhoneBook book = new PhoneBook();
        book.add(phoneAbonent1);
        book.add(phoneAbonent3);
        book.add(phoneAbonent2);

        System.out.println("3 abonents in phonebook --------------------------------------------------------------------");
        System.out.println(book);

        System.out.println("search Jon Doe: " + book.searchByName("Jon Doe"));
        System.out.println("search Chester Rodney is " + book.searchByName("Chester Rodney") + "  element in book");
        System.out.println("deleted "+ book.deleteByName("Bill Murray") + " element");

        PhoneNumber number6 = new PhoneNumber("12", "032", "6789012");
        List<PhoneNumber> numbers4 = new ArrayList<>();
        numbers4.add(number6);
        numbers4.add(number5);

        book.change(1, "Steve White", LocalDate.of(1978, 8, 11),
                "Colorado", numbers4);


        System.out.println("changed and deleted abonents in phonebook --------------------------------------------------");
        System.out.println(book);

        System.out.println("added abonents -----------------------------------------------------------------------------");
        book.add(phoneAbonent1);
        System.out.println(book);

        System.out.println("Sorted book");
        book.sortNameAndBorn();

        System.out.println("Create JSON with abonents");
        book.createJSON("phoneBook.json");

        System.out.println("read JSON with abonents");
        System.out.println(book.readJSON("phoneBook.json"));
    }
}
