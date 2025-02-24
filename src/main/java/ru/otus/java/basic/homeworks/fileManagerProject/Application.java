package ru.otus.java.basic.homeworks.fileManagerProject;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager(".");
            fileManager.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
