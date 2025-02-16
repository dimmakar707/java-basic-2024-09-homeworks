package ru.otus.java.basic.homeworks.homework18.server;

import java.sql.SQLException;

public interface AuthenticatedProvider {
    void initialize();
    boolean authenticate(ClientHandler clientHandler, String login, String password );
    boolean registration(ClientHandler clientHandler, String login, String password, String username );

}