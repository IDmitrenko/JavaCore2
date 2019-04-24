package ru.geekbrains.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler {

    private final Socket socket;

    private Thread sendThread;
    private Thread receiveThread;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {

        sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner sc = new Scanner(System.in);
                try (DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
                    do {
                        System.out.print("Введите сообщение > ");

                        while (!sc.hasNextLine()) ;

                        String msg = sc.nextLine();
                        if (socket.isClosed()) {
                            break;
                        }
                        outputStream.writeUTF(msg);
                    } while (!Thread.currentThread().isInterrupted());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {
                    while (true) {
                        String msg = inputStream.readUTF();
                        if (msg.equalsIgnoreCase("/end")) {
                            System.out.println("Пришел сигнал завершения");
                            sendThread.interrupt();
                            break;
                        }
                        System.out.printf("%nНовое сообщение > %s%n", msg);
                        System.out.print("Введите сообщение > ");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        sendThread.start();
        receiveThread.start();
    }

    public void join() {
        try {
            receiveThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}