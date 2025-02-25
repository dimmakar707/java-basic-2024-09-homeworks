package ru.otus.java.basic.homeworks.fileManagerProject;

import ru.otus.java.basic.homeworks.fileManagerProject.commands.*;
import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileManager {
    private Scanner scanner;
    private String currentLocation;
    ServiceFunctions serviceFunctions;
    Command commandLs;
    Command commandCd;
    Command commandMkDir;
    Command commandRm;
    Command commandMv;
    Command commandCp;
    Command commandFinfo;
    Command commandHelp;
    Command commandFind;
    private static final Logger LOGGER = LogManager.getLogger(FileManager.class);

    public FileManager(String currentLocation) throws IOException {
        serviceFunctions = new ServiceFunctions();
        commandLs = new CommandLs(serviceFunctions);
        commandCd = new CommandCd(serviceFunctions);
        commandMkDir = new CommandMkDir(serviceFunctions);
        commandRm = new CommandRm(serviceFunctions);
        commandMv = new CommandMv(serviceFunctions);
        commandCp = new CommandCp(serviceFunctions);
        commandFinfo = new CommandFinfo(serviceFunctions);
        commandHelp = new CommandHelp(serviceFunctions);
        commandFind = new CommandFind(serviceFunctions);
        File file = new File(serviceFunctions.changePathSeparator(currentLocation));
        this.currentLocation = file.getCanonicalPath();
    }

    public void init() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Введите команду. Для просмотра всех команд введите команду info. Для выхода введите \"exit\"");
            System.out.print(currentLocation + ": ");
            String line = scanner.nextLine();
            while (!line.equals("exit")) {
                executeCommand(line);
                System.out.print(currentLocation + ": ");
                line = scanner.nextLine();
            }
            System.out.println("Благодарим вас за использование нашего \"прекрасного\" продукта.");
        } catch (IOException e) {
            System.out.println("Ошибка инициализации менеджера");
            LOGGER.error(e);
        } finally {
            scanner.close();
        }
    }

    public void executeCommand(String command) throws IOException {
        String[] commandParts = command.split("\\s+");
        String commandName = commandParts[0];
        String[] commandArgs = new String[commandParts.length - 1];
        System.arraycopy(commandParts, 1, commandArgs, 0, commandParts.length - 1);
        Arrays.stream(commandArgs).forEach(commandArg -> serviceFunctions.changePathSeparator(commandArg));
        switch (commandName) {
            case "ls" -> {
                commandLs.run(currentLocation, commandArgs);
            }
            case "cd" -> {
                this.currentLocation = commandCd.run(currentLocation, commandArgs);
            }
            case "mkdir" -> {
                commandMkDir.run(currentLocation, commandArgs);
            }
            case "rm" -> {
                commandRm.run(currentLocation, commandArgs);
            }
            case "mv" -> {
                commandMv.run(currentLocation, commandArgs);
            }
            case "cp" -> {
                commandCp.run(currentLocation, commandArgs);
            }
            case "finfo" -> {
                commandFinfo.run(currentLocation, commandArgs);
            }
            case "help" -> {
                commandHelp.run(currentLocation, commandArgs);
            }
            case "find" -> {
                commandFind.run(currentLocation, commandArgs);
            }
            default -> {
                System.out.println("Данная функция ещё не реализована");
            }
        }
    }
}
