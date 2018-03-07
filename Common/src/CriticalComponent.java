import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Palash on 2/28/2018.
 */
public interface CriticalComponent extends Remote {
    void execute(int operationID, String methodName, int first, int second) throws NoSuchMethodException, RemoteException;
    int detectBuilding(int a, int b) throws RemoteException;
    int detectBird(int a, int b) throws RemoteException;
    int detectEnemy(int a, int b) throws RemoteException;
    int detectAlly(int a, int b) throws RemoteException;
}
