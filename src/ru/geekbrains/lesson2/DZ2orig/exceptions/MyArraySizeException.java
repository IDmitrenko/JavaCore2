package ru.geekbrains.lesson2.DZ2orig.exceptions;

public class MyArraySizeException extends Exception {

    public MyArraySizeException() {
        super("Некорректный размер массива");
    }
}
