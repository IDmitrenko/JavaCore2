package ru.geekbrains.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 7777;

    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, PORT)) {
            // канал чтения из сокета
            DataInputStream in = new DataInputStream(socket.getInputStream());
            // канал записи в сокет
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String inputMessage;

            new Thread(new Runnable() {
                @Override
                public void run () {
                    try (Scanner scanner = new Scanner(System.in)) {
                        while (!Thread.currentThread().isInterrupted() && scanner.hasNextLine()) {
                            System.out.println("Введите сообщение > ");
                            String outputMessage = scanner.nextLine();
                            try {
                                out.writeUTF(outputMessage);
                                out.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }).start();

            while (!socket.isOutputShutdown()) {
                System.out.println("Введите сообщение > ");
                try {
                    while (true) {
                        inputMessage = in.readUTF();
                        System.out.println("Новое сообщение > " + inputMessage);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}