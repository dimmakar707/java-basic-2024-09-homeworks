package ru.otus.java.basic.homeworks.homework20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите название файла и искомую строку через пробел:");
        String input = sc.nextLine();

        String fileName = input.split(" ")[0];
        String searchText = input.split(" ")[1];

        int count = countTextInFile(searchText, fileName);
        if(count != -1) {
            System.out.println("Искомый текст встречается в файле " + count + " раз.");
        }

        sc.close();
    }

    public static int countTextInFile(String text, String fileName) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count += countTextInLine(text, line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return count;
    }

    public static int countTextInLine(String text, String line) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(text, index)) != -1) {
            count++;
            index += text.length();
        }
        return count;
    }
}
