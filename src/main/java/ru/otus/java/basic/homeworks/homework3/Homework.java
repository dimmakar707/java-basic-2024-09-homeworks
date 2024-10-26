package ru.otus.java.basic.homeworks.homework3;

public class Homework {
    public static void main(String[] args) {

        //Задание 1
            int[][] array1 = {{1,2,-3}, {4,-10,6}, {-7,8,9}}; //1 + 2 + 4 + 6 + 8 + 9 = 30
            System.out.println("Сумма положительных элементов массива array1 равна: " + sumOfPositiveElements(array1));

            System.out.println("-----------------");

        //Задание 2
            printSquare(10);

            System.out.println("-----------------");

        //Задание 3
            int[][] array2 = {{1,1,1,1,1}, {1,1,1,1,1}, {1,1,1,1,1}, {1,1,1,1,1}, {1,1,1,1,1}}; //Массив 5х5
            int[][] array3 = {{2,2,2,2}, {2,2,2,2}, {2,2,2,2}, {2,2,2,2}}; //Массив 4х4
            setDiagonalElementsToZero(array2);
            setDiagonalElementsToZero(array3);
            printArray(array2);
            System.out.println();
            printArray(array3);

            System.out.println("-----------------");

        //Задание 4
            int[][] array4 = {{1, -4, 8}, {16, -2, 37}, {11, 3, 28}};
            System.out.println("Максимальный элемент в массиве array4 = " + findMax(array4));

        System.out.println("-----------------");

        //Задание 5
            int[][] array5 = {{1,2,3}, {4,5,6}, {7,8,9}};
            int[][] array6 = {{1,2,3,4,5}};
            System.out.println("Сумма элементов второй строки массива array5 равна " + sumOfSecondRowElements(array5));
            System.out.println("Сумма элементов второй строки массива array6 равна " + sumOfSecondRowElements(array6));

    }

    //Метод считает сумму положительных элементов двумерного массива
    public static int sumOfPositiveElements(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j] > 0) {
                    sum += array[i][j];
                }
            }
        }
        return sum;
    }

    //Метод печатает квадрат из звездочек, со стороной равной n
    public static void printSquare(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(" * ");
            }
            System.out.println();
        }
    }

    //Метод зануляет диагональные элементы массива
    public static void setDiagonalElementsToZero(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(i == j || array[0].length - 1 - j == i) {
                    array[i][j] = 0;
                }
            }
        }
    }

    //Метод ищет максимальный элемент в двумерном массиве
    public static int findMax(int[][] array) {
        int max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        return max;
    }

    //Метод считает сумму элементов второй строки массива
    public static int sumOfSecondRowElements(int[][] array) {

        if (array.length < 2) {
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < array[1].length; i++) {
            sum += array[1][i];
        }
        return sum;
    }

    //Вспомогательный метод, выводящий массив в виде "таблицы"
    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(" " + array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
