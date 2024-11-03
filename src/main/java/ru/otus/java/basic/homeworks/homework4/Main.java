package ru.otus.java.basic.homeworks.homework4;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Иванов", "Иванов", "Иванович", 1955, "ivanov@yandex.ru");
        User user2 = new User("Петров", "Пётр", "Петрович", 1972, "petrov@yandex.ru");
        User user3 = new User("Сидоров", "Сидор", "Сидорович", 1981, "sidorov@yandex.ru");
        User user4 = new User("Еленова", "Елена", "Егоровна", 1992, "elena@yandex.ru");
        User user5 = new User("Мариева", "Мария", "Михайловна", 1989, "mariya@yandex.ru");
        User user6 = new User("Егоров", "Егор", "Егорович", 1963, "egorov@yandex.ru");
        User user7 = new User("Сергеев", "Сергей", "Сергеевич", 1976, "sergeev@yandex.ru");
        User user8 = new User("Оксанова", "Оксана", "Олеговна", 2001, "oksana@yandex.ru");
        User user9 = new User("Каренина", "Анна", "Аркадьевна", 1873, "anna@yandex.ru");
        User user10 = new User("Олегов", "Олег", "Олегович", 1996, "oleg@yandex.ru");

        User[] users = {user1, user2, user3, user4, user5, user6, user7, user8, user9, user10};

        int currentYear = LocalDate.now().getYear();

        for(User user: users) {
            if(currentYear - user.getBirthYear() > 40) {
                user.userInfo();
                System.out.println();
            }
        }

        //Задание 2
        Box box = new Box(60, 30, 20, "Белый", false);
        box.info();
        box.putInBox("Ёлка");
        box.open();
        box.putInBox("Ёлка");
        box.info();
        box.putInBox("Пылесос");
        box.close();
        box.removeFromBox();
        box.open();
        box.removeFromBox();
        box.removeFromBox();
        box.setColor("чёрный");
        box.putInBox("Колобок");
        box.close();
        box.info();
    }
}
