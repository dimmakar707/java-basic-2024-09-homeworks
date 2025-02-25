package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.File;
import java.util.regex.Pattern;

import static ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions.SYSTEM_PATH_SEPARATOR;

public class CommandMkDir implements Command {
    ServiceFunctions serviceFunctions;

    public CommandMkDir(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) {

        if (commandArgs.length == 0) {
            System.out.println("Не передано название создаваемой директории");
            return currentLocation;
        }
        String createdDir = serviceFunctions.changePathSeparator(commandArgs[0]);
        String newDir = currentLocation + SYSTEM_PATH_SEPARATOR + createdDir;
        File file = new File(newDir);
        if (file.exists()) {
            System.out.println("Данная директория уже существует");
            return currentLocation;
        }

        String[] createdDirParts = createdDir.split(Pattern.quote(SYSTEM_PATH_SEPARATOR));
        String startDir = currentLocation;
        for (String createdDirPart : createdDirParts) {
            startDir += SYSTEM_PATH_SEPARATOR + createdDirPart;
            File dir = new File(startDir);
            if (dir.exists()) {
                continue;
            }
            boolean result = dir.mkdir();
            if (result) {
                System.out.println("Директория " + startDir + " создана.");
            } else {
                System.out.println("Директорию " + startDir + " создать не удалось.");
            }
        }

        return currentLocation;
    }
}
