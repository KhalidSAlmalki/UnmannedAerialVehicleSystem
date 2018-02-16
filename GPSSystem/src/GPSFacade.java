import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

/**
 * Created by Palash on 2/16/2018.
 */
public class GPSFacade {
    private GPSFacade() {

    }
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(2020);
            Heartbeat stub = (Heartbeat) registry.lookup("svm");
            Message msg = new Message("GPS System", new Date().getTime(), "GPS System is working!");
            stub.beat(msg);
        } catch (Exception e) {
            System.err.println("GPS facade exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
