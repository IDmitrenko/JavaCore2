package ru.geekbrains.lesson3;

public class MyPhoneBook {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addPhoneName("Петрович", "222-33-21");
        phoneBook.addPhoneName("Петров", "222-33-22");
        phoneBook.addPhoneName("Макаров", "222-33-23");
        phoneBook.addPhoneName("Петрович", "222-33-24");
        phoneBook.addPhoneName("Михайлов", "222-33-25");
        phoneBook.addPhoneName("Харламов", "222-33-26");
        phoneBook.addPhoneName("Михайлов", "222-33-27");
        phoneBook.addPhoneName("Петров", "222-33-28");
        phoneBook.addPhoneName("Петрович", "222-33-29");
        phoneBook.addPhoneName("Макаров", "222-33-22");


        System.out.println("Петрович - " + phoneBook.get("Петрович"));
        System.out.println("Макаров - " + phoneBook.get("Макаров"));
        System.out.println("Петров - " + phoneBook.get("Петров"));
        System.out.println("Харламов - " + phoneBook.get("Харламов"));
        System.out.println("Джексон - " + phoneBook.get("Джексон"));
        System.out.println("Михайлов - " + phoneBook.get("Михайлов"));

    }
}
