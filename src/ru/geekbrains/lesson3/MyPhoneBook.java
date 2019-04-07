package ru.geekbrains.lesson3;

public class MyPhoneBook {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addPhoneNumber("Петрович", "222-33-21");
        phoneBook.addPhoneNumber("Петров", "222-33-22");
        phoneBook.addPhoneNumber("Макаров", "222-33-23");
        phoneBook.addPhoneNumber("Петрович", "222-33-24");
        phoneBook.addPhoneNumber("Михайлов", "222-33-25");
        phoneBook.addPhoneNumber("Харламов", "222-33-26");
        phoneBook.addPhoneNumber("Михайлов", "222-33-27");
        phoneBook.addPhoneNumber("Петров", "222-33-28");
        phoneBook.addPhoneNumber("Петрович", "222-33-29");
        phoneBook.addPhoneNumber("Макаров", "222-33-22");


        System.out.println("Петрович - " + phoneBook.get("Петрович"));
        System.out.println("Макаров - " + phoneBook.get("Макаров"));
        System.out.println("Петров - " + phoneBook.get("Петров"));
        System.out.println("Харламов - " + phoneBook.get("Харламов"));
        System.out.println("Михайлов - " + phoneBook.get("Михайлов"));

    }
}
