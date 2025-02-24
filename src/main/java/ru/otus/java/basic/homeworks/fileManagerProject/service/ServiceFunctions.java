package ru.otus.java.basic.homeworks.fileManagerProject.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceFunctions {
    public static final String SYSTEM_PATH_SEPARATOR = File.separator;

    public void printFileInfo(Path filePath) {
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String type = isDir(filePath) ? "директория" : "файл";
        try {
            long sizeInBytes = Files.size(filePath);
            FileTime lastModifiedTime = Files.getLastModifiedTime(filePath);
            Instant instant = lastModifiedTime.toInstant();
            ZonedDateTime zdt = instant.atZone(zoneId);
            String formattedDateTime = zdt.format(formatter);
            System.out.printf("%-10s | %-13s | %-19s | %s", type, sizeInBytes, formattedDateTime, filePath.getFileName());
        } catch (IOException e) {
            System.out.println("Не удалось получить атрибуты директории/файла");
        }

    }

    public boolean isDir(Path path) {
        return Files.isDirectory(path);
    }

    public boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                if (stream.iterator().hasNext()) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    public String changePathSeparator(String path) {
        if(SYSTEM_PATH_SEPARATOR.equals("/")) {
            path = path.replace("\\", SYSTEM_PATH_SEPARATOR);
        } else {
            path = path.replace("/", SYSTEM_PATH_SEPARATOR);
        }
        return path;
    }
}
