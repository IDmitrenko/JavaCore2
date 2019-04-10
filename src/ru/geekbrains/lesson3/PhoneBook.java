package ru.geekbrains.lesson3;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private Map<String, ContactList> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void addPhoneName(String surname, String phoneNumber) {
        ContactList contactlist;
        String key = surname.toUpperCase();
        if (phoneBook.containsKey(key)) {
            contactlist = phoneBook.get(key);
        } else {
            contactlist = new ContactList(surname);
            phoneBook.put(key, contactlist);
        }

        contactlist.addPhoneNumber(phoneNumber);
    }

    public List<String> get(String surname) {
        String key = surname.toUpperCase();

        if (phoneBook.containsKey(key)) {
            return phoneBook.get(key).getPhoneNumber();
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
