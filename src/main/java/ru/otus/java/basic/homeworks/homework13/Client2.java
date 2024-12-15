package ru.otus.java.basic.homeworks.homework13;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public void send(String message) {
        try {
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMessage() {
        try {
            String result = inputStream.readUTF();
            System.out.println(result);
        } catch(IOException e) {
            System.out.println("Сервер закрыл соединение");
        }
    }

    public Client2(InputStream in, OutputStream out) {
        this.inputStream = new DataInputStream(in);
        this.outputStream = new DataOutputStream(out);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);
            Client2 client = new Client2(socket.getInputStream(), socket.getOutputStream());
            client.getMessage();
            while (true) {
                String userMessage = scanner.nextLine();
                if(userMessage.equals("exit")) {
                    client.send(userMessage);
                    break;
                }
                client.send(userMessage);
                client.getMessage();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
    }
}

