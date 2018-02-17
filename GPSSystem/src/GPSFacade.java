import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Random;

/**
 * Created by Palash on 2/16/2018.
 */
public class GPSFacade {
    private JFrame frame;

    private GPSFacade() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        HeartbeatTactics heartbeatTactics = new HeartbeatTactics("svm");
        Thread heartbeatThread = null;

        GPSFacade thermometer = null;
        try {
            heartbeatThread = heartbeatTactics.runHeartbeatTactics("GPS", "Alive");

            thermometer = new GPSFacade();
            thermometer.startUI();
        } catch (Exception e) {
            heartbeatThread.interrupt();
            thermometer.stopUI();
            throw e;
        }
    }

    private void stopUI() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void startUI() throws Exception {
        frame = new JFrame();
        frame.setSize(1050, 200);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
        frame.setTitle("GPS");

        JLabel label = new JLabel("I will take you places!");
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        label.setForeground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(label);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Random random = new Random();
        while (true) {
            int x = random.nextInt(100);
            Thread.sleep(1000);
            label.setText("Latitude/Longitude: " + (float) 100 / x);
        }
    }

}
