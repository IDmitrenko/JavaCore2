package ru.geekbrains.lesson7.client;

public interface MessageReciever {

    void submitMessage(TextMessage message);

    void userConnected(String login);

    void userDisconnected(String login);

    void submitUserList(String userList);
}
