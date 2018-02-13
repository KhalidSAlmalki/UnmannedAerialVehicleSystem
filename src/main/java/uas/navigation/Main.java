package uas.navigation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Palash on 2/13/2018.
 */
public class Main {
    Main() {
        JFrame frame = new JFrame();
        frame.setSize(1050, 200);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
        frame.setTitle("NAVIGATION SYSTEM");

        JLabel label = new JLabel("Navigation System: I AM TAKING YOU PLACES!");
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        label.setForeground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(label);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }


}
