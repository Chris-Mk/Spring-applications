package com.mkolongo.residentEvil.domain.entities;

public enum Creator {
    CORP("Corp"),
    MORT("Mort");

    private final String name;

    Creator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
