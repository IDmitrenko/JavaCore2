package ru.geekbrains.lesson1;

/**
 * Робот не является животным, но может учавствовать в соревнованиях
 * так как реализует интерфейс {@link Participant}
 */
public class Robot implements Participant {

    private String name;
    private boolean isOnDistance;
    private int runDistance;
    private int jumpHeight;
    private int swimDistance;

    public Robot(String name, int runDistance, int jumpHeight, int swimDistance) {
        this.name = name;
        this.isOnDistance = true;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance = swimDistance;
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
            System.out.println(String.format("Робот %s не умеет бегать.", getName()));
        } else if (distance > runDistance) {
            isOnDistance = false;
            System.out.println(String.format("Робот %s не смог пробежать кросс длинной %d.", getName(), distance));
            return;
        } else {
            System.out.println(String.format("Робот %s пробежал кросс длинной %d.", getName(), distance));
        }
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance) {
            return;
        }
        if (jumpHeight == 0) {
            isOnDistance = false;
            System.out.println(String.format("Робот %s не умеет прыгать.", getName()));
        } else if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println(String.format("Робот %s не смог взять высоту %d.", getName(), height));
            return;
        } else {
            System.out.println(String.format("Робот %s прыгнул на высоту %d.", getName(), height));
        }
    }

    @Override
    public void swim(int distance) {
        if (!isOnDistance) {
            return;
        }
        if (swimDistance == 0) {
            isOnDistance = false;
            System.out.println(String.format("Робот %s не умеет плавать", getName()));
        } else if (distance > swimDistance) {
            isOnDistance = false;
            System.out.println(String.format("Робот %s не смог проплыть дистанцию %d", getName(), distance));
            return;
        } else {
            System.out.println(String.format("Робот %s проплыл дистанцию %d", getName(), distance));
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Робот " + name;
    }
}