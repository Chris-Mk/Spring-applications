package com.mkolongo.residentEvil.domain.entities;

public enum Magnitude {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String name;

    Magnitude(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
