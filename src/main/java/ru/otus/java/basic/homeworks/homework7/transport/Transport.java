package ru.otus.java.basic.homeworks.homework7.transport;

import ru.otus.java.basic.homeworks.homework7.localities.Locality;

public interface Transport {
    public boolean move(Locality locality, int distance);
    public String getType();
}
