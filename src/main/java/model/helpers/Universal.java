package model.helpers;

import javax.swing.*;

import model.entities.angels.Angel;
import model.entities.demons.Demon;
import view.*;

public class Universal {
    public static int nbAngel = 0;

    public static Boolean bFightPhase = false;
    public static Boolean bEncounterPhase = false;
    public static Boolean bIsGUI = false;
    public static Boolean bIsAngel = false;

    public static Angel angel;
    public static Demon demon;
    public static Map map;
    public static JTextArea logTextArea;
}
