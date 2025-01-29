package ru.otus.java.basic.homeworks.homework21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Application {

    private class Letter {
        private String letter = "C";

        public void printLetter(String letter) {
            this.letter = letter;
            System.out.print(letter);
        }
    }

    private final Object lock = new Object();
    private Letter letterObj = new Letter();

    public static void main(String[] args) throws InterruptedException {
        Application app = new Application();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(()->{
            app.toA();
        });
        executor.execute(()->{
            app.toB();
        });
        executor.execute(()->{
            app.toC();
        });
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }

    public void toA() {
        changeAndShowLetter("C", "A");
    }

    public void toB() {
        changeAndShowLetter("A", "B");
    }

    public void toC() {
        changeAndShowLetter("B", "C");
    }

    public void changeAndShowLetter(String currentLetter, String newLetter) {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!letterObj.letter.equals(currentLetter)) {
                        lock.wait();
                    }
                    letterObj.printLetter(newLetter);
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}