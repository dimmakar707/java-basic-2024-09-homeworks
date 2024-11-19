package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.Forest;
import ru.otus.java.basic.homeworks.homework7.localities.Locality;
import ru.otus.java.basic.homeworks.homework7.localities.Swamp;

public class Car implements Transport {
    private int fuel;

    public int getFuel() {
        return fuel;
    }

    public String getType() {
        return "машина";
    }

    public Car(int fuel) {
        this.fuel = fuel;
    }

    public boolean move(Locality locality, int distance) {
        if(fuel >= distance) {
            if(locality instanceof Forest) {
                System.out.println("Машина не может ехать по густому лесу");
                return false;
            }
            if(locality instanceof Swamp) {
                System.out.println("Машина не может ехать по болоту");
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
