package ru.otus.java.basic.homeworks.homework13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class Server {
    private static final List<ClientHandler> clientHandlers = new ArrayList<>();

    private static double sum(double operand1, double operand2) {
        return operand1 + operand2;
    }
    private static double minus(double operand1, double operand2) {
        return operand1 - operand2;
    }
    private static double multiply(double operand1, double operand2) {
        return operand1 * operand2;
    }
    private static double divide(double operand1, double operand2) {
        if(operand2 == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return operand1 / operand2;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        System.out.println("Сервер запущен");
        while (true) {
            Socket client = socket.accept();
            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            System.out.println("Клиент с портом: " + client.getPort() + " подключился!");
            ClientHandler clientHandler = new ClientHandler(client, inputStream, outputStream);
            clientHandlers.add(clientHandler);
            outputStream.writeUTF("Доступные операции: +, -, *, /. Введите два числа и символ операции или введите exit для выхода");

            String userInput = inputStream.readUTF();

            if (userInput.equals("exit")) {
                System.out.println("Клиент с портом :" + client.getPort() + " отключился!");
                client.close();
                continue;
            }

            String[] parts = userInput.split(" ");

            try {
                double operand1 = parseDouble(parts[0]);
                double operand2 = parseDouble(parts[1]);
                String operation = parts[2];
                double result = 0;
                if(operation.equals("+")) {
                    result = sum(operand1, operand2);
                }
                if(operation.equals("-")) {
                    result = minus(operand1, operand2);
                }
                if(operation.equals("*")) {
                    result = multiply(operand1, operand2);
                }
                if(operation.equals("/")) {

                    result = divide(operand1, operand2);
                }
                outputStream.writeUTF("Результат операции " + operation + " для операндов " + operand1 + " и " + operand2 + " = " + result);
                outputStream.flush();
            } catch(NumberFormatException e) {
                outputStream.writeUTF("Введены некорректные значения. Оба операнда должны быть численными");
                outputStream.flush();
            }
        }
    }
}
