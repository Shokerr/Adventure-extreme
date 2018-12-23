package com.adventure.Entities;

import java.util.*;

public class Inventory {
    private List<Item> items = new ArrayList<>();

    public void put(Item item) {
        items.add(item);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (itemName.equalsIgnoreCase(item.getName())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "\n" + items;
    }
}
