package ru.geekbrains.lesson3;

import java.util.*;

public class UniqueWords {

    public static void main(String[] args) {

        String[] words = {"Спартак", "ЦСКА", "Зенит", "Локомотив", "Реал",
                        "Зенит", "ЦСКА", "Реал", "Зенит", "Ростов",
                        "Локомотив", "Фенербахче", "СКА", "Авангард", "ЦСКА"};

        List<String> wordsList = Arrays.asList(words); // формирование списка из массива
        // создаем хэш-таблицу для создания пары слово - количество повторений
        Map<String, Integer> listRepeats = new HashMap<>();

        for (String word : wordsList) {
            listRepeats.put(word, Collections.frequency(wordsList, word)); //количество повторений слова
        }

        for(Map.Entry<String, Integer> entry : listRepeats.entrySet()) {
            String key = entry.getKey();

            System.out.printf(key + " ");
        }
        System.out.println();

        System.out.println(listRepeats);

    }
}
