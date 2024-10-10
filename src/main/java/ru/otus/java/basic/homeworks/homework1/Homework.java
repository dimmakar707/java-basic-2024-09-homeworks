package ru.otus.java.basic.homeworks.homework1;

import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {

        greetings();
        checkSign(-10, 2, 3);
        selectColor();
        compareNumbers();
        addOrSubtractAndPrint(10, 3, false);

        //Задание со звездочкой:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение от 1 до 5:");
        int userAnswer = scanner.nextInt();
        while(userAnswer < 1 || userAnswer > 5) {
            System.out.println("Ещё попытка! От 1 до 5:");
            userAnswer = scanner.nextInt();
        }

        switch(userAnswer) {
            case 1:
                greetings();
                break;
            case 2:
                int a = generateRandomIntNumber(-10, 10);
                int b = generateRandomIntNumber(-10, 10);
                int c = generateRandomIntNumber(-10, 10);
                System.out.println("Метод checkSign вызыван с параметрами: " + a + ", " + b + ", " + c);
                checkSign(a, b, c);
                break;
            case 3:
                selectColor();
                break;
            case 4:
                compareNumbers();
                break;
            case 5:
                int e = generateRandomIntNumber(-10, 10);
                int f = generateRandomIntNumber(-10, 10);
                int g = generateRandomIntNumber(0, 1);
                boolean increment = (g == 0) ? false : true;
                System.out.println("Метод addOrSubtractAndPrint вызван с параметрами: " + e + ", " + f + ", " + increment);
                addOrSubtractAndPrint(e, f, increment);
                break;
        }
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a, int b, int c) {
        int result = a + b + c;
        if(result >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void selectColor() {
        int data = 15;
        if(data <= 10) {
            System.out.println("Красный");
        } else if(data > 10 && data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 3;
        int b = 7;
        if(a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        int result;
        if(increment) {
            result = initValue + delta;
        } else {
            result = initValue - delta;
        }
        System.out.println("Результат: " + result);
    }

    public static int generateRandomIntNumber(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
