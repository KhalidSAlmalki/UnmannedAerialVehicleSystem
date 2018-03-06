import java.io.IOException;
import java.nio.file.Paths;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class XInput extends UnicastRemoteObject {
    private final Registry registry;
    private Map<String, CriticalComponent> workingComponents, deadComponents;

    private XInput() throws RemoteException {
        workingComponents = new HashMap<>();
        deadComponents = new HashMap<>();
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
                Iterator<Map.Entry<String, CriticalComponent>> iterator = workingComponents.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, CriticalComponent> entry = iterator.next();
                    CriticalComponent component = entry.getValue();
                    try {
                        component.execute(operationID, methods[methodNumber], first, second);
                    } catch (RemoteException e) {
                        System.out.println(component.getClass() + " is down.");
                        deadComponents.put(entry.getKey(), component);
                        iterator.remove();
                    } catch (NoSuchMethodException e) {
                    }
                }

                iterator = workingComponents.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, CriticalComponent> entry = iterator.next();
                    CriticalComponent component = entry.getValue();
                    try {
                        if (component.getLastOperationID() != operationID) {
                            System.out.println(component.getLastOperationID() + " | " + operationID);
                        }
                    } catch (Exception e) {
                        deadComponents.put(entry.getKey(), component);
                        iterator.remove();
                    }
                }

                iterator = deadComponents.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, CriticalComponent> entry = iterator.next();
                    System.out.println("Looks like " + entry.getKey() + " is dead! Attempting to restart...");
                    String componentName = entry.getKey();
                    try {
                        String workplacepath = Paths.get(".").toAbsolutePath().normalize().toString();
//                        ProcessBuilder pb = new ProcessBuilder("java", "-jar", workplacepath + "/" + componentName + ".jar");
//                        Process p = pb.start();
//                        Process p = Runtime.getRuntime().exec("cmd /c start " + workplacepath + "/" + componentName + ".bat");
                        Process p = Runtime.getRuntime().exec("cmd /c java -jar " + workplacepath + "/" + componentName + ".jar");
                        p.waitFor();
                        iterator.remove();
                        workingComponents.put(entry.getKey(), (CriticalComponent) registry.lookup(componentName));
                    } catch (IOException | NotBoundException e) {
                        e.printStackTrace();
                        System.out.println("Component, " + componentName + ", is dead...");
                    }
                }
                System.out.println(methods[methodNumber] + ", " + first + ", " + second);
                operationID++;
                Thread.sleep(random.nextInt(20) * 300);
            } catch (InterruptedException e) {
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
        workingComponents.put("X1", x1Component);

        CriticalComponent x2Component = (CriticalComponent) registry.lookup("X2");
        workingComponents.put("X2", x2Component);
    }
}
