import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class FlightControls2 extends UnicastRemoteObject  implements SyncRmi, dataInput ,Serializable {


    protected FlightControls2() throws RemoteException {
    }
   static SyncRmi hh;
    public static void main(String [] arg) throws RemoteException, NotBoundException {
        try {
            FlightControls2 obj = new FlightControls2();
            LocateRegistry.getRegistry(2020);
            Naming.bind("//localhost:2020/RFlightControls", obj);
        } catch (Exception e) {
            System.err.println(" exception: " + e.toString());
            e.printStackTrace();
        }


        LocateRegistry.getRegistry(2020);
        Registry registry = LocateRegistry.getRegistry(2020);
        hh = (SyncRmi)registry.lookup("FlightControls");

        while (true){

        }

    }

    @Override
    public void sync(Message message) throws RemoteException {

        System.out.println("sync  from fc"+message.toString());
    }

    @Override
    public void send(String message) throws RemoteException {

        hh.sync(new Message("2",new Date().getTime(),message+"from fc2"));


    }
}
