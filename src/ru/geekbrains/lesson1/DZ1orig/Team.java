package ru.geekbrains.lesson1.DZ1orig;

/**
 * Класс - комманда участников соревнований
 */
public class Team {

    private Participant[] participants;

    // здесь используется конструктор с переменным числом параметров
    public Team(Participant... participants) {
        // внутри метода переменное число параметров интерпретируется как массив
        this.participants = participants;
    }

    public Participant[] getParticipants() {
        return participants;
    }

    public void getResults() {
        for (Participant part : participants) {
            part.getResult();
        }

        // !!! НЕ ПРАВИЛЬНО !!!
//        for (Participant part : participants) {
//            if (part instanceof Cat) {
//
//            } else if (part instanceof Dog) {
//
//            };
//        }
    }
}
