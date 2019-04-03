package ru.geekbrains.lesson2;

public class MyArraySizeException extends Exception{

    private int size;

    public MyArraySizeException(int size) {
        this.size = size;
    }

    public MyArraySizeException(int size, Throwable cause) {
        this(size);
    }

    public String getMessage() {
        return  "Задан неверный размер массива! Ожидается размерность - " + size;
    }
}
