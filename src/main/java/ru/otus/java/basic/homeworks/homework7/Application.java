package ru.otus.java.basic.homeworks.homework7;

import ru.otus.java.basic.homeworks.homework7.localities.Forest;
import ru.otus.java.basic.homeworks.homework7.localities.Locality;
import ru.otus.java.basic.homeworks.homework7.localities.Plain;
import ru.otus.java.basic.homeworks.homework7.localities.Swamp;
import ru.otus.java.basic.homeworks.homework7.transport.*;

import java.util.Random;

public class Application {
    public static void main(String[] args) {
        Human human = new Human("Дмитрий", 15);

        Locality[] localities = {
            new Forest(),
            new Plain(),
            new Swamp(),
            new Plain(),
            new Forest(),
            new Swamp(),
            new Forest(),
            new Swamp(),
            new Plain(),
            new Swamp()
        };

        Transport[] transports = {
            new Car((int)(Math.random() * 50)),
            new Bicycle(),
            new Horse((int)(Math.random() * 50)),
            new AllTerrainVehicle((int)(Math.random() * 50))
        };

        for (Locality locality : localities) {
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
