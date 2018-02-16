import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

/**
 * Created by Palash on 2/16/2018.
 */
public class GPSFacade {
    Registry registry;
    Heartbeat stub;

   public  GPSFacade() throws Exception{
         registry = LocateRegistry.getRegistry(2020);
         stub = (Heartbeat) registry.lookup("svm");
    }
    private void IamLive() throws Exception{

        Message msg = new Message("GPS System", new Date().getTime(), "GPS System is working!");
        stub.beat(msg);
    }


    public static void main(String[] args) throws Exception {

         GPSFacade gpsFacade = new GPSFacade();


         while (true){
             gpsFacade.IamLive(); // send the value to health

             Thread.sleep(500);

         }



    }


}
