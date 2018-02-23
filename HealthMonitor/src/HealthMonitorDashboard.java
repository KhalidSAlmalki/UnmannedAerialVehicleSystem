import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HealthMonitorDashboard extends UnicastRemoteObject implements Body {
    Map<Long, Message> beatsMap = new HashMap<>();
    GUIHealthMonitor guiHealthMonitor;

    public HealthMonitorDashboard() throws RemoteException {
        super();
        guiHealthMonitor = new GUIHealthMonitor();
        new Thread(() -> runCheckingFailedSystem()).start();
    }

    public static long getDateDiff(long timeUpdate) {
        return  Math.abs(System.currentTimeMillis() - timeUpdate);
    }

    public static void main(String args[]) {
        try {
            HealthMonitorDashboard obj = new HealthMonitorDashboard();
            LocateRegistry.createRegistry(2020);
            Naming.bind("//localhost:2020/svm", obj);
        } catch (Exception e) {
            System.err.println("Health Monitor Dashboard exception: " + e.toString());
            e.printStackTrace();
        }
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
        for (Long name : beatsMap.keySet()) {
            Message message = beatsMap.get(name);
            if (!message.getRead() && getDateDiff(message.getTimestamp()) > 10000) {
                guiHealthMonitor.addClosedApplication(message.getTime(), message.getId(), message.getPID());
                message.setRead(true);
                beatsMap.put(message.getPID(), message);
            }
        }
    }

    @Override
    public void beat(Message message) throws RemoteException {
        beatsMap.put(message.getPID(), message);
        guiHealthMonitor.addRunningApplication(message.getTime(), message.getId(), message.getPID());
    }
}