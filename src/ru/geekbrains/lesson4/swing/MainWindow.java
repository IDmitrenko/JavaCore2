package ru.geekbrains.lesson4.swing;

import ru.geekbrains.lesson4.TextMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private final JList<TextMessage> messagesList;

    private final DefaultListModel<TextMessage> messageListModel;

    private final TextMessageCellRenderer messageCellRenderer;

    private final JScrollPane scroll;

    private final JPanel sendMessagePanel;

    private final JButton sendButton;

    private final JTextField messageField;

    public MainWindow() {
        setTitle("Application");
        setBounds(200,200, 500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        messagesList = new JList<>();
        messageListModel = new DefaultListModel<>();
        messageCellRenderer = new TextMessageCellRenderer();
        messagesList.setModel(messageListModel);
        messagesList.setCellRenderer(messageCellRenderer);

        scroll = new JScrollPane(messagesList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        sendMessagePanel = new JPanel();
        sendMessagePanel.setLayout(new BorderLayout());
        sendButton = new JButton("Отправить");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = messageField.getText();
                if (text != null && !text.trim().isEmpty()) {
                    TextMessage msg = new TextMessage("Вы", text);
                    messageListModel.add(messageListModel.size(), msg);
                    messageField.setText(null);
                }
            }
        });
        sendMessagePanel.add(sendButton, BorderLayout.EAST);
        messageField = new JTextField();
        sendMessagePanel.add(messageField, BorderLayout.CENTER);

        add(sendMessagePanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}