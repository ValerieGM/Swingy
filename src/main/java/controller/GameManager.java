package controller;

import model.helpers.View;
import model.entities.demons.Demon;
import view.Console;
import view.Display;
import model.database.Database;

import static controller.EntityCreator.newDemon;
import static model.helpers.Universal.*;

import java.util.Random;
import java.util.Scanner;

public class GameManager {

    private static int[] oldMove = new int[2];

    public static void win() {
        if (angel.getX() == squareMap.getMapSize() - 1 || angel.getY() == squareMap.getMapSize() - 1 ||
                angel.getX() == 0 || angel.getY() == 0) {
            View.print("Quest Complete!!!");
            double xpSum = angel.getLevel() * 1000 + Math.pow(angel.getLevel() - 1, 2) * 450;

            int xp = (int) Math.round(xpSum);
            angel.setXp(xp);
            squareMap = MapCreator.generateMap(angel);
            if (!bIsGUI)
                Console.moveAngel();
        }
    }

    public static void battleOrFlee() {
        if (!bIsGUI)
            Display.displaySurvival();
        Scanner input = new Scanner(System.in);
        if (!bIsGUI) {
            while (input.hasNextLine()) {
                String takeIn = input.nextLine();
                int v  = Integer.parseInt(takeIn);
                if (v == 1 || v == 2) {
                    if (v == 1) {
                        battle(2);
                        return;
                    } else {
                        flee();
                        return;
                    }
                } else {
                    View.print("Choose what is given, not what you desire!!!");
                    Display.displaySurvival();
                }
            }
        } else
            bEncounterPhase = true;
    }

    public static void battle(int v) {
        if (v == 1 ) {
            View.print(demon.getName() + " attacks!!");
            while (angel.getHp() > 0 && demon.getHp() > 0) {
                demon.attack(angel);
                demon.attack(angel);
                if (angel.getHp() > 0)
                    angel.attack(demon);
            }
        } else {
            View.print(angel.getName() + " attacks!!");
            while (angel.getHp() > 0 && demon.getHp() > 0) {
                angel.attack(demon);
                angel.attack(demon);
                if (demon.getHp() > 0)
                    demon.attack(angel);
            }
        }

        if (angel.getHp() <= 0) {
            View.print("Quest Failed!!!");
            if (!bIsGUI)
                Console.begin();
        }
        else if (demon.getHp() <= 0) {
            Database.getInstance().updateAngel(angel);
            angel.setPosition(0, 0);
            View.print("Quest Successfully Completed!!!!");
        }
    }

    public static void flee() {
        int rand = new Random().nextInt(2);
        if (rand == 1) {
            View.print("Nowhere To Run!!!");
            battle(1);
        } else if (rand == 2) {
            View.print("Cowardice!!!");
            angel.setPosition(oldMove[0] * -1, oldMove[1] * -1);
        }
        bFightPhase = false;
    }

    public static void move(int way) {
        int north = 1;
        int east = 2;
        int south = 3;
        int west = 4;

        if (way == north) { //-10
            angel.setPosition(-1, 0);
            oldMove[0] = -1;
            oldMove[1] = 0;
        } else if (way == east) { //01
            angel.setPosition(0, 1);
            oldMove[0] = 0;
            oldMove[1] = 1;
        } else if (way == south) { //10
            angel.setPosition(1, 0);
            oldMove[0] = 1;
            oldMove[1] = 0;
        } else if (way == west) { //0-1
            angel.setPosition(0, -1);
            oldMove[0] = 0;
            oldMove[1] = -1;
        }

        // Demon encounter
        if (squareMap.getMap()[angel.getX()][angel.getY()] == 8) {
            bFightPhase = true;
            int ran = new Random().nextInt(3);
            if (ran == 3)
                demon = (Demon) newDemon(angel, "Dracula");
            else
                demon = (Demon) newDemon(angel, "Lilith");
            View.print("Demon Approached:: " + demon.getName() + " of level " + demon.getLevel());
            battleOrFlee();
        }
    }
}