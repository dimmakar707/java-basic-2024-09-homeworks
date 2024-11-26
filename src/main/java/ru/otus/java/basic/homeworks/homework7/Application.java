package ru.otus.java.basic.homeworks.homework7;

import ru.otus.java.basic.homeworks.homework7.localities.*;
import ru.otus.java.basic.homeworks.homework7.transport.*;

import java.util.Random;

public class Application {
    public static void main(String[] args) {
        Human human = new Human("Дмитрий", 15);

        LocalityType[] localities = {
            LocalityType.FOREST,
            LocalityType.PLAIN,
            LocalityType.SWAMP,
            LocalityType.PLAIN,
            LocalityType.FOREST,
            LocalityType.SWAMP,
            LocalityType.FOREST,
            LocalityType.SWAMP,
            LocalityType.PLAIN,
            LocalityType.SWAMP,
        };

        Transport[] transports = {
            new Car((int)(Math.random() * 50)),
            new Bicycle(),
            new Horse((int)(Math.random() * 50)),
            new AllTerrainVehicle((int)(Math.random() * 50))
        };

        for (LocalityType locality : localities) {
            Random random = new Random();
            if(random.nextBoolean()) {
                human.seatOnTransport(transports[random.nextInt(transports.length)]);
            } else {
                human.leaveTransport();
            }
            human.go(locality, (int)(Math.random() * 10 + 1));
        }

    }
}
