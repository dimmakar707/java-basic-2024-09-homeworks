package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.Locality;
import ru.otus.java.basic.homeworks.homework7.localities.Swamp;

public class Horse implements Transport {

    private int power;

    public int getPower() {
        return power;
    }

    public String getType() {
        return "лошадь";
    }

    public Horse(int power) {
        this.power = power;
    }

    public boolean move(Locality locality, int distance) {
        if(power >= distance) {
            if(locality instanceof Swamp) {
                System.out.println("Лошадь не может скакать по болоту");
                return false;
            }
            power -= distance;
            System.out.println("Лошадь скачет по местности: " + locality.getType() + ", дистанцию " + distance + " км");
            return true;
        }
        System.out.println("Лошадь не может пройти дистанцию " + distance + ". Недостаточно сил: " + power);
        return false;
    }


}
