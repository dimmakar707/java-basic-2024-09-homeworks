package ru.otus.java.basic.homeworks.homework14;

public class Application {

    private static final int NUM_OF_ELEMENTS = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        createArray();
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения однопоточного метода: " + (end - start));

        long start2 = System.currentTimeMillis();
        multiThreadingCreateArray();
        long end2 = System.currentTimeMillis();
        System.out.println("Время выполнения многопоточного метода: " + (end2 - start2));
    }

    public static double[] createArray() {
        double[] array = new double[NUM_OF_ELEMENTS];
        for (int i = 0; i < array.length; i++) {
            array[i] = fillElement(i);
        }
        return array;
    }

    public static double[] multiThreadingCreateArray() throws InterruptedException {
        double[] array = new double[NUM_OF_ELEMENTS];
        final int quarter = NUM_OF_ELEMENTS / 4;

        Thread t1 = new Thread(() -> {
            fillPartOfArray(array, 0, quarter);
        });
        Thread t2 = new Thread(() -> {
            fillPartOfArray(array, quarter, 2 * quarter);
        });
        Thread t3 = new Thread(() -> {
            fillPartOfArray(array, 2 * quarter, 3 * quarter);
        });
        Thread t4 = new Thread(() -> {
            fillPartOfArray(array, 3 * quarter, 4 * quarter);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        return array;
    }

    public static double fillElement(int i) {
        return 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
    }

    public static void fillPartOfArray(double[] array, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            array[i] = fillElement(i);
        }
    }

}
