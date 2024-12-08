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
    }
}
