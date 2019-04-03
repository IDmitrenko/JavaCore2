package ru.geekbrains.lesson2;

public class MyArrayDataException extends Exception {

    private int row;
    private int col;
    private String value;

    public MyArrayDataException(int row, int col, String value) {
        this(row, col, value, null);
    }

    public MyArrayDataException(int row, int col, String value, Throwable cause) {
        super(String.format("Ячейка массива %d X %d содержит некорректное значение %s", row, col, value), cause);
        this.row = row;
        this.col = col;
        this.value = value;
    }

}
