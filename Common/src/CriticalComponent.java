import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Palash on 2/28/2018.
 */
public interface CriticalComponent extends Remote {
    void execute(int operationID, String methodName, int first, int second) throws NoSuchMethodException, RemoteException;
    int add(int a, int b) throws RemoteException;
    int sub(int a, int b) throws RemoteException;
    int div(int a, int b) throws RemoteException;
    int mult(int a, int b) throws RemoteException;
}
