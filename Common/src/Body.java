import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Body extends Remote {
    void beat(Message message) throws RemoteException;
}
