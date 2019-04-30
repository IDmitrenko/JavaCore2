package ru.geekbrains.lesson8;

import org.junit.Test;
import ru.geekbrains.lesson7.client.TextMessage;


import static org.junit.Assert.*;
import static ru.geekbrains.lesson7.client.MessagePatterns.*;

/**
 * Unit тесты для различных методов сетевого чата
 * Прежде чем запускать, необходимо отметить папку test как Test Source Root
 */
public class TestParser {

    @Test
    public void testParserRegEx() {
        TextMessage textMessage = parseTextMessageRegEx("/w userFrom Example of message", "userTo");

        assertNotNull(textMessage);
        assertEquals("userFrom", textMessage.getUserFrom());
        assertEquals("Example of message", textMessage.getText());
        assertEquals("userTo", textMessage.getUserTo());
    }

    @Test
    public void testParseConnectMessageRegEx() {
        TextMessage textMessage = parseConnectMessageRegEx("/c userFrom Connected to chat", "userTo");

        assertNotNull(textMessage);
        assertEquals("userFrom", textMessage.getUserFrom());
        assertEquals("Connected to chat", textMessage.getText());
        assertEquals("userTo", textMessage.getUserTo());
    }

    @Test
    public void testParseDisconnectMessageRegEx() {
        TextMessage textMessage = parseDisconnectMessageRegEx("/d userFrom Connected to chat", "userTo");

        assertNotNull(textMessage);
        assertEquals("userFrom", textMessage.getUserFrom());
        assertEquals("Connected to chat", textMessage.getText());
        assertEquals("userTo", textMessage.getUserTo());
    }

    @Test
    public void testParseUserList() {
        String userList = parseUserList("/l ivan petr");

        assertNotNull(userList);
        assertEquals("ivan petr", userList);
    }

    @Test
    public void testParseConnectedMessage() {
        String user = parseConnectedMessage("/c ivan connected");

        assertNotNull(user);
        assertEquals("ivan", user);
    }

    @Test
    public void testParseDisconnectedMessage() {
        String user = parseDisconnectedMessage("/d petr disconnect");

        assertNotNull(user);
        assertEquals("petr", user);
    }

}