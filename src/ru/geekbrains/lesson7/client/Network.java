package ru.geekbrains.lesson7.client;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static ru.geekbrains.lesson7.client.MessagePatterns.*;


public class Network implements Closeable {

    public Socket socket;
    public DataInputStream in;
    public DataOutputStream out;

    private String hostName;
    private int port;
    private MessageReciever messageReciever;

    private String login;

    private Thread receiverThread;

    public Network(String hostName, int port, MessageReciever messageReciever) {
        this.hostName = hostName;
        this.port = port;
        this.messageReciever = messageReciever;

        this.receiverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        String text = in.readUTF();

                        System.out.println("New message " + text);
                        TextMessage msg = parseTextMessageRegEx(text, login);
                        if (msg != null) {
                            messageReciever.submitMessage(msg);
                            continue;
                        }

                        String userList = parseUserList(text);
                        if (userList != null) {
                            System.out.println("List of connected users " + text);
                            messageReciever.submitUserList(userList);
                            continue;
                        }

                        msg = parseConnectMessageRegEx(text, login);
                        String nick = parseConnectedMessage(text);
                        if (msg != null) {
                            System.out.println("Connection message " + text);
                            messageReciever.submitMessage(msg);
                            if (nick != null) {
//                                messageReciever.userConnected(nick);
                                requestConnectedUserList();
                            }
                            continue;
                        }

                        // добавить обработку отключения пользователя
                        msg = parseDisconnectMessageRegEx(text, login);
                        nick = parseDisconnectedMessage(text);
                        if (msg != null) {
                            messageReciever.submitMessage(msg);
                            if (nick != null) {
                                messageReciever.userDisconnected(nick);
                            }
                            continue;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        if (socket.isClosed()) {
                            break;
                        }
                    }
                }
            }
        });
    }

    public void authorize(String login, String password) throws IOException, AuthException {
        socket = new Socket(hostName, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        sendMessage(String.format(AUTH_PATTERN, login, password));
        String response = in.readUTF();
        if (response.equals(AUTH_SUCCESS_RESPONSE)) {
            this.login = login;
            receiverThread.start();
        } else if (response.equals(AUTH_ALREADY_RESPONSE)) {
            TextMessage msg = new TextMessage(login, login, "The user is already connected");
            messageReciever.submitMessage(msg);
        } else {
            throw new AuthException();
        }
    }

    public void sendTextMessage(TextMessage message) {

        sendMessage(String.format(MESSAGE_SEND_PATTERN, message.getUserTo(), message.getText()));
    }

    private void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogin() {
        return login;
    }

    public void requestConnectedUserList() {
        // реализовать запрос с сервера списка всех подключенных пользователей
        sendMessage(LIST_USER);

    }

    @Override
    public void close() {
        this.receiverThread.interrupt();
        sendMessage(DISCONNECT);
    }
}