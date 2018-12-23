package com.adventure.Entities;

import com.adventure.GameLogic.Direction;

import java.util.Map;

public class Location {

    private String name;
    private String description;
    private Inventory inventory;
    private Map<Direction, Location> paths;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setPaths(Map<Direction, Location> paths) {
        this.paths = paths;
    }

    public Map<Direction, Location> getPaths() {
        return paths;
    }

    public Inventory getItems() {
        return inventory;
    }

}
