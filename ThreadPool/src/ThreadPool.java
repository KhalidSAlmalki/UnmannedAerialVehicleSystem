import java.util.Comparator;
import java.util.LinkedList;

//class PriorityFutureComparator implements Comparator<Runnable> {
//    public int compare(Runnable o1, Runnable o2) {
//        if (o1 == null && o2 == null)
//            return 0;
//        else if (o1 == null)
//            return -1;
//        else if (o2 == null)
//            return 1;
//        else {
//            int p1 = ((PriorityFuture<?>) o1).getPriority();
//            int p2 = ((PriorityFuture<?>) o2).getPriority();
//
//            return p1 > p2 ? 1 : (p1 == p2 ? 0 : -1);
//        }
//    }
//}
public class ThreadPool {
    private WorkerThread[] threads;
    private LinkedList<Runnable> taskQueue;

    public ThreadPool(int threadNumber) {
        taskQueue = new LinkedList<Runnable>();
        threads = new WorkerThread[threadNumber];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    public void enqueue(Runnable r) {
        synchronized (taskQueue) {
            taskQueue.addLast(r);
            taskQueue.notify();
        }
    }

    public class WorkerThread extends Thread {
        public void run() {
            Runnable r;
            while (true) {
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            // ignore
                        }
                    }
                    r = (Runnable) taskQueue.removeFirst();
                }
                try {
                    r.run();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }
}