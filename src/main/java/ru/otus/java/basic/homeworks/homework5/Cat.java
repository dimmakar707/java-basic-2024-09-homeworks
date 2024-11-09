package ru.otus.java.basic.homeworks.homework5;

public class Cat extends Animal {

    public Cat(String name, float runSpeed, float swimSpeed, float endurance) {
        super(name, runSpeed, swimSpeed, endurance);
        this.endurancePerMeterOfRun = 1;
    }

    public float run(int distance) {
        if(endurance < distance * endurancePerMeterOfRun) {
            System.out.println("Кот " + name + " не может пробежать дистанцию " + distance + " метров. Недостаточно выносливости. Кот устал");
            isTired = true;
            return -1;
        }
        endurance -= distance * endurancePerMeterOfRun;
        float result = distance / runSpeed;
        System.out.println("Кот " + name + " пробежал дистанцию " + distance + " метров за " + result + " секунд.");
        return result;
    }

    public float swim(int distance) {
        System.out.println("Коты плавать не умеют");
        return -1;
    }

    public void info() {
        String state = isTired ? "устал" : "не устал";
        System.out.println("Кот " + name + " " + state + ". Запас выносливости: " + endurance + " единиц.");
    }
}
