package uas.navigation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * Created by Palash on 2/13/2018.
 */
public class NavigationSystem {
    JFrame frame;
    JLabel label;

    NavigationSystem() {
        frame = new JFrame();
        frame.setSize(1050, 200);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
        frame.setTitle("NAVIGATION SYSTEM");

        label = new JLabel("Navigation System: I am taking you places!");
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        label.setForeground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(label);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        NavigationSystem system = new NavigationSystem();
        system.keepBeating();
    }

    private void keepBeating() {
        try {
            while (true) {
                Random random = new Random();
                label.setText("Navigation System: We are at a height of " + 10000 / random.nextInt(10) + "m !!");
            }
        } catch (Exception e) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }


}
