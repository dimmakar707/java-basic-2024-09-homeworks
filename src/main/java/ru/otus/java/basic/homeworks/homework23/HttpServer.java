package ru.otus.java.basic.homeworks.homework23;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
    }

    public void start() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);

            try (Socket socket = serverSocket.accept()) {
                while(true) {
                    executor.execute(()-> {
                        try {
                            System.out.println("Подключился новый клиент");
                            byte[] buffer = new byte[8192];
                            int n = socket.getInputStream().read(buffer);
                            HttpRequest request = new HttpRequest(new String(buffer, 0, n));
                            request.info(true);
                            dispatcher.execute(request, socket.getOutputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

