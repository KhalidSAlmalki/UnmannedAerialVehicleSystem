import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Palash on 3/1/2018.
 */
public interface CriticalOutput extends Remote{
    int getOperationID() throws RemoteException;

    void setOutput(int operationID, String x1Main, int output) throws RemoteException;
}
