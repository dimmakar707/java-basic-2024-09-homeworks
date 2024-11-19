package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.Locality;

public class AllTerrainVehicle implements Transport {

    private int fuel;

    public int getFuel() {
        return fuel;
    }

    public String getType() {
        return "вездеход";
    }

    public AllTerrainVehicle(int fuel) {
        this.fuel = fuel;
    }

    public boolean move(Locality locality, int distance) {
        if(fuel >= distance) {
            fuel -= distance;
            System.out.println("Вездеход едет по местности: " + locality.getType() + ", дистанцию " + distance + " км");
            return true;
        }
        System.out.println("Вездеход не может проехать дистанцию " + distance + ". Недостаточно топлива: " + fuel);
        return false;
    }
}
