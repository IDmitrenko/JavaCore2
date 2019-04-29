package ru.geekbrains.lesson7.server;


import ru.geekbrains.lesson7.client.AuthException;
import ru.geekbrains.lesson7.client.TextMessage;
import ru.geekbrains.lesson7.server.auth.AuthService;
import ru.geekbrains.lesson7.server.auth.AuthServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static ru.geekbrains.lesson7.client.MessagePatterns.AUTH_FAIL_RESPONSE;
import static ru.geekbrains.lesson7.client.MessagePatterns.AUTH_SUCCESS_RESPONSE;

public class ChatServer {

    private AuthService authService = new AuthServiceImpl();
    private Map<String, ClientHandler> clientHandlerMap = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start(7777);
    }

    private void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream inp = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("New client connected!");

                User user = null;
                try {
                    String authMessage = inp.readUTF();
                    user = checkAuthentication(authMessage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (AuthException ex) {
                    out.writeUTF(AUTH_FAIL_RESPONSE);
                    out.flush();
                    socket.close();
                }
                if (user != null && authService.authUser(user)) {
                    System.out.printf("User %s authorized successful!%n", user.getLogin());
                    subscribe(user.getLogin(), socket);
                    out.writeUTF(AUTH_SUCCESS_RESPONSE);
                    out.flush();
                } else {
                    if (user != null) {
                        System.out.printf("Wrong authorization for user %s%n", user.getLogin());
                    }
                    out.writeUTF(AUTH_FAIL_RESPONSE);
                    out.flush();
                    socket.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private User checkAuthentication(String authMessage) throws AuthException {
        String[] authParts = authMessage.split(" ");
        if (authParts.length != 3 || !authParts[0].equals("/auth")) {
            System.out.printf("Incorrect authorization message %s%n", authMessage);
            throw new AuthException();
        }
        return new User(authParts[1], authParts[2]);
    }

    private void sendUserConnectedMessage(String login) throws IOException {
        for (ClientHandler clientHandler : clientHandlerMap.values()) {
            if (!clientHandler.getLogin().equals(login)) {
                System.out.printf("Sending connect notification to %s about %s%n", clientHandler.getLogin(), login);
                clientHandler.sendConnectedMessage(login);
            }
        }
    }


    public void sendMessage(TextMessage msg) throws IOException{
        ClientHandler userToClientHandler = clientHandlerMap.get(msg.getUserTo());
        // убедиться, что userToClientHandler существует и отправить сообщение
        // для отправки сообщения нужно вызвать метод userToClientHandler.sendMessage()
        if (userToClientHandler != null) {
            userToClientHandler.sendMessage(msg.getUserFrom(), msg.getText());
        } else {
            System.out.printf("User %s not connected%n", msg.getUserTo());
        }
    }

    public void subscribe(String login, Socket socket) throws IOException {
        // Проверить, подключен ли уже пользователь. Если да, то отправить клиенту ошибку
        for (ClientHandler clientHandler : clientHandlerMap.values()) {
            if (clientHandler.getLogin().equals(login)) {
                System.out.printf("The user %s is already connected%n", login);
                return;
            }
        }
        clientHandlerMap.put(login, new ClientHandler(login, socket, this));
        sendUserConnectedMessage(login);
    }

    public void unsubscribe(String login) throws IOException {
        clientHandlerMap.remove(login);
        // 2 Отправить всем подключенным пользователям сообщение, что данный пользователь отключился
        sendUserDisconnectedMessage(login);
        // удалить пользователя из списка подключенных
        sendUserDisconnect(login);
    }

    private void sendUserDisconnectedMessage(String login) throws IOException {
        for (ClientHandler clientHandler : clientHandlerMap.values()) {
            System.out.printf("Sending disconnect notification to %s about %s%n", clientHandler.getLogin(), login);
            clientHandler.sendDisconnectedMessage(login);
        }
    }

    private void sendUserDisconnect(String login) throws IOException{
        for (ClientHandler clientHandler : clientHandlerMap.values()) {
            clientHandler.sendUserDisconnect(login);
        }
    }

    public void sendListUsers() throws IOException{
        Set<String> listUsers = clientHandlerMap.keySet();
        String msg = "";
        for (String user : listUsers) {
            msg += " " + user;
        }

        for (String user : listUsers) {
            ClientHandler userToClientHandler = clientHandlerMap.get(user);
            if (userToClientHandler != null) {
                userToClientHandler.sendListUsers(msg);
            }
        }
    }
}