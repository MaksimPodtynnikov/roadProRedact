package com.roadest.roadproredact.signs;

public enum SignType {
    STOP("Стоп"),
    AWARE("Уступи дорогу"),
    MAIN("Главная"),
    SPEED("Скорость");
    private final String name;
    SignType(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
