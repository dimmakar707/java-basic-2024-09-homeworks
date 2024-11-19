package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.Locality;
import ru.otus.java.basic.homeworks.homework7.localities.Swamp;

public class Bicycle implements Transport {
    public boolean move(Locality locality, int distance) {
        if(locality instanceof Swamp) {
            System.out.println("Велосипед не может ехать по болоту");
            return false;
        }
        System.out.println("Велосипед едет по местности: " + locality.getType() + ", дистанцию " + distance + " км");
        return true;
    }

    public String getType() {
        return "велосипед";
    }
}
