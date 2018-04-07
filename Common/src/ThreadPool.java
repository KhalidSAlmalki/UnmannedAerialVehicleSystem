import java.rmi.Remote;
import java.rmi.RemoteException;

interface ThreadPool extends Remote {

     void taskreceiver(String m) throws RemoteException;
}
