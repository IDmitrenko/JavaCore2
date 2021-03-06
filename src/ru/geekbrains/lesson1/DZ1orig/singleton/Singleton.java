package ru.geekbrains.lesson1.DZ1orig.singleton;

public class Singleton {

    private static Singleton instance;

    private static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private Singleton() {

    }
}
