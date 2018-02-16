import java.io.Serializable;

public class Message implements Serializable {
    protected long timestamp;
    protected String message;
    protected String id;

    public Message(String id, long time, String msg) {
        this.id = id;
        this.timestamp = time;
        this.message = msg;
    }

    @Override
    public String toString() {
        return "[" + this.timestamp + "] [" + this.id + "] [" + this.message + "]";
    }
}
