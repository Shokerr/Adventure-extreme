package com.adventure.Entities;

import com.adventure.GameLogic.Combinator;
import com.adventure.GameLogic.Combo;
import com.adventure.GameLogic.Direction;

import java.util.Map;

public class Player {

    private Location currentLocation;

    public Inventory getInventory() {
        return inventory;
    }

    private Inventory inventory;

    public Player(Location location) {
        inventory = new Inventory();
        this.currentLocation = location;
    }

    public void go(String direction) {
        Location loc;
        switch (direction) {
            case "верх":
                loc = currentLocation.getPaths().get(Direction.UP);
                break;
            case "вниз":
                loc = currentLocation.getPaths().get(Direction.DOWN);
                break;
            case "север":
                loc = currentLocation.getPaths().get(Direction.NORTH);
                break;
            case "юг":
                loc = currentLocation.getPaths().get(Direction.SOUTH);
                break;
            case "запад":
                loc = currentLocation.getPaths().get(Direction.WEST);
                break;
            case "восток":
                loc = currentLocation.getPaths().get(Direction.EAST);
                break;
            default:
                loc = null;
        }
        if (loc != null) {
            for (Direction dir : Direction.values()) {
                if (dir.getName().equalsIgnoreCase(direction)) {
                    if (checkExistenceDirection(dir, currentLocation.getPaths())) {
                        this.currentLocation = currentLocation.getPaths().get(dir);
                        System.out.println("\nВы пришли в локацию - " + currentLocation.getName() + "\n" + currentLocation.getDescription());
                    }
                }
            }
        } else System.out.println("Там ничего нет...");
    }

    public void use(String object, String subject, Combinator combinator) {
        Item first = inventory.getItem(object);
        Item second;
        if (inventory.getItems().contains(inventory.getItem(subject))) {
            second = inventory.getItem(subject);
        } else if (currentLocation.getItems().getItems().contains(currentLocation.getItems().getItem(subject))) {
            second = currentLocation.getItems().getItem(subject);
        } else {
            second = null;
        }

        if (first == null && second == null) {
            System.out.println("предмет " + subject + " не найден");
        } else {
            Combo combo = combinator.findCombo(first, second);
            if (combo != null) {
                this.inventory.put(combo.getResult());
                this.inventory.remove(first);
                if (second != null && second.isMobility()) {
                    this.inventory.remove(second);
                }
            } else System.out.println("такой комбинации не нашлось");
        }
    }

    public void lookInventory() {
        System.out.println(inventory);
    }

    public void lookAround() {
        System.out.println("В локации " + currentLocation.getName() + " находятся: " + currentLocation.getItems());
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void pickUp(String itemName) {
        Item item = currentLocation.getItems().getItem(itemName);
        if (currentLocation.getItems().getItems().contains(item)) {
            if (currentLocation.getItems().getItem(itemName).isMobility()) {
                inventory.put(item);
                currentLocation.getItems().remove(item);
            } else System.out.println("Данный предмет нельзя положить в свой инвентарь");
        } else System.out.println("в данной локации этого нет...");
    }

    private boolean checkExistenceDirection(Direction direction, Map<Direction, Location> paths) {
        if (!currentLocation.getName().equals(paths.get(direction).getName()) && paths.containsKey(direction)) {
            System.out.println(direction.getName());
            return true;
        } else {
            System.out.println("\nТам ничего нет...\n");
            return false;
        }
    }

}
