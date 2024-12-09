package ru.otus.java.basic.homeworks.homework11;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 8, 5, 3, 4, 9, 4, 3, 6};
        System.out.println("Начальный массив: " + Arrays.toString(nums));
        SortArray.bubbleSort(nums);
        System.out.println("Отсортированный массив: " + Arrays.toString(nums));

        System.out.println("------------------");

        int[] nums2 = {1, 5, 3, 8, 5, 3, 4, 9, 4, 3, 6};
        System.out.println("Начальный массив: " + Arrays.toString(nums2));
        SortArray.quickSort(nums2);
        System.out.println("Отсортированный массив: " + Arrays.toString(nums2));


        PersonDataBase personDataBase = new PersonDataBase();
        Person person1 = new Person("Ivanov Ivan", Position.DIRECTOR, 1L);
        Person person2 = new Person("Petrov Petr", Position.JUNIOR_DEVELOPER, 2L);
        Person person3 = new Person("Sidorov Sidor", Position.PLUMBER, 3L);
        personDataBase.add(person1);
        personDataBase.add(person2);
        personDataBase.add(person3);

        Person findedPerson1 = personDataBase.findById(2L);
        System.out.println(findedPerson1);

        boolean isManager1 = personDataBase.isManager(person1);
        System.out.println(isManager1);

        boolean isEmployee1 = personDataBase.isEmployee(person1.getId());
        System.out.println(isEmployee1);

        boolean isManager2 = personDataBase.isManager(person2);
        System.out.println(isManager2);

        boolean isEmployee2 = personDataBase.isEmployee(person2.getId());
        System.out.println(isEmployee2);
    }
}
