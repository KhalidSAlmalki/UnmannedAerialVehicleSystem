import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HealthMonitorDashboard extends UnicastRemoteObject implements Body {

    Map<String, Message> beatsMap = new HashMap<String, Message>();

    public static long getDateDiff(long timeUpdate) {
        long diffInMillies = Math.abs(System.currentTimeMillis() - timeUpdate);
        return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public HealthMonitorDashboard() throws RemoteException {
        super();
//        new java.util.Timer().schedule(
//                new java.util.TimerTask() {
//                    @Override
//                    public void run() {
//                        detectFailedSystem();
//                    }
//                },
//                5000
//        );
    }

    private synchronized void detectFailedSystem() {
        for (String name : beatsMap.keySet()) {
            String key = name.toString();
            Message beat = beatsMap.get(name);
            System.out.println(getDateDiff(beat.getTimestamp()));
            if (getDateDiff(beat.getTimestamp()) > 10) {
                System.out.println("Down" + beat.toString());
            }
        }
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
    }

    @Override
    public void beat(Message message) throws RemoteException {
        beatsMap.put(message.getId(), message);
        System.out.println(message.toString());
    }
}