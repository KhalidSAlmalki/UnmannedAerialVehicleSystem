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
    private Body body;
    private JFrame frame;

    private GPSFacade() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(2020);
        this.body = (Body) registry.lookup("svm");
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        GPSFacade gps = new GPSFacade();
        try {
            gps.startUI();
            gps.keepBeating();
        } catch (Exception e) {
            System.out.println("Stopping GPS System due to unexpected exception.");
        } finally {
            gps.stopUI();
        }
    }

    private void stopUI() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void startUI() throws Exception {
        frame = new JFrame();
        frame.setSize(1050, 200);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
        frame.setTitle("NAVIGATION SYSTEM");

        JLabel label = new JLabel("Navigation System: I am taking you places!");
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        label.setForeground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(label);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void keepBeating() throws Exception {
        while (true) {
            Random random = new Random();
            int x = random.nextInt(10);
            System.out.println("x: " + 100 / x);

            Thread.sleep(1000);
            beat();
        }
    }

    private void beat() throws RemoteException {
        this.body.beat(new Message("GPS System", new Date().getTime(), "GPS System is working!"));
    }

}
