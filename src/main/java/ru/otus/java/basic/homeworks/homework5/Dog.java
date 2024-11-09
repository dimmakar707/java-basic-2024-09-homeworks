package ru.otus.java.basic.homeworks.homework5;

public class Dog extends Animal {

    public Dog(String name, float runSpeed, float swimSpeed, float endurance) {
        super(name, runSpeed, swimSpeed, endurance);
        this.endurancePerMeterOfRun = 1;
        this.endurancePerMeterOfSwim = 2;
    }

    public float run(int distance) {
        if(endurance < distance * endurancePerMeterOfRun) {
            System.out.println("Собака " + name + " не может пробежать дистанцию " + distance + " метров. Недостаточно выносливости. Собака устала.");
            isTired = true;
            return -1;
        }
        endurance -= distance * endurancePerMeterOfRun;
        float result = distance / runSpeed;
        System.out.println("Собака " + name + " пробежала дистанцию " + distance + " метров за " + result + " секунд.");
        return result;
    }

    public float swim(int distance) {
        if(endurance < distance * endurancePerMeterOfSwim) {
            System.out.println("Собака " + name + " не может проплыть дистанцию " + distance + " метров. Недостаточно выносливости. Собака устала.");
            isTired = true;
            return -1;
        }
        endurance -= distance * endurancePerMeterOfSwim;
        float result = distance / swimSpeed;
        System.out.println("Собака " + name + " проплыла дистанцию " + distance + " метров за " + result + " секунд.");
        return result;
    }

    public void info() {
        String state = isTired ? "устала" : "не устала";
        System.out.println("Собака " + name + " " + state + ". Запас выносливости: " + endurance + " единиц.");
    }

}
