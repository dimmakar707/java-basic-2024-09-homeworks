package ru.otus.java.basic.homeworks.homework18.server;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InDBAuthenticatedProvider implements AuthenticatedProvider {

    private class User {
        private String login;
        private String password;
        private String username;
        private String role;

        public User(String login, String password, String username, String role) {
            this.login = login;
            this.password = password;
            this.username = username;
            this.role = role;
        }
    }

    private static final String IS_LOGIN_BUSY_QUERY = "SELECT count(1) FROM users WHERE login = ?";
    private static final String IS_USERNAME_BUSY_QUERY = "SELECT count(1) FROM users WHERE username = ?";
    private static final String GET_ALL_USERS_QUERY = "SELECT u.login, u.username, u.password, r.name as role_name FROM users u " +
                                                        "JOIN users_to_roles ur on u.id = ur.user_id " +
                                                        "JOIN roles r on r.id = ur.role_id";
    private static final String GET_ROLE_OF_USER = "select r.name from roles r " +
                                                    "join users_to_roles ur on r.id = ur.role_id " +
                                                    "join users u on ur.user_id = u.id " +
                                                    "where login = ? and password = ?";

    private static final String GET_USERNAME_OF_USER = "select username from users " +
                                                        "where login = ? and password = ?";

    private static final String ADD_USER_TO_DB = "INSERT INTO users (login, password, username) VALUES (?, ?, ?)";
    private static final String SET_USER_ROLE_IN_DB = "INSERT INTO users_to_roles (user_id, role_id) VALUES (?, ?)";

    private List<User> users;
    private Server server;
    private Connection connection;

    public InDBAuthenticatedProvider(Server server) {
        this.server = server;
        this.users = new CopyOnWriteArrayList<>();
        initialize();
        try(Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_QUERY)) {
                while (resultSet.next()) {
                    String login = resultSet.getString("login");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String role_name = resultSet.getString("role_name");
                    User user = new User(login, username, password, role_name);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        final String DATABASE_URL = "jdbc:postgresql://localhost:5432/console-chat-db";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "admin", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        try(PreparedStatement prStatement = connection.prepareStatement(GET_USERNAME_OF_USER)) {
            prStatement.setString(1, login);
            prStatement.setString(2, password);
            try(ResultSet resultSet = prStatement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    return username;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String getUserRoleByLoginAndPassword(String login, String password) {
        try(PreparedStatement prStatement = connection.prepareStatement(GET_ROLE_OF_USER)) {
            prStatement.setString(1, login);
            prStatement.setString(2, password);
            try(ResultSet resultSet = prStatement.executeQuery()) {
                if (resultSet.next()) {
                    String role_name = resultSet.getString("name");
                    return role_name;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        String authUsername = getUsernameByLoginAndPassword(login, password);
        String authUserRole = getUserRoleByLoginAndPassword(login, password);
        if (authUsername == null) {
            clientHandler.sendMsg("Неверный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(authUsername)) {
            clientHandler.sendMsg("Указанная учетная запись уже занята");
            return false;
        }
        clientHandler.setUsername(authUsername);
        clientHandler.setRole(authUserRole);
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/authok " + authUsername);

        return true;
    }

    private boolean isLoginAlreadyExists(String login) {
        int flag = 0;
        try(PreparedStatement prStatement = connection.prepareStatement(IS_LOGIN_BUSY_QUERY)) {
            prStatement.setString(1, login);
            try(ResultSet resultSet = prStatement.executeQuery()) {
                if (resultSet.next()) {
                    flag = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag == 1;
    }

    private boolean isUsernameAlreadyExists(String username) {
        int flag = 0;
        try(PreparedStatement prStatement = connection.prepareStatement(IS_USERNAME_BUSY_QUERY)) {
            prStatement.setString(1, username);
            try(ResultSet resultSet = prStatement.executeQuery()) {
                if (resultSet.next()) {
                    flag = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag == 1;
    }

    @Override
    public boolean registration(ClientHandler clientHandler, String login, String password, String username) {
        if (login.length() < 3 || password.length() < 3 || username.length() < 3) {
            clientHandler.sendMsg("Логин 3+ символа, пароль 3+ символа, имя пользователя 3+ символа");
            return false;
        }
        if (isLoginAlreadyExists(login)) {
            clientHandler.sendMsg("Указанный логин уже занят");
            return false;
        }
        if (isUsernameAlreadyExists(username)) {
            clientHandler.sendMsg("Указанное имя пользователя уже занято");
            return false;
        }
        users.add(new User(login, password, username, "user"));
        try(PreparedStatement prStatement = connection.prepareStatement(ADD_USER_TO_DB, Statement.RETURN_GENERATED_KEYS)) {
            prStatement.setString(1, login);
            prStatement.setString(2, password);
            prStatement.setString(3, username);
            int affectedRows = prStatement.executeUpdate();
            if(affectedRows > 0) {
                System.out.println("affectedRows > 0");
                try (ResultSet rs = prStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("rs.next()");
                        int insertedUserId = rs.getInt(1);
                        try(PreparedStatement prStatement2 = connection.prepareStatement(SET_USER_ROLE_IN_DB)) {
                            prStatement2.setInt(1, insertedUserId);
                            prStatement2.setInt(2, 2);
                            prStatement2.executeQuery();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clientHandler.setUsername(username);
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/regok " + username);
        return true;
    }
}