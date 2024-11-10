package ru.otus.java.basic.homeworks.homework5.animals;

public class Cat extends Animal {

    public Cat(String name, float runSpeed, float swimSpeed, float endurance) {
        super(name, runSpeed, swimSpeed, endurance);
        this.endurancePerMeterOfRun = 1;
    }

    @Override
    public float swim(int distance) {
        System.out.println("Коты плавать не умеют");
        return -1;
    }

}
