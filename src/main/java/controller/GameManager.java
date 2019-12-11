package controller;

import model.database.Database;
import view.Console;
import view.Display;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import static model.Universal.*;

public class GameManager {
    private static int north = 1;
    private static int east = 2;
    private static int south = 3;
    private static int west = 4;

    private static int[] oldMove = new int[2];

    public static void win() {
        if (angel.getX() == squareMap.getMapSize() - 1 || angel.getY() == squareMap.getMapSize() - 1 ||
                angel.getX() == 0 || angel.getY() == 0) {
            System.out.println("Quest Complete!!!");
            squareMap = MapFactory.generateMap(angel);
            //check what this section is for
            if (!bIsGUI)
                Console.moveAngel();
        }
    }

    public static void battleOrFlee() throws SQLException {
        if (!bIsGUI)
            Display.displaySurvival();
        Scanner input = new Scanner(System.in);
        if (!bIsGUI) {
            while (input.hasNextLine()) {
                String takeIn = input.nextLine();
                if (takeIn.equals(1) || takeIn.equals(2)) {
                    if (takeIn.equals(1)) {
                        battle(2);
                        return;
                    } else {
                        flee();
                        return;
                    }
                } else {
                    System.out.println("Choose what is given, not what you desire!!!");
                    Display.displaySurvival();
                }
            }
        } else bEncounterPhase = true; //experiment with that too
    }

    public static void battle(int v) throws SQLException {
        if (v == 1 ) {
            while (angel.getHp() > 0 && demon.getHp() > 0) {
                demon.attack(angel);
                //demon.attack(angel);
                if (angel.getHp() > 0)
                    angel.attack(demon);
            }
        } else {
            while (angel.getHp() > 0 && demon.getHp() > 0) {
                angel.attack(demon);
                angel.attack(demon);
                if (demon.getHp() > 0)
                    demon.attack(angel);
            }
        }

        if (angel.getHp() <= 0) {
            System.out.println("Quest Failed!!!");
            if (!bIsGUI)
                Console.begin();
        }
        else if (demon.getHp() <= 0) {
            Database.getInstance().updateAngel(angel);
            angel.setPosition(0, 0);
            System.out.println("" +
                    "Quest Success!!!!");
        }
    }

    public static void flee() throws SQLException {
        int rand = new Random().nextInt(2);
        if (rand == 1) {
            System.out.println("Nowhere To Run!!!");
            battle(1);
        } else if (rand == 2) {
            System.out.println("Cowardice!!!");
            angel.setPosition(oldMove[0] * -1, oldMove[1] * -1); //experiment with that
        }
        bFightPhase = false;
    }

    public static void move(int way) {
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

        // battle or flee
        if (squareMap.getMap()[angel.getX()][angel.getY()] == 8) {
        }
    }
}