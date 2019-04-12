package ru.geekbrains.lesson4.swing;

import javax.swing.*;

public class SwingAppJL {

    private static MainWindowJL mainWindowJL;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainWindowJL = new MainWindowJL();
            }
        });
    }
}
