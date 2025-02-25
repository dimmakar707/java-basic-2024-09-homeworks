package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.File;
import java.io.IOException;

import static ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions.SYSTEM_PATH_SEPARATOR;

public class CommandCd implements Command {
    ServiceFunctions serviceFunctions;

    public CommandCd(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) throws IOException {
        String newDir = currentLocation + SYSTEM_PATH_SEPARATOR + serviceFunctions.changePathSeparator(commandArgs[0]);
        File file = new File(newDir);
        if (!file.isDirectory()) {
            System.out.println("Данная директория не существует");
            return currentLocation;
        }
        return file.getCanonicalPath();
    }
}
