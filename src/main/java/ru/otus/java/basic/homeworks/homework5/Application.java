package ru.otus.java.basic.homeworks.homework5;

import ru.otus.java.basic.homeworks.homework5.animals.Cat;
import ru.otus.java.basic.homeworks.homework5.animals.Dog;
import ru.otus.java.basic.homeworks.homework5.animals.Horse;

public class Application {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", 2, 0, 20);
        Dog dog = new Dog("Sharik", 3, 2, 35);
        Horse horse = new Horse("Bucifal", 6, 3, 70);

        cat.run(15);
        cat.swim(10);
        cat.info();
        cat.run(10);
        cat.info();

        System.out.println("------------");

        dog.run(25);
        dog.info();
        dog.swim(5);
        dog.info();
        dog.run(30);
        dog.info();

        System.out.println("------------");

        horse.run(50);
        horse.info();
        horse.swim(10);
        horse.info();

    }
}
