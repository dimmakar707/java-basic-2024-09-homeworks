package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

public class CommandFind implements Command {
    ServiceFunctions serviceFunctions;
    boolean isFinded;

    public CommandFind(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) throws IOException {
        if (commandArgs.length == 0) {
            System.out.println("Не передано название файла");
            return currentLocation;
        }
        Path rootDirectory = Paths.get(currentLocation);
        String fileName = commandArgs[0];
        boolean isFindAll = commandArgs.length >= 2 && commandArgs[1].equals("-all");
        boolean result = findFile(rootDirectory, fileName, isFindAll);
        if (!result) {
            System.out.println("Файл с таким именем не найден");
        }
        return currentLocation;
    }

    private boolean findFile(Path rootDirectory, String filename, boolean findAll) throws IOException {
        isFinded = false;
        Files.walkFileTree(rootDirectory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (Objects.equals(file.getFileName().toString(), filename)) {
                    isFinded = true;
                    System.out.println("Найден файл: " + file.toAbsolutePath());
                    if (!findAll) {
                        return FileVisitResult.TERMINATE;
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return isFinded;
    }
}
