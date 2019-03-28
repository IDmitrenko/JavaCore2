package ru.geekbrains.lesson1.animal;

import ru.geekbrains.lesson1.Participant;
import ru.geekbrains.lesson1.enums.Color;

public class Cat extends Animal implements Participant {

    public Cat(String name, String type, Color color, int age, int runDistance, int jumpHeight) {
        super(name, type, color, age, runDistance, jumpHeight, 0);
    }

    @Override
    public void voice() {
        System.out.println("Мяу");
    }
}
