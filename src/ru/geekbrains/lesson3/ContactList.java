package ru.geekbrains.lesson3;

import java.util.ArrayList;
import java.util.List;

public class ContactList {

    private String surname;
    private List<String>
            phoneNumber;

    public ContactList(String surname) {
        this.surname = surname;
        this.phoneNumber = new ArrayList<>();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void addPhoneNumber(String number) {
        phoneNumber.add(number);
    }
}
