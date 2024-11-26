package ru.otus.java.basic.homeworks.homework7.localities;

public enum LocalityType {

    FOREST("лес"), PLAIN("равнина"), SWAMP("болото");
    
    private String type;

    public String getType() {
        return type;
    }

    LocalityType(String type) {
        this.type = type;
    }
}
