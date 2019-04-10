package ru.geekbrains.lesson1.DZ1orig.Animal;

import ru.geekbrains.lesson1.Participant;
import ru.geekbrains.lesson1.enums.Color;

public class Cat extends Animal implements Participant {

    private boolean isOnDistance;
    private int runDistance;
    private int jumpHeight;

    public Cat(String name, Color color, int age, int runDistance, int jumpHeight) {
        super(name, color, age);

        // так делать не желательно
        super.dummy = 1;

        this.isOnDistance = true;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }

    public Cat(String name, Color color) {
        super(name, color, 0);
    }

    @Override
    public void voice() {
        System.out.println("Мяу");
    }

    @Override
    public boolean isOnDistance() {
        return isOnDistance;
    }

    @Override
    public void run(int distance) {
        if (!isOnDistance) {
            return;
        }
        if (distance > runDistance) {
            isOnDistance = false;
            return;
        }
        System.out.println(String.format("Кошка %s пробежала кросс длинной %d", getName(), distance));
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance) {
            return;
        }
        if (height > jumpHeight) {
            isOnDistance = false;
            return;
        }
        System.out.println(String.format("Собака %s пругнула на высоту %d", getName(), height));
    }

    @Override
    public void swim(int distance) {
        isOnDistance = false;
        System.out.println("Кошка не умеет плавать");
        // throw new UnsupportedOperationException("Кошка не умеет плавать");
    }

    @Override
    public void getResult() {
        System.out.println(String.format("Кошка %s прошла%s полосу препятствий",
                getName(), isOnDistance ? "" : " не"));
    }

    @Override
    public String getName() {
        return "Кошка " + super.getName();
    }

    public void setRunDistance(int runDistance) {
        this.runDistance = runDistance;
    }
}