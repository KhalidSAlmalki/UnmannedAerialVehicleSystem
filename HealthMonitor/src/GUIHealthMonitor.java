//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..

import javax.swing.*;
import java.awt.*;

public class GUIHealthMonitor {

    JTextArea runningPane, closedPane;

    public GUIHealthMonitor() {
        JFrame guiFrame = new JFrame();

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Health Monitor  GUI");
        guiFrame.setSize(400, 400);

        runningPane = new JTextArea();
        runningPane.setBounds(guiFrame.getBounds());
        guiFrame.add(runningPane);

        closedPane = new JTextArea();
        closedPane.setBounds(guiFrame.getBounds());
        guiFrame.add(closedPane);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                runningPane, closedPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

//        textArea = new JTextArea();
//        textArea.setBounds(guiFrame.getBounds());
//        guiFrame.add(textArea);
//
//        textArea.setEditable(false);
//        scroll = new JScrollPane(textArea);
//        scroll.setBounds(guiFrame.getBounds());
//        guiFrame.getContentPane().add(scroll);

        guiFrame.add(splitPane);

        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);
    }

//    public void addValueToTextArea(String text) {
//        textArea.append("\n");
//        textArea.append(text);
//    }

    public void addRunningApplication(String text){
        runningPane.append("\n");
        runningPane.append(text);
    }

    public void addClosedApplication(String text){
        closedPane.append("\n");
        closedPane.append(text);
    }
}