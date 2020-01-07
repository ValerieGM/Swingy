package view;

import java.util.*;

import controller.EntityCreator;
import controller.GameManager;
import controller.MapCreator;
import model.database.Database;
import model.entities.angels.Angel;
import model.helpers.View;

import static model.helpers.Universal.*;
import static view.Display.*;

public class Console {

    public static void begin() {
            bIsGUI = false;
            View.print("******Console View******");
            displayMenu();
            Scanner input = new Scanner(System.in);

            while (input.hasNextLine()) {
                try {
                    String takeIn = input.nextLine();
                    Integer v = Integer.parseInt(takeIn);
                    if (v.equals(1) || v.equals(2) || v.equals(3) || v.equals(4)) {
                        if (v == 1) {
                            //create hero
                            createAngel();
                            break;
                        } else if (v == 2) {
                            //select hero;
                            selectAngel();
                            break;
                        } else if (v == 3) {
                            bIsGUI = true;
                            GUI gui = new GUI();
                            return;
                        } else if (v == 4) {
                            View.print("Goodbye!!!");
                            break;
                        }
                    } else {
                        View.print("Invalid!!! Follow Instructions");
                        displayMenu();
                    }
                } catch (Exception e){
                    View.print("ConsoleBegin:: Invalid Input");
                }
            }
            input.close();
    }

    private static void createAngel() {
            Display.displayAngel();
            Scanner input = new Scanner(System.in);
            while (input.hasNextLine()) {
                try {
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
                    } else {
                        View.print("Oops!! Let's try that again");
                        Display.displayAngel();
                    }
                } catch (Exception e) {
                    View.print("ConsoleCreateAngel:: Invalid Input");
                }
            }
            Display.displayMenu();
    }

    public static void nameAngel(String type) {
        View.print("Name Your Angel::");
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takenIn = input.nextLine();
            if (EntityCreator.newAngel(takenIn, type) !=  null) {
                Database.getInstance().insertAngel((Angel) EntityCreator.newAngel(takenIn, type));
                break ;
            }
            else
                View.print("Name your Angel::");
        }
    }

    private static void selectAngel() {
        View.print("Select Your Angel::");
        Database.getInstance().printDatabase();
        if (!bIsAngel) {
            View.print("No angels exist in this realm!!!");
            Display.displayMenu();
            return ;
        } else {
            View.print("Select Your Angel By Name And Finish Thy Journey::");
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
                    map = MapCreator.generateMap(angel);
                    moveAngel();
                    identical = 1;
                }
            }
            if (identical == 0)
                View.print("::Choose What Is Given Or Create Your Own!!!");
        }
    }

    public static void moveAngel() {
        Display.displayDirections();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            try {
                String takeIn = input.nextLine();
                Integer v = Integer.parseInt(takeIn);
                if (v.equals(1) || v.equals(2) || v.equals(3) || v.equals(4)) {
                    GameManager.move(v);
                    GameManager.win();
                } else if (takeIn.equals("5")) {
                    View.print("::Level -- " + angel.getLevel());
                    View.print("::XP -- " + angel.getXp());
                    View.print("::HP -- " + angel.getHp());
                    View.print("::Attack -- " + angel.getAttack());
                    View.print("::Defense -- " + angel.getDefense());
                } else {
                    View.print("Follow Instructions!!!");
                }
                Display.displayDirections();
            } catch (Exception e){
                View.print("MoveAngel:: Invalid Input");
            }
        }
        input.close();
    }
}