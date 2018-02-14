package uas.navigation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/**
 * Created by Palash on 2/13/2018.
 */
public class Main {
    int counter = 0;

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

        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e) {
                counter = 50;
                System.out.println("Closed");
                e.getWindow().dispose();
            }


        });
        while (counter <50){
            label.setText("Navigation System: I AM TAKING YOU PLACES! "+ counter);
            System.out.println(counter);
            counter++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        new Main();

    }


}
