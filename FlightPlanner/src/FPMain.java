import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Palash on 3/1/2018.
 */
public class FPMain extends UnicastRemoteObject implements CriticalOutput {

    private static FPMain fp;
    private int operationID = -1;

    protected FPMain() throws RemoteException {
    }

    public static void main(String[] args) throws AlreadyBoundException, RemoteException, MalformedURLException {
        fp = new FPMain();
        Naming.bind("//localhost:2020/FlightPlanner", fp);
        fp.run();
    }

    private void run() throws RemoteException, MalformedURLException, AlreadyBoundException {
        while (true) {
        }
    }

    @Override
    public int getOperationID() {
        return this.operationID;
    }

    @Override
    public void setOutput(int operationID, String oa, int output) {
        this.operationID = operationID;
        System.out.println("FlightPlanner: [" + operationID + "] " + oa + " : " + output);
    }
}
