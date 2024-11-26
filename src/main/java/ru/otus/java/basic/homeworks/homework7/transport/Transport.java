package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.LocalityType;

public interface Transport {
    public boolean move(LocalityType locality, int distance);
    public String getType();
}
