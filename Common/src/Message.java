import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.Locale;

class Message implements Serializable {
    private long timestamp;
    private String message;
    private String id;
    private Boolean isRead;

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
        return "[" + this.timestamp + "] [" + this.id + "] [" + this.message + "]" + "[PID]" + "[" + getPID() + "]";
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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getCrashMessage() {
        return String.format("[%20s] %-32s %s", getTime(), this.id, this.PID);
    }

    public String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.timestamp);
    }

    public String getRunningMessage() {
        return String.format("[%20s] %-32s %s", getTime(), this.id, this.PID);
    }
}
