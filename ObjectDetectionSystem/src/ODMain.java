import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ODMain extends UnicastRemoteObject {
    private final Registry registry;
    private Map<String, CriticalComponent> workingComponents;
    private List<String> deadComponents;

    private ODMain() throws RemoteException {
        workingComponents = new HashMap<>();
        deadComponents = new ArrayList<>();
        registry = LocateRegistry.getRegistry(2020);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        ODMain input = new ODMain();
        input.initialize();
        input.run();
    }

    private void run() {
        int operationID = 0;
        Random random = new Random();
            String[] methods = {"detect building", "detect bird", "detect enemy", "detect ally"};
        while (true) {
            try {
                int methodNumber = random.nextInt(4), first = random.nextInt(10), second = random.nextInt(10);
                System.out.println("ODMain: [" + operationID + "] Commanding processors to '" + methods[methodNumber] + "' with input values " + first + " and " + second);
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
                    System.out.println("ODMain: " + componentName + " is dead!");
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

        CriticalComponent oa1 = (CriticalComponent) registry.lookup("OA1");
        workingComponents.put("OA1", oa1);

        CriticalComponent oa2 = (CriticalComponent) registry.lookup("OA2");
        workingComponents.put("OA2", oa2);
    }
}
