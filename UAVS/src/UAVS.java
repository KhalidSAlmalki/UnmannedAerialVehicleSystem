import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;

public class UAVS {

    static String Workplacepath;

    public static void main(String[] args) throws IOException, InterruptedException {
        Workplacepath = Paths.get(".").toAbsolutePath().normalize().toString();
        setUpGUI();

        Process p = Runtime.getRuntime().exec("java -jar " + Workplacepath + "/HealthMonitor.jar");
        Thread.sleep(1000);

        Process p1 = Runtime.getRuntime().exec("java -jar " + Workplacepath + "/CameraSystem.jar");
        Thread.sleep(1000);

        Process p2 = Runtime.getRuntime().exec("java -jar " + Workplacepath + "/GPSSystem.jar");
        Thread.sleep(1000);

        Process p3 = Runtime.getRuntime().exec("java -jar " + Workplacepath + "/TemperatureSVM.jar");
    }

    static void setUpGUI() {

        JFrame guiFrame = new JFrame();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("SYSTEM GUI");
        guiFrame.setSize(400, 80);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        JButton Health = new JButton();
        Health.setSize(100, 100);
        Health.setText("Health Monitor");
        guiFrame.add(Health);

        Health.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar " + Workplacepath + "/HealthMonitor.jar");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // button  camera
        JButton camera = new JButton();
        camera.setSize(100, 100);
        camera.setText("Camera System");

        camera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar  " + Workplacepath + "/CameraSystem.jar");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        guiFrame.add(camera);

        // Gps  camera
        JButton Gps = new JButton();
        Gps.setSize(100, 100);
        Gps.setText("GPS System");
        guiFrame.add(Gps);

        Gps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar " + Workplacepath + "/GPSSystem.jar");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        JButton temperature = new JButton();
        temperature.setSize(100, 100);
        temperature.setText("Temperature System");
        guiFrame.add(temperature);

        temperature.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar " + Workplacepath + "/TemperatureSVM.jar");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        guiFrame.setLayout(new FlowLayout());
        guiFrame.setVisible(true);
    }
}
