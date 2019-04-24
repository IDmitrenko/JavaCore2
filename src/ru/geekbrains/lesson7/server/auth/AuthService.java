package ru.geekbrains.lesson7.server.auth;

import ru.geekbrains.lesson7.server.User;

public interface AuthService {

    boolean authUser(User user);
}
