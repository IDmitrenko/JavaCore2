package ru.geekbrains.lesson2;

import java.sql.SQLOutput;

public class Application {

    public static void main(String[] args) {

        String arr1[][] = {{"65", "8", "12", "4"},
                {"99", "24", "9", "5"},
                {"54", "16", "2", "14"},
                {"4", "1", "31", "52"}};

        String arr2[][] = {{"1", "2", "3", "4"},
                {"5", "6", "7", "8", "9"},
                {"10", "11", "12", "13"},
                {"14", "15"}};

        String arr3[][] = {{"5", "5", "3", "4"},
                {"8", "7", "57", "90"},
                {"12", "4", "12z", "5"},
                {"16", "16", "17", "26"}};

        try {
            System.out.println("Сумма значений массива arr1 = " + processingArray(arr1));
        } catch (MyArraySizeException | MyArrayDataException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Неизвестная ошибка : " + ex);
        }

        try {
            System.out.println("Сумма значений массива arr2 = " + processingArray(arr2));
        } catch (MyArraySizeException ex) {
            System.out.println("Ошибка : " + ex.getMessage());
        } catch (MyArrayDataException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            System.out.println("Неизвестная ошибка : " + ex);
        }

        try {
            System.out.println("Сумма значений массива arr3 = " + processingArray(arr3));
        } catch (MyArraySizeException | MyArrayDataException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Неизвестная ошибка : " + ex);
        }

    }


    private static int processingArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {

        final int SIZE = 4;

        if (arr.length != SIZE) {
            throw new MyArraySizeException(SIZE);
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != SIZE) {
                throw new MyArraySizeException(SIZE);
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(i, j, arr[i][j], ex);
                }
            }
        }

        return sum;
    }
}
