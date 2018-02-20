//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUIHealthMonitor {

    private final DefaultTableModel closedModel, runningModel;

    public GUIHealthMonitor() {
        JFrame guiFrame = new JFrame();

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Health Monitor  GUI");
        guiFrame.setSize(900, 600);

        runningModel = new DefaultTableModel(new Object[]{"Time", "Application", "PID"}, 0);
        JTable runningT = new JTable(runningModel);
        runningT.setShowGrid(false);
        runningT.setBounds(guiFrame.getBounds());
        runningT.setForeground(Color.decode("#006400"));
        JScrollPane runningSP = new JScrollPane(runningT);
        JPanel runningP = new JPanel();
        runningP.setLayout(new BoxLayout(runningP, BoxLayout.Y_AXIS));
        JLabel runningL = new JLabel("Running applications");
        runningP.add(runningL);
        runningP.add(runningSP);
        guiFrame.add(runningP);

        closedModel = new DefaultTableModel(new Object[]{"Time", "Application", "PID"}, 0);
        JTable closedT = new JTable(closedModel);
        closedT.setShowGrid(false);
        closedT.setBounds(guiFrame.getBounds());
        closedT.setForeground(Color.RED);
        JScrollPane closedSP = new JScrollPane(closedT);
        JPanel closedP = new JPanel();
        closedP.setLayout(new BoxLayout(closedP, BoxLayout.Y_AXIS));
        JLabel closedL = new JLabel("Closed applications");
        closedP.add(closedL);
        closedP.add(closedSP);
        guiFrame.add(closedP);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                runningP, closedP);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(450);

        guiFrame.add(splitPane);

        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);
    }

    public void addRunningApplication(String time, String application, long PID) {
        runningModel.addRow(new Object[]{time, application, PID});
    }

    public void addClosedApplication(String time, String application, long PID) {
        closedModel.addRow(new Object[]{time, application, PID});
    }
}