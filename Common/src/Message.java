import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

class Message implements Serializable {
    private long timestamp;
    private String message;
    private String id;



    private Boolean isRead ;


    private long PID;

    Message(String id, long time, String msg) {
        this.id = id;
        this.timestamp = time;
        this.message = msg;
        this.PID = getPIDs();
        this.isRead = false;
    }

    private long getPIDs() {
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        return Long.valueOf(bean.getName().split("@")[0]);
    }

    @Override
    public String toString() {
        return "[" + this.timestamp + "] [" + this.id + "] [" + this.message + "]"+"[PID]"+"["+getPID()+"]";
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getId() {
        return this.id;
    }

    public long getPID() {
        return PID;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
