import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Palash on 2/16/2018.
 */
public class GPSFacade {
    private JFrame frame;

    private GPSFacade() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        HeartbeatTactics heartbeatTactics = new HeartbeatTactics("svm");
        TimerTask heartbeatThread = null;

        GPSFacade thermometer = null;
        heartbeatThread = heartbeatTactics.runHeartbeatTactics("GPS", "Alive");

        try {

            thermometer = new GPSFacade();
            thermometer.startUI();
        } catch (Exception e) {
            e.printStackTrace();
            heartbeatThread.cancel();
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

        JLabel label = new JLabel("GPS STARTED WORKING!");
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        label.setForeground(Color.RED);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(label);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Random random = new Random();
        while (true) {

            int x = random.nextInt(50);
            label.setText("GPS: " +  100 / x);

            Thread.sleep(1000);
        }
    }

}
