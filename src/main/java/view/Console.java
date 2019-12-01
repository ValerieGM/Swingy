package view;

import controller.EntityFactory;
import controller.GameManager;
import controller.MapFactory;
import model.database.Database;
import model.entities.angels.Angel;

import java.sql.SQLException;
import java.util.*;

import static model.Universal.*;
import static view.Display.*;

public class Console {

    public static void begin() throws SQLException {
        bIsGUI = false;
        System.out.println("******Console View******");
        displayMenu();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takeIn = input.nextLine();
            if (takeIn.equals("1") || takeIn.equals("2") || takeIn.equals("3")) {
                int v = Integer.parseInt(takeIn.trim());
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

    private static void createAngel() throws SQLException {
        Display.displayAngel();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takeIn = input.nextLine();
            if (takeIn.equals("1") || takeIn.equals("2") || takeIn.equals("3")) {
                int v = Integer.parseInt(takeIn.trim());
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

    public static void nameAngel(String type) throws SQLException {
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

    private static void selectAngel() throws SQLException {
        System.out.println("Select Your Angel::");
        Database.getInstance().printDatabase();
        if (bIsAngel) {
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
//            boolean yes = false;
            for (Angel a : angels) {
                if (a.getName().equals(takeIn)) {
                    angel = Database.getInstance().angelDetails(a.getName());
                    squareMap = MapFactory.generateMap(angel);
                    moveAngel();
//                    yes = true;
                }
            }
        }
    }

    public static void moveAngel() {
        Display.displayDirections();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takeIn = input.nextLine();
            if (takeIn.equals("1") || takeIn.equals("2") || takeIn.equals("3") || takeIn.equals("4")) {
                Integer choice = Integer.parseInt(takeIn);
                GameManager.move(choice);
//                GameManager.win();
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