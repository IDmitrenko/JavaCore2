package ru.geekbrains.lesson2;

public class MyArraySizeException extends Exception{

    private int size;

    public MyArraySizeException(int size) {
        this(size, null);
    }

    public MyArraySizeException(int size, Throwable cause) {
        super("Задан неверный размер массива! Ожидается размерность - " + size);
        this.size = size;
    }

}
