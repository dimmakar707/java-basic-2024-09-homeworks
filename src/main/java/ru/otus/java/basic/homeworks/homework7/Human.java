package ru.otus.java.basic.homeworks.homework7;

import ru.otus.java.basic.homeworks.homework7.localities.Locality;
import ru.otus.java.basic.homeworks.homework7.transport.Transport;

public class Human {
    private String name;
    private Transport currentTransport;
    private boolean isOnTransport;
    private int power;

    public String getName() {
        return name;
    }

    public Transport getCurrentTransport() {
        return currentTransport;
    }

    public int getPower() {
        return power;
    }

    public Human(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public void seatOnTransport(Transport transport) {
        System.out.println(name + " сел в транспорт: " + transport.getType());
        currentTransport = transport;
        isOnTransport = true;
    }

    public void leaveTransport() {
        if(isOnTransport) {
            System.out.println(name + " вышел из транспорта: " + currentTransport.getType());
            currentTransport = null;
            isOnTransport = false;
        }
    }

    public boolean go(Locality locality, int distance) {
        if(isOnTransport) {
            return currentTransport.move(locality, distance);
        }
        if(distance > power) {
            System.out.println("Человек не может пройти дистанцию " + distance + " км, недостаточно сил: " + power);
            return false;
        }
        power -= distance;
        System.out.println("Человек идет пешком по местности: " + locality.getType() + ", дистанцию " + distance + " км");
        return true;
    }
}
