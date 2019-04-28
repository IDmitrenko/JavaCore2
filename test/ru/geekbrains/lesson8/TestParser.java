package ru.geekbrains.lesson8;

import org.junit.Test;
import ru.geekbrains.lesson7.client.TextMessage;


import static org.junit.Assert.*;
import static ru.geekbrains.lesson7.client.MessagePatterns.parseTextMessage;
import static ru.geekbrains.lesson7.client.MessagePatterns.parseTextMessageRegx;

/**
 * Unit тесты для различных методов сетевого чата
 * Прежде чем запускать, необходимо отметить папку test как Test Source Root
 */
public class TestParser {

    @Test
    public void testParser() {
        TextMessage textMessage = parseTextMessage("/w userFrom Example of message", "userTo");

        assertNotNull(textMessage);
        assertEquals("userFrom", textMessage.getUserFrom());
        assertEquals("Example of message", textMessage.getText());
        assertEquals("userTo", textMessage.getUserTo());
    }

    @Test
    public void testParserRegx() {
        TextMessage textMessage = parseTextMessageRegx("/w userFrom Example of message", "userTo");

        assertNotNull(textMessage);
        assertEquals("userFrom", textMessage.getUserFrom());
        assertEquals("Example of message", textMessage.getText());
        assertEquals("userTo", textMessage.getUserTo());
    }

    @Test
    public void testParseConnectedMessage() {
        // TODO написать тест для метода MessagePattern.ParseConnectedMessage
    }

    // TODO написать тесты для новых парсеров, если они будут добавлены при выполнении остальных заданий
}