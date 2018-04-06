import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class FlightControls extends UnicastRemoteObject implements SyncRmi, dataInput ,Serializable{

    protected FlightControls() throws RemoteException {
    }
   static SyncRmi h;
    public static void main(String [] arg) throws RemoteException, InterruptedException, NotBoundException {

        try {
            FlightControls obj = new FlightControls();
            LocateRegistry.createRegistry(2020);

            Naming.bind("//localhost:2020/FlightControls", obj);
        } catch (Exception e) {
            System.err.println("Health Monitor Dashboard exception: " + e.toString());
            e.printStackTrace();
        }

        Thread.sleep(5000);
        LocateRegistry.getRegistry(2020);
        Registry registry = LocateRegistry.getRegistry(2020);
         h = (SyncRmi)registry.lookup("RFlightControls");





    }


    @Override
    public void sync(Message message) throws RemoteException {

        System.out.println("sync  from re"+message.toString());
    }
//
    @Override
    public void send(String message) throws RemoteException {

        h.sync(new Message("1",new Date().getTime(),message));
    }
}
