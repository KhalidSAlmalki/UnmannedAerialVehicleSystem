import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class X1Main extends UnicastRemoteObject implements CriticalComponent {
    private Queue<CriticalMethod> methods;
    private int lastOperationID = -1;
    static X1Main x1Main = null;

    private X1Main() throws RemoteException {
        super();
        this.methods = new LinkedList<>();
    }

    @Override
    public void execute(int operationID, String methodName, int first, int second) throws NoSuchMethodException {
        this.methods.add(new CriticalMethod(methodName, operationID, first, second));
        this.lastOperationID = operationID;
    }

    public static void main(String[] args) {
        try {
            x1Main = new X1Main();
            Naming.rebind("//localhost:2020/X1", x1Main);
            x1Main.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void run() throws InterruptedException, IllegalAccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(2020);
        CriticalOutput xComponent = null;

        xComponent = (CriticalOutput) registry.lookup("XOutput");
        while (true) {
            if (!this.methods.isEmpty()) {
                CriticalMethod method = this.methods.poll();
                if (xComponent.getOperationID() > method.getOperationID()) {
                    continue;
                } else {
                    int output = execute(method.getMethod(), method.getFirst(), method.getSecond(), method.getOperationID());
                    xComponent = (CriticalOutput) registry.lookup("XOutput");
                    if (xComponent.getOperationID() < method.getOperationID())
                        xComponent.setOutput(method.getOperationID(), "X1Main", output);
                }
            }
            Random random = new Random();
            Thread.sleep(random.nextInt(20) * 300);
            int x = random.nextInt(20);
            int a = 100 / x;
        }
    }

    private int execute(String method, int first, int second, int operationID) {
        System.out.println("X1Main processing [" + operationID + "] " + method + "(" + first + ", " + second + ")");
        switch (method) {
            case "add":
                return add(first, second);
            case "sub":
                return sub(first, second);
            case "div":
                return div(first, second);
            case "mult":
                return mult(first, second);
        }
        return 0;
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int div(int a, int b) {
        try {
            return a / b;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int getLastOperationID() throws RemoteException {
        return this.lastOperationID;
    }

    @Override
    public String toString() {
        return "X1Main";
    }
}
