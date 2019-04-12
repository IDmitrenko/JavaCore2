package ru.geekbrains.lesson4.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowJL extends JFrame {

    public MainWindowJL() {

        setTitle("Geek Chat");
        setBounds(200, 200, 500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        DefaultListModel listModel = new DefaultListModel<>();
        JList<Message> listMessage = new JList<>(listModel);

        JScrollPane scroll = new JScrollPane(listMessage,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        JPanel sendMessagePanel = new JPanel();
        sendMessagePanel.setLayout(new BorderLayout());
        JButton sendButton = new JButton("Отправить");

        sendMessagePanel.add(sendButton, BorderLayout.EAST);
        JTextField messageField = new JTextField();
        sendMessagePanel.add(messageField, BorderLayout.CENTER);

        add(sendMessagePanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myListener(messageField, listModel);
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myListener(messageField, listModel);
            }
        });

        setVisible(true);
        messageField.requestFocus();

    }

    private void myListener(JTextField messageField, DefaultListModel listModel) {

        String msg = messageField.getText();
        if (msg == null || msg.trim().isEmpty()) {
            JOptionPane.showMessageDialog(MainWindowJL.this,
                    "Пустое сообщение не отправляется!",
                    "Отправка сообщения",
                    JOptionPane.ERROR_MESSAGE);
            messageField.setText("");
            messageField.requestFocus();
            return;
        }

        Message message = new Message(msg.trim());
        listModel.addElement(msg);
        messageField.setText("");
        messageField.requestFocus();
    }
}
