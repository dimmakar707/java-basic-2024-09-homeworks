package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions.SYSTEM_PATH_SEPARATOR;

public class CommandRm implements Command {
    ServiceFunctions serviceFunctions;
    private static final Logger LOGGER = LogManager.getLogger(CommandRm.class);

    public CommandRm(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) throws IOException {
        boolean isForce = commandArgs.length >= 2 && commandArgs[1].equals("-f");
        Path filePath = Paths.get(currentLocation + SYSTEM_PATH_SEPARATOR + serviceFunctions.changePathSeparator(commandArgs[0]));

        if (commandArgs.length < 1) {
            System.out.println("Не указан путь до директории / файла");
            return currentLocation;
        }

        if (!Files.exists(filePath)) {
            System.out.println("Указанная директория / файл не найден");
            return currentLocation;
        }

        if (!serviceFunctions.isEmpty(filePath) && !isForce) {
            System.out.println("Директория не пуста. Чтобы удалить непустую директорию введите команду rm dir -f");
            return currentLocation;
        }

        if (isForce) {
            Files.walkFileTree(filePath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc == null) {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    } else {
                        throw exc;
                    }
                }
            });
            System.out.println(filePath + " удален");
            return currentLocation;
        }

        try {
            Files.delete(filePath);
            System.out.println(filePath + " удален");
        } catch (IOException e) {
            System.out.println("Не удалось удалить файл");
            LOGGER.error("Исключение в классе CommandRm:", e);
        }

        return currentLocation;

    }
}
