package ru.otus.java.basic.homeworks.homework9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Application {
    public static void main(String[] args) {

        //Задание 1
        List<Integer> list = fillArrayList(-5, 5);
        System.out.println(list);

        //Задание 2
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 7, 3, 9, 8, 2, 6, 5));
        int sum = sumOfArrayList(list2);
        System.out.println(sum);

        //Задание 3
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        fillArrayListByValue(list3, 5);
        System.out.println(list3);

        //Задание 4
        List<Integer> list4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        addValueToElementsOfArrayList(list4, 3);
        System.out.println(list4);

        //Задание 5. Список имён сотрудников
        List<Employee> list5 = new ArrayList<>();
        list5.add(new Employee("Андрей", 30));
        list5.add(new Employee("Марина", 27));
        list5.add(new Employee("Елена", 33));
        list5.add(new Employee("Иван", 21));
        list5.add(new Employee("Сергей", 38));
        List<String> listOfNames = getListOfEmployeeNames(list5);
        System.out.println(listOfNames);

        //Задание 6. Список сотрудников старше чем
        List<Employee> olderEmployees = getListOfOlderEmployees(list5, 30);
        System.out.println(olderEmployees);

        //Задание 7. Проверить превышает ли средний возраст списка сотрудников указанное значение
        boolean isOlder = checkAverageAgeOfEmployeeList(list5, 30);
        System.out.println(isOlder);

        //Задание 8. Вернуть ссылку на самого молодого сотрудника в списке
        Employee youngestEmployee = getYoungestEmployee(list5);
        System.out.println(youngestEmployee);

    }

    public static List<Integer> fillArrayList(int min, int max) {
        int capacity = max - min;
        List<Integer> list = new ArrayList<>(capacity);
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    public static int sumOfArrayList(List<Integer> list) {
        int sum = 0;
        for (Integer i : list) {
            if (i > 5) {
                sum += i;
            }
        }
        return sum;
    }

    public static void fillArrayListByValue(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, value);
        }
    }

    public static void addValueToElementsOfArrayList(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + value);
        }
    }

    public static List<String> getListOfEmployeeNames(List<Employee> list) {
        List<String> listOfNames = new ArrayList<>();
        for (Employee emp : list) {
            listOfNames.add(emp.getName());
        }
        return listOfNames;
    }

    public static List<Employee> getListOfOlderEmployees(List<Employee> list, int age) {
        List<Employee> resultList = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= age) {
                resultList.add(emp);
            }
        }
        return resultList;
    }

    public static boolean checkAverageAgeOfEmployeeList(List<Employee> list, int avgAge) {
        float avgAgeOfEmployee = 0;
        for (Employee emp : list) {
            avgAgeOfEmployee += emp.getAge();
        }
        avgAgeOfEmployee /= list.size();
        System.out.println("Средний возраст списка сотрудников: " + avgAgeOfEmployee);
        return avgAgeOfEmployee > avgAge;
    }

    public static Employee getYoungestEmployee(List<Employee> list) {
        int minAge = list.get(0).getAge();
        Employee youngestEmployee = list.get(0);
        for (Employee emp : list) {
            if (emp.getAge() < minAge) {
                minAge = emp.getAge();
                youngestEmployee = emp;
            }
        }
        return youngestEmployee;
    }
}
