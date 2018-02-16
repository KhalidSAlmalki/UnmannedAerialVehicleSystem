import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

/**
 * Created by Palash on 2/16/2018.
 */
public class TemperatureFacade {
    private TemperatureFacade() {

    }
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(2020);
            Heartbeat stub = (Heartbeat) registry.lookup("temperature");
            Message msg = new Message("Thermometer", new Date().getTime(), "Thermometer is working!");
            stub.beat(msg);
        } catch (Exception e) {
            System.err.println("Temperature facade exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
