package ru.otus.java.basic.homeworks.homework6;

public class Cat {
    String name;
    int appetite;
    boolean isHappy;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        isHappy = false;
    }

    public void eat(Plate plate) {
        if (plate.decreaseFood(appetite)) {
            isHappy = true;
            System.out.println("Кушает кот " + name);
        } else {
            System.out.println("Коту " + name + " не хватило еды");
        }
    }

    public void info() {
        String state = isHappy ? "сыт" : "голоден";
        System.out.println("Кот " + name + " " + state);
    }

}
