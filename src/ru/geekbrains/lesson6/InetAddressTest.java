package ru.geekbrains.lesson6;

import java.net.InetAddress;
import java.net.UnknownHostException;

class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress Address = InetAddress.getLocalHost();
        System.out.println(Address);

        Address = InetAddress.getByName("www.sport-express.ru");
        System.out.println(Address);

        InetAddress AllName[] = InetAddress.getAllByName("www.mail.ru");
        for (int i = 0; i < AllName.length; i++) {
            System.out.println(AllName[i]);
        }


    }
}
