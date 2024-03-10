package com.roadest.roadproredact.borders;

public enum BorderType {
    Solid("Сплошная"),
    DoubleSolid("Двойная сплошная"),
    Dotted("Прерывистая");
    private final String name;
    BorderType(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
