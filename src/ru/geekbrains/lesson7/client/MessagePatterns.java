package ru.geekbrains.lesson7.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessagePatterns {

    public static final String AUTH_PATTERN = "/auth %s %s";
    public static final String AUTH_SUCCESS_RESPONSE = "/auth successful";
    public static final String AUTH_FAIL_RESPONSE = "/auth fail";

    public static final String DISCONNECT = "/disconnect";
    public static final String CONNECTED = "/connected";
    public static final String CONNECTED_SEND = CONNECTED + " %s";
    public static final String DISCONNECTED_PREFIX = "/d";
    public static final String DISCONNECTED_SEND_PATTERN = DISCONNECTED_PREFIX + " %s %s";
    public static final Pattern DISCONNECTED_REC_PATTERN = Pattern.compile("^/d (\\w+) (.+)", Pattern.MULTILINE);

    public static final String MESSAGE_PREFIX = "/w";
    public static final String MESSAGE_SEND_PATTERN = MESSAGE_PREFIX + " %s %s";

    public static final Pattern MESSAGE_REC_PATTERN = Pattern.compile("^/w (\\w+) (.+)", Pattern.MULTILINE);

    public static final String LIST_USER = "/l";
    public static final String LIST_SEND_PATTERN = LIST_USER + " %s";
    public static final Pattern LIST_REC_PATTERN = Pattern.compile("^/l (.+)", Pattern.MULTILINE);

    public static TextMessage parseTextMessageRegx(String text, String userTo) {
        Matcher matcher = MESSAGE_REC_PATTERN.matcher(text);
        if (matcher.matches()) {
            return new TextMessage(matcher.group(1), userTo,
                    matcher.group(2));
        } else {
            System.out.println("Unknown message pattern: " + text);
            return null;
        }
    }

    public static TextMessage parseDisconnectMessageRegx(String text, String userTo) {
        Matcher matcher = DISCONNECTED_REC_PATTERN.matcher(text);
        if (matcher.matches()) {
            return new TextMessage(matcher.group(1), userTo,
                    matcher.group(2));
        } else {
            System.out.println("Unknown message pattern: " + text);
            return null;
        }
    }

    public static String parseUserList(String text) {
        Matcher matcher = LIST_REC_PATTERN.matcher(text);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            System.out.println("Unknown message pattern: " + text);
            return null;
        }
    }

    public static TextMessage parseTextMessage(String text, String userTo) {
        String[] parts = text.split(" ", 3);
        if (parts.length == 3 && parts[0].equals(MESSAGE_PREFIX)) {
            return new TextMessage(parts[1], userTo, parts[2]);
        } else {
            System.out.println("Unknown message pattern: " + text);
            return null;
        }
    }

    public static String parseConnectedMessage(String text) {
        String[] parts = text.split(" ");
        if (parts.length == 2 && parts[0].equals(CONNECTED)) {
            return parts[1];
        } else {
            System.out.println("Unknown message pattern: " + text);
            return null;
        }
    }

    public static String parseDisconnectedMessage(String text) {
        String[] parts = text.split(" ");
        if (parts.length == 2 && parts[0].equals(DISCONNECT)) {
            return parts[1];
        } else {
            System.out.println("Unknown message pattern: " + text);
            return null;
        }
    }
}
