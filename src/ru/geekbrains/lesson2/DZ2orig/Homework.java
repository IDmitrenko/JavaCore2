package ru.geekbrains.lesson2.DZ2orig;

import ru.geekbrains.lesson2.exceptions.MyArrayDataException;
import ru.geekbrains.lesson2.exceptions.MyArraySizeException;

public class Homework {

    private final static int SIZE = 4;

    public static void main(String[] args) {
        String testArr1[][] = {
                {"10", "2", "3", "4"},
                {"-1", "21", "-31", "4"},
                {"1", "200", "3", "40"},
                {"1", "22", "-35", "4"}};

        String testArr2[][] = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}};

        String testArr3[][] = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3"},
                {"1", "2", "3", "4"}};

        String testArr4[][] = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "asdfasdf", "4"},
                {"1", "2", "3", "4"}};

        try {
            System.out.println("Сумма массива 1: " + calcArraySum(testArr1));
        } catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println(ex);
        }

        try {
            System.out.println("Сумма массива 2: " + calcArraySum(testArr2));
        } catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println(ex);
        }

        try {
            System.out.println("Сумма массива 3: " + calcArraySum(testArr3));
        } catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println(ex);
        }

        try {
            System.out.println("Сумма массива 4: " + calcArraySum(testArr4));
        } catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println(ex);
        }
    }

    public static int calcArraySum(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int res = 0;

        if (arr.length != SIZE) {
            throw new MyArraySizeException();
        }

        for (int i=0; i<arr.length; i++) {
            if (arr[i].length != SIZE) {
                throw new MyArraySizeException();
            }

            for (int j=0; j<arr[i].length; j++) {
                try {
                    res += Integer.valueOf(arr[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(i, j, arr[i][j], ex);
                }
            }
        }

        return res;
    }
}
