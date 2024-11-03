package ru.otus.java.basic.homeworks.homework4;

public class Box {
    private int length;
    private int width;
    private int height;
    private String color;
    private boolean isOpened;
    private String item;

    public void setColor(String color) {
        this.color = color;
        System.out.println("Коробка перекрашена в " + color + " цвет.");
    }

    public void open() {
        isOpened = true;
        System.out.println("Коробка открыта");
    }

    public void close() {
        isOpened = false;
        System.out.println("Коробка закрыта");
    }

    public void putInBox(String item) {
        if(isOpened) {
            if(this.item == null) {
                this.item = item;
                System.out.println("В коробку положили предмет: " + item);
            } else {
                System.out.println("В коробку нельзя положить " + item + ", т.к. она занята. В ней лежит " + this.item);
            }
        } else {
            System.out.println("Нельзя положить " + item + ", потому что коробка закрыта");
        }
    }

    public void removeFromBox() {
        if(isOpened) {
            if(this.item == null) {
                System.out.println("Нельзя выкинуть, в коробке ничего нет");
            } else {
                System.out.println("Из коробки выкинули предмет:" + this.item);
                this.item = null;
            }
        } else {
            System.out.println("Нельзя выкинуть предмет, т.к. коробка закрыта");
        }
    }

    public void info() {
        String state = isOpened ? "открыта" : "закрыта";
        String filled = item == null ? "пусто" : "лежит " + item;
        System.out.println("Коробка имеет размеры: длина - " + length + ", ширина - " + width + ", высота - " + height + ". Цвет коробки - " + color + ". Коробка " + state + ". В коробке " + filled);
    }

    public Box(int length, int width, int height, String color, boolean isOpened) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.isOpened = isOpened;
    }
}
