package ru.geekbrains.lesson3.DZ3orig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {

    private Map<String, Set<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void addPhone(String name, String phone) {
        //Set<String> phones = phoneBook.computeIfAbsent(name, key -> new HashSet<>());
        Set<String> phones = phoneBook.get(name);
        if (phones == null) {
            phones = new HashSet<>();
            phoneBook.put(name, phones);
        }
        phones.add(phone);
    }

    public Set<String> getPhonesByName(String name) {
        return phoneBook.get(name);
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addPhone("Man", "123");
        phoneBook.addPhone("Man", "234");
        phoneBook.addPhone("Man1", "1235");
        phoneBook.addPhone("Man1", "2346");

        System.out.println(phoneBook.getPhonesByName("Man"));
        System.out.println(phoneBook.getPhonesByName("Man1"));
    }
}
