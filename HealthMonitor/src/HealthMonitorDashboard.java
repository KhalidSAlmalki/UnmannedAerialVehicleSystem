import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class HealthMonitorDashboard extends UnicastRemoteObject implements Body {

    Map<String, Message> beatsMap = new HashMap<String, Message>();

    GUIHealthMonitor guiHealthMonitor;

    public HealthMonitorDashboard() throws RemoteException {
        super();
        guiHealthMonitor = new GUIHealthMonitor();
        new Thread(() -> runCheckingFailedSystem()).start();
    }

    public static long getDateDiff(long timeUpdate) {
        long diffInMillies = Math.abs(System.currentTimeMillis() - timeUpdate);
        return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static void main(String args[]) {
        // thread check after 10 second
        try {
            HealthMonitorDashboard obj = new HealthMonitorDashboard();
            LocateRegistry.createRegistry(2020);
            Naming.bind("//localhost:2020/svm", obj);

            System.err.println("Health Monitor started running.");

        } catch (Exception e) {
            System.err.println("Health Monitor Dashboard exception: " + e.toString());
            e.printStackTrace();
        }

//        while (true) {
//        }
    }

    private void runCheckingFailedSystem() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                detectFailedSystem();
            }
        };
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 2000);
    }

    private synchronized void detectFailedSystem() {
        for (String name : beatsMap.keySet()) {
            Message beat = beatsMap.get(name);
            if (getDateDiff(beat.getTimestamp()) > 3 && !beat.getRead()) {
                guiHealthMonitor.addClosedApplication(beat.getId() + " has crashed !!" + " [PID: " + beat.getPID() + "]");
                beat.setRead(true);
                beatsMap.put(beat.getId(), beat);
            }
        }
    }

    @Override
    public void beat(Message message) throws RemoteException {
        beatsMap.put(message.getId(), message);
        guiHealthMonitor.addRunningApplication(message.toString());

    }
}