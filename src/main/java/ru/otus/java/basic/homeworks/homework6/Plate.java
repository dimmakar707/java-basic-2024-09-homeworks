package ru.otus.java.basic.homeworks.homework6;

public class Plate {
    int maxCapacity;
    int currentCapacity;

    public Plate(int maxCapacity) {
        System.out.println("Создана тарелка с количеством еды " + maxCapacity);
        this.maxCapacity = maxCapacity;
        currentCapacity = maxCapacity;
    }

    public void addFood(int quantity) {
        System.out.println("Кладем еду в тарелку");
        currentCapacity = Math.min(currentCapacity + quantity, maxCapacity);
    }

    public boolean decreaseFood(int quantity) {
        if (currentCapacity <= quantity) {
            return false;
        }
        currentCapacity = currentCapacity - quantity;
        return true;
    }

    public void info() {
        System.out.println("В тарелке находится " + currentCapacity + " единиц еды");
    }
}
