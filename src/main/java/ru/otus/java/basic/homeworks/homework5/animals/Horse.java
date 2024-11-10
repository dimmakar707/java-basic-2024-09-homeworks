package ru.otus.java.basic.homeworks.homework5.animals;

public class Horse extends Animal {

    public Horse(String name, float runSpeed, float swimSpeed, float endurance) {
        super(name, runSpeed, swimSpeed, endurance);
        this.endurancePerMeterOfRun = 1;
        this.endurancePerMeterOfSwim = 4;
    }

}
