package ru.otus.java.basic.homeworks.homework16.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;

    private String username;
    private Role role;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                System.out.println("Клиент подключился на порту: " + socket.getPort());

                while (true) {
                    sendMsg("Для начала работы надо пройти аутентификацию. Формат команды /auth login password \n" +
                            "или регистрацию. Формат команды /reg login password username ");

                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.equalsIgnoreCase("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }

                        if (message.startsWith("/auth ")) {
                            String[] element = message.split(" ");
                            if (element.length != 3){
                                sendMsg("Неверный формат команды /auth");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .authenticate(this, element[1], element[2])){

                                break;
                            }
                        }

                        if (message.startsWith("/reg ")) {
                            String[] element = message.split(" ");
                            if (element.length != 4){
                                sendMsg("Неверный формат команды /reg");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .registration(this, element[1], element[2], element[3])){
                                break;
                            }
                        }
                    }
                }

                while (true) {
                    String message = in.readUTF();

                    if (message.startsWith("/kick ")) {
                        if(role == Role.ADMIN) {
                            String[] element = message.split(" ");
                            if (element.length != 2){
                                sendMsg("Неверный формат команды /kick");
                                continue;
                            }
                            String removeUsername = element[1];

                            ClientHandler removedClientHandler = server.getClientHandlerByUsername(removeUsername);
                            if(removedClientHandler != null) {
                                server.sendMessageToClient(removeUsername, "Вы удалены за нарушения");
                                server.sendMessageToClient(removeUsername, "/removeok");
                                //removedClientHandler.disconnect();
                                server.broadcastMessage("Пользователь " + username + " удален за нарушения");
                            } else {
                                server.sendMessageToClient(username, "Пользователь с именем " + removeUsername + " не найден");
                            }
                        } else {
                            server.sendMessageToClient(username, "У вас недостаточно прав для данной команды");
                        }
                    }

                    if(message.startsWith("/w")) {
                        String[] strings = message.split(" ", 3);
                        String username = strings[1];
                        String msg = strings[2];
                        server.sendMessageToClient(username, msg);
                        sendMsg(msg);
                    }
                    if (message.startsWith("/")) {
                        if (message.equalsIgnoreCase("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }
                    } else {
                        server.broadcastMessage(username + " : " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        server.unsubscribe(this);
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
