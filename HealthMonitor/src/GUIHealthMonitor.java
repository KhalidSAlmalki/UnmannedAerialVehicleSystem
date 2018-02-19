//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..

import javax.swing.*;

public class GUIHealthMonitor {

    JTextArea textArea;

    public GUIHealthMonitor() {
        JFrame guiFrame = new JFrame();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Health Monitor  GUI");
        guiFrame.setSize(400, 400);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);


        // add teatarea

        textArea = new JTextArea();
        textArea.setBounds(guiFrame.getBounds());
        guiFrame.add(textArea);
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
    }

    public void addValueToTeaxArea(String text) {

        textArea.append("\n");
        textArea.append(text);
        textArea.getAutoscrolls();


    }


}