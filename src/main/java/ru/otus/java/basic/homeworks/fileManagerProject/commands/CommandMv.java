package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandMv implements Command {
    ServiceFunctions serviceFunctions;
    private static final Logger LOGGER = LogManager.getLogger(CommandMv.class);

    public CommandMv(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) {
        boolean isForce = commandArgs.length > 2 && commandArgs[2].equals("-f");
        Path from = Paths.get(commandArgs[0]);
        Path to = Paths.get(commandArgs[1]);

        if (commandArgs.length < 2) {
            System.out.println("Указаны не все директории для перемещения файла");
            return currentLocation;
        }

        if (!Files.exists(from)) {
            System.out.println("Указанный файл не найден.");
            return currentLocation;
        }

        if (Files.exists(to) && !isForce) {
            System.out.println("Указанный файл уже существует в папке назначения. Если вы хотите заменить его введите команду: mv from to -f");
            return currentLocation;
        }

        try {
            Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл перемещён");
        } catch (IOException e) {
            System.out.println("Не удалось перенести файл");
            LOGGER.error("Исключение в классе CommandMv:", e);
        }

        return currentLocation;
    }
}
