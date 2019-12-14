package view;

import java.util.*;

import controller.EntityFactory;
import controller.GameManager;
import controller.MapFactory;
import model.database.Database;
import model.entities.angels.Angel;

import static model.Universal.*;
import static view.Display.*;

public class Console {

    public static void begin() {
        bIsGUI = false;
        System.out.println("******Console View******");
        displayMenu();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takeIn = input.nextLine();
            Integer v = Integer.parseInt(takeIn);
            if (v.equals(1) || v.equals(2) || v.equals(3)) {
                if (v == 1) {
                    //create hero
                    createAngel();
                    break;
                }
                else if (v == 2) {
                    //select hero;
                    selectAngel();
                    break;
                }
                else if (v == 3) {
                    bIsGUI = true;
                    GUI gui = new GUI();
                    break;
                }
                else {
                    System.out.println("Goodbye!!!");
                    break;
                }
            } else {
                System.out.println("Invalid!!! Follow Instructions");
                displayMenu();
            }
        }
        input.close();
    }

    private static void createAngel() {
        Display.displayAngel();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takeIn = input.nextLine();
            Integer v = Integer.parseInt(takeIn);
            if (v.equals(1) || v.equals(2) || v.equals(3)) {
                if (v == 1)
                    nameAngel("Seraph");
                else if (v == 2)
                    nameAngel("Archangel");
                else if (v == 3)
                    nameAngel("Cherubim");
                Display.displayMenu();
            }
            else {
                System.out.println("Oops!! Let's try that again");
                Display.displayAngel();
            }
        }
        Display.displayMenu();
    }

    public static void nameAngel(String type) {
        System.out.println("Name Your Angel::");
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takenIn = input.nextLine();
            if (EntityFactory.newAngel(takenIn, type) !=  null) {
                Database.getInstance().insertAngel((Angel) EntityFactory.newAngel(takenIn, type));
                break ;
            }
            else
                System.out.println("Name your Angel::");
        }
    }

    private static void selectAngel() {
        System.out.println("Select Your Angel::");
        Database.getInstance().printDatabase();
        if (!bIsAngel) {
            System.out.println("No angels exist in this realm!!!");
            Display.displayMenu();
            return ;
        } else {
            System.out.println("Select Your Angel And Finish Thy Journey::");
        }
        bIsAngel = false;
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takeIn = input.nextLine();
            List<Angel> angels = Database.getInstance().extractDatabase();
            int identical = 0;
            for (Angel a : angels) {
                if (a.getName().equals(takeIn)) {
                    angel = Database.getInstance().angelDetails(a.getName());
                    squareMap = MapFactory.generateMap(angel);
                    moveAngel();
                    identical = 1;
                }
            }
            if (identical == 1)
                System.out.println("::Choose What Is Given Or Create Your Own!!!");
        }
    }

    public static void moveAngel() {
        Display.displayDirections();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takeIn = input.nextLine();
            Integer v = Integer.parseInt(takeIn);
            if (v.equals(1) || v.equals(2) || v.equals(3) || v.equals(4)) {
                GameManager.move(v);
                GameManager.win();
            } else if (takeIn.equals("5")) {
                System.out.println("::Level -- " + angel.getLevel());
                System.out.println("::XP -- " + angel.getXp());
                System.out.println("::HP -- " + angel.getHp());
                System.out.println("::Attack -- " + angel.getAttack());
                System.out.println("::Defense -- " + angel.getDefense());
            } else {
                System.out.println("Follow Instructions!!!");
            }
            Display.displayDirections();
        }
        input.close();
    }
}