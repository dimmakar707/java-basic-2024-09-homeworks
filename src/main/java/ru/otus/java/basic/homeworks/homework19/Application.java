package ru.otus.java.basic.homeworks.homework19;

public class Application {
    public static void main(String[] args) {
        Apple apple1 = new Apple(0.1);
        Apple apple2 = new Apple(0.2);
        Apple apple3 = new Apple(0.3);
        Apple apple4 = new Apple(0.4);
        Apple apple5 = new Apple(0.5);
        Apple apple6 = new Apple(0.7);

        Orange orange1 = new Orange(0.1);
        Orange orange2 = new Orange(0.2);
        Orange orange3 = new Orange(0.3);
        Orange orange4 = new Orange(0.4);
        Orange orange5 = new Orange(0.5);
        Orange orange6 = new Orange(0.7);

        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();

        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        Box<Fruit> fruitBox1 = new Box<>();

        appleBox1.add(apple1);
        appleBox1.add(apple3);
        appleBox1.add(apple6);

        appleBox2.add(apple2);
        appleBox2.add(apple4);
        appleBox2.add(apple5);

        orangeBox1.add(orange1);
        orangeBox1.add(orange3);
        orangeBox1.add(orange6);

        orangeBox2.add(orange2);
        orangeBox2.add(orange4);
        orangeBox2.add(orange5);

        System.out.println(appleBox1.weight());
        System.out.println(appleBox2.weight());
        System.out.println(appleBox1.compare(appleBox2));

        System.out.println(orangeBox1.weight());
        System.out.println(orangeBox2.weight());
        System.out.println(orangeBox1.compare(orangeBox2));

        System.out.println(orangeBox1.compare(appleBox2));

        appleBox1.moveFruitsToAnotherBox(appleBox2);
        appleBox1.moveFruitsToAnotherBox(fruitBox1);
        orangeBox1.moveFruitsToAnotherBox(fruitBox1);

    }
}
