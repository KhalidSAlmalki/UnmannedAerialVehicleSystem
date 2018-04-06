import java.rmi.Remote;
import java.rmi.RemoteException;

public interface dataInput extends Remote {
    void send(String message) throws RemoteException;
    //void receive(String message) throws RemoteException;

}


