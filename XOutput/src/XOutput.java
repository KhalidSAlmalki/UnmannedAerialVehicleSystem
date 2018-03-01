import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Palash on 3/1/2018.
 */
public class XOutput extends UnicastRemoteObject implements CriticalOutput {

    private static XOutput xOutput;
    private int operationID = -1;
    private String xMain;
    private int output;

    protected XOutput() throws RemoteException {
    }

    public static void main(String[] args) throws AlreadyBoundException, RemoteException, MalformedURLException {
        xOutput = new XOutput();
        xOutput.run();
    }

    private void run() throws RemoteException, MalformedURLException, AlreadyBoundException {
        Naming.bind("//localhost:2020/XOutput", xOutput);
        while (true) {
        }
    }

    @Override
    public int getOperationID() {
        return this.operationID;
    }

    @Override
    public void setOutput(int operationID, String xMain, int output) {
        this.operationID = operationID;
        this.xMain = xMain;
        this.output = output;
        System.out.println("[" + operationID + "] " + xMain + " : " + output);
    }
}
