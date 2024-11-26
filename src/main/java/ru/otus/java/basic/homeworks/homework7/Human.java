package ru.otus.java.basic.homeworks.homework7;

import ru.otus.java.basic.homeworks.homework7.localities.LocalityType;
import ru.otus.java.basic.homeworks.homework7.transport.Transport;

public class Human {
    private String name;
    private Transport currentTransport;
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
        if(currentTransport == null) {
            System.out.println(name + " сел в транспорт: " + transport.getType());
            currentTransport = transport;
            return;
        }
        System.out.println(name + " не может сесть в транспорт " + transport.getType() + ", т.к. он уже в транспорте: " + currentTransport.getType());
    }

    public void leaveTransport() {
        if(currentTransport != null) {
            System.out.println(name + " вышел из транспорта: " + currentTransport.getType());
            currentTransport = null;
        }
    }

    public boolean go(LocalityType locality, int distance) {
        if(currentTransport != null) {
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
