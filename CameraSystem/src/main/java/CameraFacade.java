import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Palash on 2/16/2018.
 */
public class CameraFacade {
    private JFrame frame;

    private CameraFacade() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        HeartbeatTactics heartbeatTactics = new HeartbeatTactics("svm");
        Thread heartbeatThread = null;

        CameraFacade thermometer = null;
        try {
            heartbeatThread = heartbeatTactics.runHeartbeatTactics("Camera", "Alive");

            thermometer = new CameraFacade();
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
        frame.setSize(500, 200);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
        frame.setTitle("GPS");

        JLabel label = new JLabel(" cameras are warming up .....");
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        label.setForeground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(label);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Random random = new Random();
        int x = random.nextInt(100);

        ArrayList<String> dataStreaming = new ArrayList<String>(x);




        for (int i = 0; i < x; i++) {
            dataStreaming.add(" camera num " + i);

        }

        while (true) {
            int index = random.nextInt(100);

           String curentStv =  dataStreaming.get(index);
            Thread.sleep(1000);

            label.setText("observing: " + curentStv);
        }
    }

}
