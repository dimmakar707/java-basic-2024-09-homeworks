package ru.otus.java.basic.homeworks.homework2;

import java.util.Arrays;

public class Homework {

    public static void main(String[] args) {

        //Задание 1
            printStringSomeTimes(5, "Hello, Java");

        //Задание 2
            int[] arr1 = {-3, 7, 2, 14, -6, 1, 9, 5, 11, 6}; // 7 + 14 + 9 + 11 + 6 = 47

            calculateAndPrintSumOfElementsGreaterFive(arr1);

        //Задание 3
            int[] arr2 = new int[10];

            fillElementsOfArrayByNumber(5, arr2);

            System.out.println(Arrays.toString(arr2));

        //Задание 4
            int[] arr3 = {1, 2, 3, 4, 5};

            increaseElementsOfArray(2, arr3);

            System.out.println(Arrays.toString(arr3));

        //Задание 5
            int[] arr4 = {1, 3, 5, 7, 9, 10, 2, 4, 6, 8}; //Сумма левой половины - 25, сумма правой половины - 30

            calculateSumsOfArrayHalfs(arr4);

        //Задание 1 со звездочкой
            int[] arr5 = {1, 2, 3};
            int[] arr6 = {2, 2};
            int[] arr7 = {1, 1, 1, 1, 1};
            sumOfArrays(arr5, arr6, arr7);

        //Задание 2 со звездочкой
            int[] arr8 = { 1, 1, 1, 1, 1, 5 };
            findEqualHalfsPoint(arr8);

        //Задание 3 со звездочкой
            int[] arr9 = {3, 2, 1, 2, 5};
            checkArrayForAscOrDesc(arr9, false);

        //Задание 4 со звездочкой
            int[] arr10 = {1, 2, 3, 4, 5};
            reverseArray(arr10);
            System.out.println(Arrays.toString(arr10));
    }

    //Метод печатает строку n раз
    public static void printStringSomeTimes(int n, String s) {
        for(int i = 0; i < n; i++) {
            System.out.println((i + 1) + " " + s);
        }
    }

    //Метод считает и печатает сумму всех элементов массива, которые больше пяти
    public static void calculateAndPrintSumOfElementsGreaterFive(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > 5) {
                result += arr[i];
            }
        }
        System.out.println("Сумма элементов, которые больше 5 равна: " + result);
    }

    //Метод заполняет элементы массива указанным числом
    public static void fillElementsOfArrayByNumber(int number, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = number;
        }
    }

    //Метод увеличивает каждый элемент массива на указанное число
    public static void increaseElementsOfArray(int number, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] + number;
        }
    }

    //Метод считает сумма какой из половин массива больше
    public static void calculateSumsOfArrayHalfs(int[] arr) {
        int leftHalfSum = 0;
        int rightHalfSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if(i < arr.length / 2) {
                leftHalfSum += arr[i];
            } else {
                rightHalfSum += arr[i];
            }
        }
        if(leftHalfSum == rightHalfSum) {
            System.out.println("Суммы левой половины и правой половин массива равны");
        } else if(leftHalfSum > rightHalfSum) {
            System.out.println("Сумма левой половины (" + leftHalfSum + ") массива больше суммы правой половины (" + rightHalfSum + ")");
        } else {
            System.out.println("Сумма правой половины (" + rightHalfSum + ") массива больше суммы левой половины (" + leftHalfSum + ")" );
        }
    }

    //метод, принимающий на вход набор целочисленных массивов, и получающий новый массив равный сумме входящих
    public static void sumOfArrays(int[] ...args) {
        //Находим максимальную длину из переданных массивов
            int maxLengthOfArray = 0;
            for (int i = 0; i < args.length; i++) {
                if(args[i].length > maxLengthOfArray) {
                    maxLengthOfArray = args[i].length;
                }
            }
        //Объявляем результирующий массив
            int[] resultArray = new int[maxLengthOfArray];

        //Складываем значения в результирующий массив
            for (int i = 0; i < maxLengthOfArray; i++) { //проходим по индексам
                int elementValue = 0; //значение, которое нужно будет положить в ячейку результирующего массива
                for (int j = 0; j < args.length; j++) { //проходим по элементам каждого переданного массива
                    if(i < args[j].length) {
                        elementValue += args[j][i];
                    }
                }
                resultArray[i] = elementValue;
            }

        System.out.println(Arrays.toString(resultArray));
    }

    //метод, проверяет есть ли в массиве "точка", в которой сумма элементов левой и правой части массива равны
        public static void findEqualHalfsPoint(int[] arr) {
            int sum = 0; //Сумма всех элементов массива
            boolean result = false;
            int halfIndex = 0;

            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
            if(sum % 2 == 0) { //Если сумма четная - точка возможна, если сумма нечетная - точки нет

                int halfSum = sum / 2; //Находим половину суммы

                for (int i = 0; i < arr.length; i++) { //Проходим по элементам
                    sum -= arr[i];
                    if(sum == halfSum) {
                        result = true;
                        halfIndex = i;
                        break;
                    }
                    if(sum < halfSum) { //
                        break;
                    }
                }

            }

            if(result) {
                System.out.println("Точка найдена, она находится после индекса: " + halfIndex);
            } else {
                System.out.println("Точка не найдена");
            }

        }

    //Метод проверяющий что все элементы в массиве идут в порядке убывания или возрастания
        public static void checkArrayForAscOrDesc(int[] arr, boolean asc) { //если asc равно true, то проверяем на возрастание, иначе - на убывание
            boolean result = true;
            int findIndex = 0;
            if(asc) { //проверяем на возрастание
                for (int i = 0; i < arr.length - 1; i++) {
                    if(arr[i] > arr[i + 1]) {
                        result = false;
                        findIndex = i;
                        break;
                    }
                }
            } else { //проверяем на убывание
                for (int i = 0; i < arr.length - 1; i++) {
                    if(arr[i] < arr[i + 1]) {
                        result = false;
                        findIndex = i;
                        break;
                    }
                }
            }
            if(result) {
                System.out.println("Массив " + (asc ? " возрастает" : " убывает"));
            } else {
                System.out.println("Массив НЕ" + (asc ? " возрастает" : " убывает") + ". Последовательность нарушена после элемента: значение=" + arr[findIndex] + ", индекс=" + findIndex);
            }
        }

    //Метод переворачивающий массив
        public static void reverseArray(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if(i == Math.round(arr.length / 2)) {
                    break;
                }
                int temp = arr[i];
                arr[i] = arr[arr.length - 1 - i];
                arr[arr.length - 1 - i] = temp;
            }
        }
}
