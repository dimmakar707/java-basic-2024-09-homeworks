package ru.otus.java.basic.homeworks.homework19;

import ru.otus.java.basic.homeworks.homework19.fruits.Fruit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public double weight() {
        double weight = 0.0;
        for (int i = 0; i < fruits.size(); i++) {
            weight += fruits.get(i).getWeight();
        }
        return weight;
    }

    public boolean compare(Box anotherBox) {
        return Math.abs(this.weight() - anotherBox.weight()) < 0.0001;
    }

    public void moveFruitsToAnotherBox(Box<? super T> anotherBox) {
        Iterator<T> iterator = fruits.iterator();
        while(iterator.hasNext()) {
            T fruit = iterator.next();
            anotherBox.add(fruit);
            iterator.remove();
        }
    }
}
