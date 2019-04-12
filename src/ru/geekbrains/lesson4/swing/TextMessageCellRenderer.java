package ru.geekbrains.lesson4.swing;

import ru.geekbrains.lesson4.TextMessage;

import javax.swing.*;
import java.awt.*;

public class TextMessageCellRenderer extends JPanel implements ListCellRenderer<TextMessage> {

    private final JLabel created;

    private final JLabel userName;

    private final JTextArea messageText;

    public TextMessageCellRenderer() {
        setLayout(new BorderLayout());

        created = new JLabel();
        userName = new JLabel();
        messageText = new JTextArea();

        Font f = userName.getFont();
        userName.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);

        add(created, BorderLayout.EAST);
        add(userName, BorderLayout.NORTH);
        add(messageText, BorderLayout.WEST);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends TextMessage> list,
                                                  TextMessage value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        setBackground(list.getBackground());
        created.setText(value.getCreated().toString());
        userName.setOpaque(true);
        userName.setText(value.getUserName());
        messageText.setText(value.getText());
        return this;
    }
}