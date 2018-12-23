package com.adventure.GameLogic;

public enum Direction {
    NORTH("север"),
    SOUTH("юг"),
    WEST("запад"),
    EAST("восток"),
    UP("верх"),
    DOWN("вниз");

    public String getName() {
        return name;
    }

    private String name;

    Direction(String name) {
        this.name = name;
    }
}
