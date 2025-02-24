package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions.SYSTEM_PATH_SEPARATOR;

public class CommandFinfo implements Command {
    ServiceFunctions serviceFunctions;

    public CommandFinfo(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) throws IOException {

        if (commandArgs.length == 0) {
            System.out.println("Не передано название файла");
            return currentLocation;
        }
        Path filePath = Paths.get(currentLocation + SYSTEM_PATH_SEPARATOR + commandArgs[0]);

        if (!Files.exists(filePath)) {
            System.out.println("Данный файл не найден");
            return currentLocation;
        }

        serviceFunctions.printFileInfo(filePath);

        System.out.println();

        return currentLocation;

    }
}
