package ru.geekbrains.lesson5;

import java.util.Arrays;

public class StreamsInArrays {

    static final int SIZE = 10000000;
    static final int SIZE_PART = SIZE / 2;

    static void calcArr(float[] arr, int offset) {
        int x;
        for (int i = 0; i < arr.length; i++) {
            x = i + offset;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + x / 5) *
                    Math.cos(0.2f + x / 5) * Math.cos(0.4f + x / 2));
        }
    }

    static class CalcRun implements Runnable {

        private final float[] arr;
        private final int offset;

        public CalcRun(float[] arr, int offset) {
            this.arr = arr;
            this.offset = offset;
        }

        @Override
        public void run() {
            calcArr(arr, offset);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        long timeStart = System.currentTimeMillis();

        calcArr(arr, 0);

        System.out.printf("Посчитали новое значение для элементов массива за %d миллисекунд%n",
                System.currentTimeMillis() - timeStart);

        Arrays.fill(arr, 1.0f);

        float[] arr1 = new float[SIZE_PART];
        float[] arr2 = new float[SIZE_PART];

        timeStart = System.currentTimeMillis();
        //разбиваем на массивы
        System.arraycopy(arr, 0, arr1, 0, SIZE_PART);
        System.arraycopy(arr, SIZE_PART, arr2, 0, SIZE_PART);

        Thread thread1 = new Thread(new CalcRun(arr1, 0));
        thread1.start();

        Thread thread2 = new Thread(new CalcRun(arr2, SIZE_PART));
        thread2.start();

        thread1.join();
        thread2.join();

        //склеиваем массивы
        System.arraycopy(arr1, 0, arr, 0, SIZE_PART);
        System.arraycopy(arr2, 0, arr, SIZE_PART, SIZE_PART);

        System.out.printf("Посчитали новое значение для элементов массива в двух потоках за %d миллисекунд%n",
                System.currentTimeMillis() - timeStart);
    }

}