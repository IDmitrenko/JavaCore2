package ru.geekbrains.lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static ru.geekbrains.lesson7.client.MessagePatterns.MESSAGE_SEND_PATTERN;

/*
ClientHandler это класс, который ответственен за обработку одного из подключившихся клиентов

При создании этого класса мы передаем в него имя пользователя и сокет через который
осуществляется взаимодействие с клиентским приложением

Метод sendMessage в этом классе отправляет сообщение на клиент, данного обработчика

Алгоритм работы примерно такой
1. Клиент отправил сообщение.
2. Это сообщение считано в потоке соответствующего ClientHandler
3. Определяем из пришедшей команды, кто адресат сообщения
4. Вызываем метод sendMessage() сервера
5. Сервер находит ClientHandler адресата и вызывает его метод sendMessage чтобы отправить сообщение адресату
*/

public class ClientHandler {

    private final String login;
    private final Socket socket;
    private final DataInputStream inp;
    private final DataOutputStream out;
    private final Thread handleThread;
    private ChatServer chatServer;

    public ClientHandler(String login, Socket socket, ChatServer chatServer) throws IOException {
        this.login = login;
        this.socket = socket;
        this.inp = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        this.chatServer = chatServer;

        this.handleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        String msg = inp.readUTF();
                        System.out.printf("Message from user %s: %s%n", login, msg);

                        // проверить является ли msg сообщением для пользователя
                        // если да, то переслать это сообщение пользователю
                        String[] arr = msg.split(" ", 3);
                        if (arr[0].equals(MESSAGE_SEND_PATTERN.substring(0, 2))) {
                            String userTo = arr[1];
                            String message = arr[2];
                            if (!login.equals(userTo) && !message.trim().isEmpty()) {
                                sendMessage(login, message);
                                chatServer.sendMessage(userTo, login, message);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        this.chatServer = chatServer;
        this.handleThread.start();
    }

    public String getLogin() {
        return login;
    }

    public void sendMessage(String userTo, String msg) throws IOException {
        out.writeUTF(String.format(MESSAGE_SEND_PATTERN, userTo, msg));
    }
}
