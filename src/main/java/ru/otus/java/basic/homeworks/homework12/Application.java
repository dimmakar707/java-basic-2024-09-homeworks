package ru.otus.java.basic.homeworks.homework12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> rootFileNames = getRootFiles();

        if(rootFileNames.isEmpty()) {
            System.out.println("В корневой директории нет текстовых файлов");
        } else {
            System.out.println("В корневой директории содержатся файлы:");
            showFileNames(rootFileNames);
            String choosedFile = chooseFile(rootFileNames, scanner);
            if(choosedFile != null) {
                getFileContent(choosedFile);
                System.out.println("\nВведите строку, которую нужно добавить в файл. Для выхода введите exit");
                writeToFile(choosedFile, scanner);
            }
        }

        scanner.close();

    }

    public static List<String> getRootFiles() {
        File file = new File("./");
        List<String> rootFileNames = new ArrayList<>();
        for (File f : file.listFiles()) {
            String fileName = f.getName();
            if (fileName.endsWith(".txt")) {
                rootFileNames.add(fileName);
            }
        }
        return rootFileNames;
    }

    public static void showFileNames(List<String> rootFileNames) {
        for (String fileName : rootFileNames) {
            System.out.println(fileName);
        }
    }

    public static String chooseFile(List<String> rootFileNames, Scanner scanner) {
        if(!rootFileNames.isEmpty()) {
            System.out.println("Введите название файла:");
            String choosedFile = scanner.nextLine();
            while (!rootFileNames.contains(choosedFile)) {
                System.out.println("Такого файла нет. Введите новое название файла:");
                choosedFile = scanner.nextLine();
            }
            return choosedFile;
        }
        return null;
    }

    public static void getFileContent(String fileName) {
        System.out.println("Содержимое файла " + fileName + ":");
        try (InputStreamReader in = new InputStreamReader(new BufferedInputStream(new FileInputStream(fileName)))) {
            int n = in.read();
            while (n != -1) {
                System.out.print((char) n);
                n = in.read();
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать содержимое файла " + fileName);
            e.printStackTrace();
        }
    }

    public static void writeToFile(String fileName, Scanner scanner) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName, true))) {
            String writedString = scanner.nextLine();
            while (!writedString.equals("exit")) {
                writedString = "\n" + writedString;
                byte[] buffer = writedString.getBytes(StandardCharsets.UTF_8);
                for (int i = 0; i < buffer.length; i++) {
                    out.write(buffer[i]);
                }
                out.flush();
                System.out.println("Введите следующую строку:");
                writedString = scanner.nextLine();
            }
        } catch (IOException e) {
            System.out.println("Не удалось записать в файл " + fileName);
            e.printStackTrace();
        }
    }
}
