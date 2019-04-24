package ru.geekbrains.lesson6;

import java.io.IOException;
import java.net.Socket;

public class EchoClientP {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 7777)) {
            SocketHandler socketHandler = new SocketHandler(socket);
            socketHandler.start();
            socketHandler.join();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
