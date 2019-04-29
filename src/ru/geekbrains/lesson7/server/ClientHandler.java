package ru.geekbrains.lesson7.server;

import ru.geekbrains.lesson7.client.TextMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static ru.geekbrains.lesson7.client.MessagePatterns.*;

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
                        String text = inp.readUTF();
                        System.out.printf("Message from user %s: %s%n", login, text);

                        // проверить является ли msg сообщением для пользователя
                        // если да, то переслать это сообщение пользователю
                        System.out.println("New message " + text);
                        TextMessage msg = parseTextMessageRegx(text, login);
                        if (msg != null) {
                            msg.swapUsers();
                            chatServer.sendMessage(msg);
                        } else if (text.equals(DISCONNECT)) {
                            System.out.printf("User %s is disconnected%n", login);
                            chatServer.unsubscribe(login);
                            return;
                        } else if (text.equals(LIST_USER)) {
                            System.out.printf("I got a request for a list of connected users from %s%n", login);
                            chatServer.sendListUsers();
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

    public void sendMessage(String userFrom, String msg) throws IOException {
        if (socket.isConnected()) {
            out.writeUTF(String.format(MESSAGE_SEND_PATTERN, userFrom, msg));
        }
    }

    public void sendConnectedMessage(String login) throws IOException {
        if (socket.isConnected()) {
            out.writeUTF(String.format(CONNECTED_SEND, login));
        }
    }

    public void sendDisconnectedMessage(String userFrom, String msg) throws IOException{
        if (socket.isConnected()) {
            out.writeUTF(String.format(DISCONNECTED_SEND_PATTERN, userFrom, msg));
        }
    }

    public void sendListUsers(String msg) throws IOException {
        if (socket.isConnected()) {
            out.writeUTF(String.format(LIST_SEND_PATTERN, msg));
        }
    }
}
