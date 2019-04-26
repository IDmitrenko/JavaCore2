package ru.geekbrains.lesson7.client.swing;


import ru.geekbrains.lesson7.client.MessageReciever;
import ru.geekbrains.lesson7.client.Network;
import ru.geekbrains.lesson7.client.TextMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements MessageReciever {

    private final JList<TextMessage> messageList;

    private final DefaultListModel<TextMessage> messageListModel;

    private final TextMessageCellRenderer messageCellRenderer;

    private final JScrollPane scroll;

    private final JPanel sendMessagePanel;

    private final JButton sendButton;

    private final JTextField messageField;

    private final JTextField recipient;

    private final Network network;

    public MainWindow() {
        setTitle("Сетевой чат GeekBrains");
        setBounds(200,200, 600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        messageList = new JList<>();
        messageListModel = new DefaultListModel<>();
        messageCellRenderer = new TextMessageCellRenderer();
        messageList.setModel(messageListModel);
        messageList.setCellRenderer(messageCellRenderer);

        scroll = new JScrollPane(messageList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        sendMessagePanel = new JPanel();
        sendMessagePanel.setLayout(new BorderLayout());
        recipient = new JTextField(7);
        sendMessagePanel.add(recipient, BorderLayout.WEST);
        sendButton = new JButton("Отправить");
        sendMessagePanel.add(sendButton, BorderLayout.EAST);
        messageField = new JTextField();
        sendMessagePanel.add(messageField, BorderLayout.CENTER);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener(messageField, recipient, messageListModel);
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener(messageField,recipient, messageListModel);
            }
        });

        recipient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener(messageField, recipient, messageListModel);
            }
        });

        add(sendMessagePanel, BorderLayout.SOUTH);

        setVisible(true);

        this.network = new Network("localhost", 7777, this);

        LoginDialog loginDialog = new LoginDialog(this, network);
        loginDialog.setVisible(true);

        if (!loginDialog.isConnected()) {
            System.exit(0);
        }

        setTitle("Сетевой чат GeekBrains. Пользователь " + network.getLogin());
    }

    @Override
    public void submitMessage(TextMessage message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                messageListModel.add(messageListModel.size(), message);
                messageList.ensureIndexIsVisible(messageListModel.size() - 1);
            }
        });
    }

    private void myListener(JTextField messageField, JTextField recipient, DefaultListModel messageListModel) {
        String text = messageField.getText();
        String adressee = recipient.getText();
        if (text == null || text.trim().isEmpty()) {
            JOptionPane.showMessageDialog(MainWindow.this,
                    "Пустое сообщение не отправляется!",
                    "Отправка сообщения.",
                    JOptionPane.ERROR_MESSAGE);
            messageField.setText("");
            messageField.requestFocus();
            return;
        }

        if (adressee == null || adressee.trim().isEmpty()) {
            JOptionPane.showMessageDialog(MainWindow.this,
                    "Отсутствует адрес получателя!",
                    "Отправка сообщения.",
                    JOptionPane.ERROR_MESSAGE);
            recipient.setText("");
            recipient.requestFocus();
            return;
        }

        TextMessage msg = new TextMessage(network.getLogin(), adressee, text);
        messageListModel.add(messageListModel.size(), msg);
        messageField.setText(null);
        messageField.requestFocus();

        network.sendTextMessage(msg);

    }
}