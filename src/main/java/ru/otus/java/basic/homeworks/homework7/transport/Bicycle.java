package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.Locality;
import ru.otus.java.basic.homeworks.homework7.localities.LocalityType;
import ru.otus.java.basic.homeworks.homework7.localities.Swamp;

import java.util.ArrayList;
import java.util.List;

public class Bicycle implements Transport {

    private List<LocalityType> noRoadObstacles = new ArrayList<>();

    public Bicycle() {
        noRoadObstacles.add(LocalityType.SWAMP);
    }

    public boolean move(LocalityType locality, int distance) {
        if(noRoadObstacles.contains(locality)) {
            System.out.println("Велосипед не может ехать по местности " + locality.getType());
            return false;
        }
        System.out.println("Велосипед едет по местности: " + locality.getType() + ", дистанцию " + distance + " км");
        return true;
    }

    public String getType() {
        return "велосипед";
    }
}
