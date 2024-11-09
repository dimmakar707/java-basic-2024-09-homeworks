package ru.otus.java.basic.homeworks.homework5;

abstract public class Animal {

    String name;
    float runSpeed;
    float swimSpeed;
    float endurance; //Выносливость
    float endurancePerMeterOfRun; //Затраты выносливости на метер бега
    float endurancePerMeterOfSwim; //Затраты выносливости на метер плавания
    boolean isTired = false;

    public Animal(String name, float runSpeed, float swimSpeed, float endurance) {
        this.name = name;
        this.runSpeed = runSpeed;
        this.swimSpeed = swimSpeed;
        this.endurance = endurance;
    }

    abstract float run(int distance);
    abstract float swim(int distance);
    abstract void info();
}
