import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class HealthMonitorDashboard extends UnicastRemoteObject implements Heartbeat {



    public HealthMonitorDashboard() throws RemoteException {
        super();
    }



    private void detectFailedSystem(){

    }

    public static void main(String args[]) {

        // thread check after 10 second


        //

        try {
            HealthMonitorDashboard obj = new HealthMonitorDashboard();
            LocateRegistry.createRegistry(2020);
            Naming.bind("//localhost:2020/svm", obj);
            System.err.println("HealthMonitorDashboard ready");
        } catch (Exception e) {
            System.err.println("HealthMonitorDashboard exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void beat(Message message) throws RemoteException {
        System.out.println(message.toString());
    }
}