package ru.geekbrains.lesson1;

import ru.geekbrains.lesson1.animal.Cat;
import ru.geekbrains.lesson1.animal.Dog;
import ru.geekbrains.lesson1.animal.Human;
import ru.geekbrains.lesson1.course.Course;
import ru.geekbrains.lesson1.course.Cross;
import ru.geekbrains.lesson1.course.Wall;
import ru.geekbrains.lesson1.course.Water;
import ru.geekbrains.lesson1.enums.Color;

/**
 * Класс для запуска приложения - симулятор кросса
 */
public class Application {

    public static void main(String[] args) {
        Team team = new Team("Крутые ребята",
                new Cat("Барсик", "Кот",Color.BLACK, 1, 100, 1),
                new Cat("Ксюша", "Кошка", Color.BLACK, 1, 80, 1),
                new Dog("Шарик", "Собака", Color.GRAY, 3, 500, 2, 30),
                new Dog("Матильда", "Собака", Color.WHITE, 15, 50, 1, 15),
                new Human("Игорь", "Человек", Color.WHITE, 25, 100, 2, 100),
                new Human("Лена", "Человек", Color.BLACK, 40, 60, 0, 10),
                new Robot("Электроник", 180, 3, 150 ),
                new Robot("R2-D2", 90, 1, 0)
        );

        Course course = new Course(
                new Cross(50),
                new Wall(1),
                new Cross(90),
                new Water(25)
        );

        course.doIt(team);

        team.getTeamMember();

        team.getGoDistance();
    }
}