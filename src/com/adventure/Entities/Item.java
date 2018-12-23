package com.adventure.Entities;

import com.adventure.GameLogic.Mobility;

public class Item {
    private String name;
    private String description;
    private Mobility mobility;

    public String getName() {
        return name;
    }

    public Item(String name, String description, Mobility mobility) {
        this.name = name;
        this.description = description;
        this.mobility = mobility;
    }

    public boolean isMobility() {
        return mobility.equals(Mobility.MOBILE);
    }

    @Override
    public String toString() {
        return "\n Название='" + name + '\'' + "\n" +
                " Описание='" + description + '\'' + "\n" +
                " Подвижность=" + mobility + "\n";
    }
}
