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
    static HeartbeatTactics heartbeatTactics;
    private GPSFacade() throws Exception {
    }

    public static void main(String[] args) throws Exception {
         heartbeatTactics = new HeartbeatTactics("svm");

        GPSFacade thermometer = null;


        thermometer = new GPSFacade();
        thermometer.startUI();


    }

    private void stopUI() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void startUI() throws Exception  {
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

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                label.setText("method called ");

                int x = random.nextInt(100);
                label.setText("Latitude/Longitude: " + (float) 100 / x);

                try {
                    heartbeatTactics.runHeartbeatTactics("GPS", "Alive");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        java.util.Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 5000);

    }

}
