package ru.geekbrains.lesson1;

/**
 * Класс - комманда участников соревнований
 */
public class Team {

    private final Participant[] participants;
    private final String name;

    // здесь используется конструктор с переменным числом параметров
    public Team(String name, Participant... participants) {
        this.name = name;
        // внутри метода переменное число параметров интерпретируется как массив
        this.participants = participants;
    }

    public Participant[] getParticipants() {
        return participants;
    }

    public void getTeamMember() {
        System.out.printf(String.format("Список членов команды %s : %n", name));
        for (Participant participant : participants) {
            System.out.println(participant);
        }
    }

    public void getGoDistance() {
        System.out.printf(String.format("Список членов команды %s, прошедших дистанцию : %n", name));
        for (Participant participant : participants) {
            if (participant.isOnDistance()) {
                System.out.println(participant);
            }
        }
    }
}
