import java.lang.reflect.Method;

/**
 * Created by Palash on 3/1/2018.
 */
public class CriticalMethod {
    private String methodName;
    private int operationID, first, second;

    public CriticalMethod(String methodName, int operationID, int first, int second) {
        this.methodName = methodName;
        this.operationID = operationID;
        this.first = first;
        this.second = second;
    }

    public int getOperationID() {
        return this.operationID;
    }

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }

    public String getMethod() {
        return this.methodName;
    }
}
