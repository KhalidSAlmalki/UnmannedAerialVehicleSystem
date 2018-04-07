import java.util.concurrent.Callable;

class LenthyJob implements Callable<Long> {
    private int priority;

    public LenthyJob(int priority) {
        this.priority = priority;
    }

    public Long call() throws Exception {
        System.out.println("Executing: " + priority);
        long num = 1000000;
        for (int i = 0; i < 1000000; i++) {
            num *= Math.random() * 1000;
            num /= Math.random() * 1000;
            if (num == 0)
                num = 1000000;
        }
        return num;
    }

    public int getPriority() {
        return priority;
    }
}