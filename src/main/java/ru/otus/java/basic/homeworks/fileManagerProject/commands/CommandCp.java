package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CommandCp implements Command {
    ServiceFunctions serviceFunctions;

    public CommandCp(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) {
        boolean isForce = commandArgs.length > 2 && commandArgs[2].equals("-f");
        Path from = Paths.get(commandArgs[0]);
        Path to = Paths.get(commandArgs[1]);

        if (commandArgs.length < 2) {
            System.out.println("Указаны не все пути для копирования файла");
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
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentLocation;
    }
}
