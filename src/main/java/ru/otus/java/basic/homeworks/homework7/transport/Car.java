package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.LocalityType;

import java.util.ArrayList;
import java.util.List;

public class Car implements Transport {
    private int fuel;
    private List<LocalityType> noRoadObstacles = new ArrayList<>();


    public int getFuel() {
        return fuel;
    }

    public String getType() {
        return "машина";
    }

    public Car(int fuel) {
        this.fuel = fuel;
        noRoadObstacles.add(LocalityType.FOREST);
        noRoadObstacles.add(LocalityType.SWAMP);
    }

    public boolean move(LocalityType locality, int distance) {

        if(fuel >= distance) {
            if(noRoadObstacles.contains(locality)) {
                System.out.println("Машина не может ехать по местности: " + locality.getType());
                return false;
            }
            fuel -= distance;
            System.out.println("Машина едет по местности: " + locality.getType() + ", дистанцию " + distance + " км");
            return true;
        }
        System.out.println("Машина не может проехать дистанцию " + distance + ". Недостаточно топлива: " + fuel);
        return false;
    }
}
