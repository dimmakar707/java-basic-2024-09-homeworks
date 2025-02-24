package ru.otus.java.basic.homeworks.fileManagerProject;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager(".");
            fileManager.init();
        } catch (IOException e) {
            System.out.println("Ошибка в работе менеджера");
            LOGGER.error(e);
        }
    }
}
