package ru.geekbrains.lesson3.DZ3orig;

import java.util.*;

public class WordCount {

    public static void main(String[] args) {
        String[] arr = {"привет", "пока", "hello", "ahoj", "привет", "hello", "ahoj", "привет",};

        Set strSet = new HashSet();
        for (String str : arr) {
            strSet.add(str);
        }
        System.out.println(strSet);

        Map<String, Integer> wordCount = new HashMap<>();
        for (String str : arr) {
            int cnt = 0;
            // Collections.frequency();
            for (String str1 : arr) {
                if (str.equals(str1)) {
                    cnt++;
                }
            }
            wordCount.put(str, cnt);
        }
        System.out.println(wordCount);

        wordCount = new HashMap<>();
        for (String str : arr) {
            wordCount.putIfAbsent(str, 0);
            // wordCount.computeIfPresent(str, (key, val) -> val + 1);
            int cnt = wordCount.get(str);
            wordCount.put(str, cnt+1);
        }
        System.out.println(wordCount);
    }
}
