package ru.geekbrains.lesson1.DZ1orig;

/**
 * Интерфейс - участник соревнований
 */
public interface Participant {

    boolean isOnDistance();

    void run(int distance);

    void jump(int height);

    void swim(int distance);

    void getResult();
}