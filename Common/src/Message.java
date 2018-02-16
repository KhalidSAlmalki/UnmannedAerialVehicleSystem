import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class Message implements Serializable {
    protected long timestamp;
    protected String message;
    protected String id;

    public Message(String id, long time, String msg) {
        this.id = id;
        this.timestamp = time;
        this.message = msg+" -- PID: "+getPID();
    }

    private long getPID(){

        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String jvmName = bean.getName();

        long pid = Long.valueOf(jvmName.split("@")[0]);
        return pid;
    }

    @Override
    public String toString() {
        return "[" + this.timestamp + "] [" + this.id + "] [" + this.message + "]";
    }
}
