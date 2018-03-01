import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SyncRmi extends Remote {
    void sync(Message message) throws RemoteException;
}


