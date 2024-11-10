package ru.otus.java.basic.homeworks.homework5.animals;

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

    public float run(int distance) {
        if(endurance < distance * endurancePerMeterOfRun) {
            System.out.println(name + " не может пробежать дистанцию " + distance + " метров. Недостаточно выносливости. Животное устало.");
            isTired = true;
            return -1;
        }
        endurance -= distance * endurancePerMeterOfRun;
        float result = distance / runSpeed;
        System.out.println(name + " пробежал(а) дистанцию " + distance + " метров за " + result + " секунд.");
        return result;
    }

    public float swim(int distance) {
        if(endurance < distance * endurancePerMeterOfSwim) {
            System.out.println(name + " не может проплыть дистанцию " + distance + " метров. Недостаточно выносливости. Животное устало.");
            isTired = true;
            return -1;
        }
        endurance -= distance * endurancePerMeterOfSwim;
        float result = distance / swimSpeed;
        System.out.println(name + " проплыл(а) дистанцию " + distance + " метров за " + result + " секунд.");
        return result;
    }

    public void info() {
        String state = isTired ? "устал(а)" : "не устал(а)";
        System.out.println(name + " " + state + ". Запас выносливости: " + endurance + " единиц.");
    }
}
