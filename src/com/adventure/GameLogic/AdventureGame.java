package com.adventure.GameLogic;

import com.adventure.Entities.Item;
import com.adventure.Entities.Location;
import com.adventure.Entities.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGame {

    private Player player;
    private Combinator combinator;

    public AdventureGame() {
        initiateTheWorld();
    }

    private void initiateTheWorld() {
        Item wizard = new Item("волшебник", "спящий(пьяный в стельку) волшебник", Mobility.STATIONARITY);
        Item bucket = new Item("ведро", "пустое ведро", Mobility.MOBILE);
        Item whiskey = new Item("виски", "начатая бутылка виски...видимо волшебник не осилил", Mobility.MOBILE);
        Item frog = new Item("лягушка", "сидела во дворе лягушка и никого не трогала", Mobility.MOBILE);
        Item chain = new Item("цепь", "во дворе нашлась достаточно крепкая цепь", Mobility.MOBILE);
        Item waterPit = new Item("колодец", "во дворе нашелся действующий колодец", Mobility.STATIONARITY);
        Item welding = new Item("сварка", "сварочный аппарат", Mobility.STATIONARITY);
        Item bucketWithChain = new Item("ведро", "в ведре лежит цепь", Mobility.MOBILE);
        Item weldBucket = new Item("ведро", "ведро с приваренной цепью", Mobility.MOBILE);
        Item waterWeldBucket = new Item("ведро", "ведро с водой", Mobility.MOBILE);
        Item crystal = new Item("кристалл", "волшебник дал Вам кристалл", Mobility.MOBILE);

        Location livingRoom = new Location("спальня", "спальня, в которой спит волшебник");
        Location attic = new Location("чердак", "фууу, тут все в паутине и тут темно...");
        Location garden = new Location("сад", "около дома растет яблоневый сад");

        garden.getItems().getItems().add(chain);
        garden.getItems().getItems().add(waterPit);
        garden.getItems().getItems().add(frog);

        attic.getItems().getItems().add(welding);

        player = new Player(livingRoom);
        player.setCurrentLocation(livingRoom);

        livingRoom.getItems().getItems().add(whiskey);
        livingRoom.getItems().getItems().add(wizard);
        livingRoom.getItems().getItems().add(bucket);

        Map<Direction, Location> gardenPaths = new HashMap<>();
        gardenPaths.put(Direction.WEST, livingRoom);
        garden.setPaths(gardenPaths);

        Map<Direction, Location> livingRoomPaths = new HashMap<>();
        livingRoomPaths.put(Direction.EAST, garden);
        livingRoomPaths.put(Direction.UP, attic);
        livingRoom.setPaths(livingRoomPaths);

        Map<Direction, Location> atticPaths = new HashMap<>();
        atticPaths.put(Direction.DOWN, livingRoom);
        attic.setPaths(atticPaths);

        combinator = new Combinator();
        combinator.addCombo(bucket, chain, bucketWithChain, "Вы получили ведро с цепью");
        combinator.addCombo(bucketWithChain, welding, weldBucket, "вы приварили цепь к ведру");
        combinator.addCombo(weldBucket, waterPit, waterWeldBucket, "наполненное водой ведро");
        combinator.addCombo(waterWeldBucket, wizard, crystal, "Вы разбудили волшебника и он дал Вам кристалл!");

    }

    public void play() {
        boolean isGame = true;
        String[] commands;
        System.out.println("Приветствуем в игре 'ЭКСТРИМАЛЬНЫЕ ПРИКЛЮЧЕНИЯ'");
        System.out.println("цель игры - разбудить волшебника и получить кристалл");
        Scanner scan = new Scanner(System.in);
        while (isGame) {

            System.out.println("Введите команду: ");
            commands = scan.nextLine().toLowerCase().split(" ");

            switch (commands[0]) {
                case "осмотреться":
                    player.lookAround();
                    break;
                case "инвентарь":
                    player.lookInventory();
                    break;
                case "идти":
                    if (commands.length == 2) {
                        player.go(commands[1]);
                    }
                    break;
                case "взять":
                    if (commands.length == 2) {
                        player.pickUp(commands[1]);
                    }
                    break;
                case "использовать":
                    if (commands.length == 3) {
                        player.use(commands[1], commands[2], combinator);
                    }
                    break;
                case "да":
                    new AdventureGame().play();
                    break;
                case "нет":
                    isGame = false;
                    System.out.println("Спасибо за игру!");
                    break;
                    default:
                        System.out.println("команда не найдена");
            }

            if(player.getInventory().getItems().contains(player.getInventory().getItem("кристалл"))&& isGame){
                player.lookInventory();
                System.out.println("Поздравляем, вы прошли игру!");
                System.out.println("Начать новую игру? да/нет");
            }

        }
    }

}
