package model.helpers;

import static model.helpers.Universal.bIsGUI;

public class View {
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
