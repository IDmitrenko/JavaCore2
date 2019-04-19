package ru.geekbrains.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    private static final int PORT = 7777;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер ожидает подключения!");
            Socket socket = serverSocket.accept();
            System.out.println("Кто-то подключился: "+socket.getInetAddress());
            DataInputStream in = new DataInputStream(socket.getInputStream());
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

//            while(socket.isConnected()) {
            while(!socket.isClosed()) {
                System.out.println("Введите сообщение > ");
                try {
                    while (true) {
                        inputMessage = in.readUTF();
                        System.out.println("Новое сообщение > " + inputMessage);
                    }
                } catch(IOException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
