import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

/**
 * Created by Palash on 2/17/2018.
 */
class HeartbeatTactics {

    private final Body body;

    HeartbeatTactics(String lookup) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(2020);
        this.body = (Body) registry.lookup(lookup);
    }

    private void beat(String id, String msg) throws RemoteException {
        this.body.beat(new Message(id, new Date().getTime(), msg));
    }

    Thread runHeartbeatTactics(String id, String msg) throws Exception {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    beat(id, msg);
                } catch (Exception e) {
                    break;
                }
            }
        });
        thread.start();

        return thread;
    }
}
