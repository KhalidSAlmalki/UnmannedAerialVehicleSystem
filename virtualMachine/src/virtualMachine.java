
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class virtualMachine  {

    protected virtualMachine() throws RemoteException {
    }

    public static void main(String [] arg) throws RemoteException, NotBoundException, InterruptedException {

        Registry registry = LocateRegistry.getRegistry(2020);
        System.out.println(registry.list()[0]);
        dataInput h = (dataInput)registry.lookup("FlightControls");
        dataInput h2 = (dataInput)registry.lookup("RFlightControls");


        while (true){
            Thread.sleep(5000);
            System.out.println("send khalid");
            h.send("khalid ");
            h2.send("khalid ");

        }

    }
}
