package ru.otus.java.basic.homeworks.homework8;

public class Application {

    public static int sumElementsOfArray(String[][] array) throws Exception {

        int sum = 0;

        if(array.length != 4) {
            throw new AppArraySizeException("Неверный размер массива");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new AppArraySizeException("Неверный размер массива");
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (Exception e) {
                    throw new AppArrayDataException("Неверное значение в ячейке [" + i + "][" + j + "]" + " - " + array[i][j]);
                }
            }
        }

        return sum;

    }

    public static void main(String[] args) {

        String[][] array1 = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"},
        };

        String[][] array2 = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
        };

        String[][] array3 = {
            {"1", "2", "3", "4"},
            {"5", "6", "7"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"},
        };

        String[][] array4 = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "badValue", "12"},
            {"13", "14", "15", "16"},
        };

        try {
            int sum = sumElementsOfArray(array1);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int sum = sumElementsOfArray(array2);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int sum = sumElementsOfArray(array3);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int sum = sumElementsOfArray(array4);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
