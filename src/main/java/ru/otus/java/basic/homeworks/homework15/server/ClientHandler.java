package ru.otus.java.basic.homeworks.homework15.server;

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
    private static int userCount = 0;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        userCount++;
        username = "user" + userCount;

        new Thread(() -> {
            try {
                System.out.println("Клиент подключился " + socket.getPort());

                while(true) {
                    String message = in.readUTF();
                    //Тут код для дз String[] strings = message.split(" ", 3);
                    if(message.startsWith("/w")) {
                        String[] strings = message.split(" ", 3);
                        String username = strings[1];
                        String msg = strings[2];
                        server.sendMessageToClient(username, msg);
                        sendMsg(msg);
                    }

                    if(message.startsWith("/")) {
                        if(message.equalsIgnoreCase("/exit")) {
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
            if(in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }
}
