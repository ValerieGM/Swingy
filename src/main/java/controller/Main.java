package controller;

import view.GUI;
import view.Console;
import java.sql.SQLException;


import static model.Universal.bIsGUI;

public class Main {
    public static void main(String[] args) {
        try {
            if (args[0].equals("console")) {
                //start console
                System.out.println("Welcome to 42 Heaven (Or Hell)!!!!!!!");
                Console.begin();
          } else if (args[0].equals("gui")) {
                //start gui
                bIsGUI = true;
                GUI gui = new GUI();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Let's add console or gui alright?!?!?!?");
        }
    }

}