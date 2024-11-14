package ru.otus.java.basic.homeworks.homework6;

public class Application {

    public static void main(String[] args) {

        Cat[] cats = {
            new Cat("Барсик", 3),
            new Cat("Мурзик", 4),
            new Cat("Рыжик", 5),
            new Cat("Чук", 2),
            new Cat("Маруся", 3),
        };

        Plate plate = new Plate(10);

        for(Cat cat : cats) {
            cat.eat(plate);
        }

        for(Cat cat : cats) {
            cat.info();
        }

        plate.info();

        System.out.println("-------------------");

        //Второй прогон, когда насыпаем еду в тарелку, каждому коту, которому не хватило еды
        plate.addFood(plate.maxCapacity - plate.currentCapacity);

        plate.info();

        for(Cat cat : cats) {
            cat.eat(plate);
            if(!cat.isHappy) {
                plate.addFood(plate.maxCapacity - plate.currentCapacity);
                cat.eat(plate);
            }
        }

        for(Cat cat : cats) {
            cat.info();
        }

    }
}
