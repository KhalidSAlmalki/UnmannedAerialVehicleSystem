import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Palash on 2/28/2018.
 */
public class XInput extends UnicastRemoteObject {
    private final Registry registry;
    private List<CriticalComponent> workingComponents, deadComponents;

    private XInput() throws RemoteException {
        workingComponents = new ArrayList<>();
        deadComponents = new ArrayList<>();
        registry = LocateRegistry.createRegistry(2020);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        XInput input = new XInput();
        input.initialize();
        input.run();
    }

    private void run() {
        int operationID = 0;
        Random random = new Random();
        String[] methods = {"add", "sub", "div", "mult"};
        while (true) {
            try {
                int methodNumber = random.nextInt(4), first = random.nextInt(10), second = random.nextInt(10);
                for (CriticalComponent component : workingComponents) {
                    component.execute(operationID, methods[methodNumber], first, second);
                }
                System.out.println(methods[methodNumber] + ", " + first + ", " + second);
                operationID++;
                Thread.sleep(1000);
            } catch (InterruptedException | NoSuchMethodException | RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize() throws RemoteException, NotBoundException {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CriticalComponent x1Component = (CriticalComponent) registry.lookup("X1");
        workingComponents.add(x1Component);

        CriticalComponent x2Component = (CriticalComponent) registry.lookup("X2");
        workingComponents.add(x2Component);
    }
}
