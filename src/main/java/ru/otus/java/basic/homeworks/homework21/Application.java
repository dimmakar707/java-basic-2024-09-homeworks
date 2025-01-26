package ru.otus.java.basic.homeworks.homework21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    private String letter;

    public void printLetter(String letter) {
        System.out.print(letter);
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public static void main(String[] args) {

        Application app = new Application();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(()->{
            synchronized (app) {
                try {
                    for (int i = 0; i < 5; i++) {
                        app.printLetter("A");
                        app.setLetter("A");
                        while (app.letter != "C") {
                            app.wait();
                        }
                        app.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(()->{
            synchronized (app) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (app.letter != "A") {
                            app.wait();
                        }
                        app.printLetter("B");
                        app.setLetter("B");
                        app.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(()->{
            synchronized (app) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (app.letter != "B") {
                            app.wait();
                        }
                        app.printLetter("C");
                        app.setLetter("C");
                        app.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.shutdown();
    }
}
