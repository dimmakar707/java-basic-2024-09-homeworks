package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class CommandLs implements Command {
    private ServiceFunctions serviceFunctions;

    public CommandLs(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) throws IOException {
        boolean showDetails = commandArgs.length > 0 && commandArgs[0].equals("-i");
        String currentPath = commandArgs.length > 1 ? commandArgs[1] : currentLocation;

        Path directoryPath = Paths.get(currentPath);

        if (!Files.isDirectory(directoryPath)) {
            System.out.println("Указанный путь не является директорией.");
            return currentLocation;
        }

        Stream<Path> sortedPaths = Files.list(directoryPath).sorted(Comparator.comparing((Path p) -> !Files.isDirectory(p)));

        if (showDetails) {
            System.out.printf("%-10s | %-13s | %-19s | %s", "Тип", "Размер (байт)", "Дата изменения", "Имя директории/файла");
            System.out.println();
            System.out.println("------------------------------------------------------------------------");
        }

        sortedPaths.forEach(filePath -> {
            String message = serviceFunctions.isDir(filePath) ? "директория " + filePath.getFileName() : "файл " + filePath.getFileName();
            if (!showDetails) {
                System.out.println(message);
                return;
            }

            serviceFunctions.printFileInfo(filePath);
            System.out.println();

        });

        return currentLocation;
    }
}
