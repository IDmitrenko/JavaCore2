package ru.geekbrains.lesson1.animal;

import ru.geekbrains.lesson1.Participant;
import ru.geekbrains.lesson1.enums.Color;

public class Human extends Animal implements Participant {

    public Human(String name, String type, Color color, int age, int runDistance, int jumpHeight, int swimDistance) {
        super(name, type, color, age, runDistance, jumpHeight, swimDistance);
    }

    @Override
    public void voice() {
        System.out.println("Ура!");
    }
}