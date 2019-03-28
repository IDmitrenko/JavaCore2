package ru.geekbrains.lesson1.animal;

import ru.geekbrains.lesson1.Participant;
import ru.geekbrains.lesson1.enums.Color;

public abstract class Animal implements Participant {

    private String name;
    private String type;
    private Color color;
    private int age;
    private boolean isOnDistance;
    private int runDistance;
    private int jumpHeight;
    private int swimDistance;


    public Animal(String name, String type, Color color, int age, int runDistance, int jumpHeight, int swimDistance) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.age = age;
        this.isOnDistance = true;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance = swimDistance;
    }

    public abstract void voice();

    private String getName() {
        return name;
    }

    private String getType() {
        return type;
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
        if (runDistance == 0) {
            isOnDistance = false;
            System.out.println(String.format("%s %s не умеет бегать." , getType(), getName()));
        } else if (distance > runDistance) {
            isOnDistance = false;
            System.out.println(String.format("%s %s не смог(-ла) пробежать кросс длиной %d" , getType(), getName(), distance));
            return;
        } else {
            System.out.println(String.format("%s %s пробежал(-а) кросс длинной %d", getType(), getName(), distance));
        }
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance) {
            return;
        }
        if (jumpHeight == 0) {
            isOnDistance = false;
            System.out.println(String.format("%s %s не умеет прыгать." , getType(), getName()));
        } else if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println(String.format("%s %s не смог(-ла) взять высоту %d" , getType(), getName(), height));
            return;
        } else {
            System.out.println(String.format("%s %s прыгнул(-а) на высоту %d", getType(), getName(), height));
        }
    }

    @Override
    public void swim(int distance) {
        if (!isOnDistance) {
            return;
        }
        if (swimDistance == 0) {
            isOnDistance = false;
            System.out.println(String.format("%s %s не умеет плавать." , getType(), getName()));
        } else if (distance > swimDistance) {
            isOnDistance = false;
            System.out.println(String.format("%s %s не смог(-ла) проплыть дистанцию %d" , getType(), getName(), distance));
            return;
        } else {
            System.out.println(String.format("%s %s проплыл(-а) дистанцию %d", getType(), getName(), distance));
        }
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
