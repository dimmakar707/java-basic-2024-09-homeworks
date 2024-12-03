package ru.otus.java.basic.homeworks.homework10;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        ArrayList<String> findedPhones;

        phoneBook.add("+79211234567", "Иванов Иван Иванович");
        phoneBook.add("+79217654321", "Иванов Иван Иванович");
        phoneBook.add("+79212222222", "Петров Пётр Петрович");
        phoneBook.add("+79213333333", "Сидоров Сидор Сидорович");

        String name = "Иванов Иван Иванович";
        findedPhones = phoneBook.find(name);
        getInfo(findedPhones, name);
        System.out.println("-----------------------------------");

        name = "петров пётр петрович";
        findedPhones = phoneBook.find(name);
        getInfo(findedPhones, name);
        System.out.println("-----------------------------------");

        name = "Борисов Борис Борисович";
        findedPhones = phoneBook.find(name);
        getInfo(findedPhones, name);
        System.out.println("-----------------------------------");

        boolean isContains = phoneBook.containsPhoneNumber("+79211234567");
        System.out.println(isContains);
        System.out.println("-----------------------------------");

        isContains = phoneBook.containsPhoneNumber("+79218888888");
        System.out.println(isContains);

    }

    public static void getInfo(ArrayList<String> list, String name) {
        if (list.size() == 0) {
            System.out.println("По имени: " + name + " ничего не найдено");
        } else {
            System.out.println("По имени: " + name + " найдены телефоны: " + list);
        }
    }
}
