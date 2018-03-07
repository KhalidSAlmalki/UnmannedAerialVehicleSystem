import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class OAMain2 extends UnicastRemoteObject implements CriticalComponent {
    private Queue<CriticalMethod> methods;
    static OAMain2 oa = null;

    private OAMain2() throws RemoteException {
        super();
        this.methods = new LinkedList<>();
    }

    @Override
    public void execute(int operationID, String methodName, int first, int second) throws NoSuchMethodException {
        this.methods.add(new CriticalMethod(methodName, operationID, first, second));
        System.out.println("OA2: Added command number " + operationID + " to list of operations to perform.");
    }

    public static void main(String[] args) {
        try {
            oa = new OAMain2();
            Naming.rebind("//localhost:2020/OA2", oa);
            oa.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void run() throws InterruptedException, IllegalAccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(2020);
        CriticalOutput xComponent = null;

        xComponent = (CriticalOutput) registry.lookup("FlightPlanner");
        while (true) {
            if (!this.methods.isEmpty()) {
                CriticalMethod method = this.methods.poll();
                if (xComponent.getOperationID() > method.getOperationID()) {
                    System.out.println("OA2: No need to execute command [" + method.getOperationID() + "] as Output is at " + xComponent.getOperationID() + "! Proceed to next command.");
                    continue;
                } else {
                    int output = execute(method.getMethod(), method.getFirst(), method.getSecond(), method.getOperationID());
                    if (xComponent.getOperationID() < method.getOperationID())
                        xComponent.setOutput(method.getOperationID(), "ObjectAvoidance", output);
                    else
                        System.out.println("OA2: Command [" + method.getOperationID() + "] took a lot of time to execute. Output was provided by other component. Ignoring this result.");
                }
            }
            Random random = new Random();
            int x = random.nextInt(100);
            Thread.sleep(1000);
            int a = 100 / x;
        }
    }

    private int execute(String method, int first, int second, int operationID) {
        System.out.println("OA2: Processing [" + operationID + "] " + method + "(" + first + ", " + second + ")");
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(25) * 500);
        } catch (InterruptedException e) {
        }
        switch (method) {
            case "detect building":
                return detectBuilding(first, second);
            case "detect bird":
                return detectBird(first, second);
            case "detect enemy":
                return detectEnemy(first, second);
            case "detect ally":
                return detectAlly(first, second);
        }
        return 0;
    }

    @Override
    public int detectBuilding(int a, int b) {
        return a + b;
    }

    @Override
    public int detectBird(int a, int b) {
        return a - b;
    }

    @Override
    public int detectEnemy(int a, int b) {
        try {
            return a / b;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int detectAlly(int a, int b) {
        return a * b;
    }

    @Override
    public String toString() {
        return "ObjectAvoidance2";
    }
}
