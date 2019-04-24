package ru.geekbrains.lesson6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerP {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            while (true) {
                System.out.println("Сервер ожидает подключения!");
                Socket socket = serverSocket.accept();

                System.out.println("Подключился новый клиент!");
                SocketHandler socketHandler = new SocketHandler(socket);
                socketHandler.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
