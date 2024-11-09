package ru.otus.java.basic.homeworks.homework5;

public class Horse extends Animal {
    public Horse(String name, float runSpeed, float swimSpeed, float endurance) {
        super(name, runSpeed, swimSpeed, endurance);
        this.endurancePerMeterOfRun = 1;
        this.endurancePerMeterOfSwim = 4;
    }

    public float run(int distance) {
        if(endurance < distance * endurancePerMeterOfRun) {
            System.out.println("Лошадь " + name + " не может пробежать дистанцию " + distance + " метров. Недостаточно выносливости. Лошадь устала.");
            isTired = true;
            return -1;
        }
        endurance -= distance * endurancePerMeterOfRun;
        float result = distance / runSpeed;
        System.out.println("Лошадь " + name + " пробежала дистанцию " + distance + " метров за " + result + " секунд.");
        return result;
    }

    public float swim(int distance) {
        if(endurance < distance * endurancePerMeterOfSwim) {
            System.out.println("Лошадь " + name + " не может проплыть дистанцию " + distance + " метров. Недостаточно выносливости. Лошадь устала.");
            isTired = true;
            return -1;
        }
        endurance -= distance * endurancePerMeterOfSwim;
        float result = distance / swimSpeed;
        System.out.println("Лошадь " + name + " проплыла дистанцию " + distance + " метров за " + result + " секунд.");
        return result;
    }

    public void info() {
        String state = isTired ? "устала" : "не устала";
        System.out.println("Лошадь " + name + " " + state + ". Запас выносливости: " + endurance + " единиц.");
    }
}
