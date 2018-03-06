import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class XInput extends UnicastRemoteObject {
    private final Registry registry;
    private Map<String, CriticalComponent> workingComponents;
    private List<String> deadComponents;

    private XInput() throws RemoteException {
        workingComponents = new HashMap<>();
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
                System.out.println("XInput: [" + operationID + "] Commanding processors to '" + methods[methodNumber] + "' with input values " + first + " and " + second);
                Iterator<Map.Entry<String, CriticalComponent>> iterator = workingComponents.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, CriticalComponent> entry = iterator.next();
                    CriticalComponent component = entry.getValue();
                    try {
                        component.execute(operationID, methods[methodNumber], first, second);
                    } catch (RemoteException | NoSuchMethodException e) {
                        deadComponents.add(entry.getKey());
                        iterator.remove();
                    }
                }

                for (String componentName : deadComponents) {
                    System.out.println("XInput: " + componentName + " is dead!");
                }
                operationID++;
                Thread.sleep(random.nextInt(25) * 400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize() throws RemoteException, NotBoundException {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CriticalComponent fc1 = (CriticalComponent) registry.lookup("FC1");
        workingComponents.put("FC1", fc1);

        CriticalComponent fc2 = (CriticalComponent) registry.lookup("FC2");
        workingComponents.put("FC2", fc2);
    }
}
