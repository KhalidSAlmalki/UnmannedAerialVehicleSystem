import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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

    TimerTask runHeartbeatTactics(String id, String msg)  {

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {

                    try {
                        beat(id, msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            };

            java.util.Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(timerTask, 0, 3000);



        return timerTask;
    }
}
