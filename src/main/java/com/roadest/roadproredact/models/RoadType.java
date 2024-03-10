package com.roadest.roadproredact.models;

public enum RoadType {
    GROUND("Грунт"),
    ASPHALT("Асфальт");
    private final String name;
    RoadType(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
