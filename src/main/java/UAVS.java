import javafx.scene.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UAVS {

    public static void main(String[] args) {

        setUpGUI();
    }

    static void setUpGUI(){

        JFrame guiFrame = new JFrame();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Health Monitor  GUI");
        guiFrame.setSize(400, 400);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        JButton Health = new JButton();
        Health.setSize(100,100);
        Health.setText("Health Monitor");
        guiFrame.add(Health);

        Health.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar /Users/khalidalmalki/Documents/GitHub/UnmannedAerialVehicleSystem/out/artifacts/HealthMonitor_jar/HealthMonitor.jar");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                System.out.println("Health");
            }
        });

        // button  camera
        JButton camera = new JButton();
        camera.setSize(100,100);
        camera.setText("camera");

        camera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar  /Users/khalidalmalki/Documents/GitHub/UnmannedAerialVehicleSystem/out/artifacts/CameraSystem_jar/CameraSystem.jar");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                System.out.println("camera");

            }
        });

        guiFrame.add(camera);

        // Gps  camera
        JButton Gps = new JButton();
        Gps.setSize(100,100);
        Gps.setText("GPS");
        guiFrame.add(Gps);

        Gps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar /Users/khalidalmalki/Documents/GitHub/UnmannedAerialVehicleSystem/out/artifacts/GPSSystem_jar/GPSSystem.jar");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                System.out.println("Gps on in proceess id ");
            }
        });




        JButton temp = new JButton();
        temp.setSize(100,100);
        temp.setText("temp");
        guiFrame.add(temp);

        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar /Users/khalidalmalki/Documents/GitHub/UnmannedAerialVehicleSystem/out/artifacts/TemperatureSVM_jar/TemperatureSVM.jar");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                System.out.println("temp on in proceess id ");
            }
        });


        //

        //make sure the JFrame is visible
        guiFrame.setLayout(new FlowLayout());

        guiFrame.setVisible(true);
    }
}
