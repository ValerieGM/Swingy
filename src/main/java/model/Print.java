package model;

import jdk.nashorn.internal.objects.Global;

import static model.Universal.bIsGUI;

public class Print {
    public static void print(String text) {
        if (!bIsGUI)
            System.out.println(text);
        else {
            if (!Universal.bFightPhase)
                Universal.logTextArea.setText(text + "\n");
            else
                Universal.logTextArea.append(text + "\n");
        }
    }
}
