import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

class Message implements Serializable {
    private long timestamp;
    private String message;
    private String id;
    private long PID;

    Message(String id, long time, String msg) {
        this.id = id;
        this.timestamp = time;
        this.message = msg;
        this.PID = getPID();
    }

    private long getPID() {
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        return Long.valueOf(bean.getName().split("@")[0]);
    }

    @Override
    public String toString() {
        return "[" + this.timestamp + "] [" + this.id + "] [" + this.message + "]";
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getId() {
        return this.id;
    }
}
