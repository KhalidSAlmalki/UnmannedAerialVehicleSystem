import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
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

    private X1Main() throws RemoteException {
        super();
        this.methods = new LinkedList<>();
    }

    @Override
    public void execute(int operationID, String methodName, int first, int second) throws NoSuchMethodException {
        this.methods.add(new CriticalMethod(methodName, operationID, first, second));
    }

    public static void main(String[] args) throws InterruptedException, InvocationTargetException, IllegalAccessException, RemoteException, MalformedURLException, AlreadyBoundException, NotBoundException {
        X1Main x1Main = new X1Main();
        Naming.bind("//localhost:2020/X1", x1Main);
        x1Main.run();
    }

    private void run() throws InterruptedException, InvocationTargetException, IllegalAccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(2020);
        CriticalOutput xComponent = null;

        while (true) {
            if (!this.methods.isEmpty()) {
                CriticalMethod method = this.methods.poll();
                xComponent = (CriticalOutput) registry.lookup("XOutput");
                if (xComponent.getOperationID() > method.getOperationID()) {
                    continue;
                } else {
                    int output = execute(method.getMethod(), method.getFirst(), method.getSecond(), method.getOperationID());
                    xComponent = (CriticalOutput) registry.lookup("XOutput");
                    if (xComponent.getOperationID() < method.getOperationID())
                        xComponent.setOutput(method.getOperationID(), "X1Main", output);
                }
            }
            Thread.sleep(new Random().nextInt(20) * 100);
        }
    }

    private int execute(String method, int first, int second, int operationID) {
        System.out.println("[" + operationID + "]" + method + ", " + first + ", " + second);
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
        return a / b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }
}
