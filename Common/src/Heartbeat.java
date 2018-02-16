import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Heartbeat extends Remote {
    void beat(Message message) throws RemoteException;
}
