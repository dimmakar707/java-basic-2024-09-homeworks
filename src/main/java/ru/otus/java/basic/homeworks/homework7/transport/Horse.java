package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.LocalityType;

import java.util.ArrayList;
import java.util.List;

public class Horse implements Transport {

    private int power;
    private List<LocalityType> noRoadObstacles = new ArrayList<>();

    public int getPower() {
        return power;
    }

    public String getType() {
        return "лошадь";
    }

    public Horse(int power) {
        this.power = power;
        noRoadObstacles.add(LocalityType.SWAMP);
    }

    public boolean move(LocalityType locality, int distance) {
        if(power >= distance) {
            if(noRoadObstacles.contains(locality)) {
                System.out.println("Лошадь не может скакать по местности " + locality.getType());
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
