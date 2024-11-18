package ru.otus.java.basic.homeworks.homework6;

public class Plate {
    private int maxCapacity;
    private int currentCapacity;

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public Plate(int maxCapacity) {
        System.out.println("Создана тарелка с количеством еды " + maxCapacity);
        this.maxCapacity = maxCapacity;
        currentCapacity = maxCapacity;
    }

    public void addFood(int quantity) {
        if (quantity > 0) {
            System.out.println("Кладем еду в тарелку");
            if (currentCapacity + quantity > maxCapacity) {
                currentCapacity = maxCapacity;
            } else {
                currentCapacity += quantity;
            }
        }
    }

    public boolean decreaseFood(int quantity) {
        if (currentCapacity < quantity || quantity < 0) {
            return false;
        }
        currentCapacity = currentCapacity - quantity;
        return true;
    }

    public void info() {
        System.out.println("В тарелке находится " + currentCapacity + " единиц еды");
    }
}
