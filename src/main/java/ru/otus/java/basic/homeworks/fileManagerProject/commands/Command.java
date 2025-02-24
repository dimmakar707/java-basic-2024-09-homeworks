package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import java.io.IOException;

public interface Command {
    public String run(String currentLocation, String[] commandArgs) throws IOException;
}
