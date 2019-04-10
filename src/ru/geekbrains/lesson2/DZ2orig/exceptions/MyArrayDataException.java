package ru.geekbrains.lesson2.DZ2orig.exceptions;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int i, int j, String value, Throwable caused) {
        super(String.format("Некорректное значение %s в ячейка [%d][%d]", value, i, j), caused);
    }
}