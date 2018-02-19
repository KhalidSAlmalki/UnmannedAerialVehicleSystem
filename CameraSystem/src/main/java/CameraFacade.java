import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

/**
 * Created by Palash on 2/16/2018.
 */
public class CameraFacade {
    private JFrame frame;

    private CameraFacade() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        HeartbeatTactics heartbeatTactics = new HeartbeatTactics("svm");
        TimerTask heartbeatThread = null;

        CameraFacade camera = null;
        heartbeatThread = heartbeatTactics.runHeartbeatTactics("Camera", "Alive");

        try {
            camera = new CameraFacade();
            camera.startUI();
        } catch (Exception e) {
            e.printStackTrace();
            heartbeatThread.cancel();
            camera.stopUI();
            throw e;
        }
    }

    private void stopUI() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void startUI() throws Exception {
        frame = new JFrame();
        frame.setSize(400, 200);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
        frame.setTitle("Camera ");

        JLabel label = new JLabel("Camera STARTED WORKING!");
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        label.setForeground(Color.RED);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(label);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
        Random random = new Random();
        while (true) {

            int x = random.nextInt(10);
            label.setText("Camera: " +  100 / x);

            Thread.sleep(1000);
        }
    }


}
