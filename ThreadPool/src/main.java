import com.util.concurrent.PriorityExecutorService;

import java.util.LinkedList;

import static com.util.concurrent.Executors.newPriorityFixedThreadPool;


public class main {

    private LinkedList<Runnable> taskQueue;

    public static void main(String[] args) throws InterruptedException {

        PriorityExecutorService s =  newPriorityFixedThreadPool(10);

        for(int i=0;i<10;i++)
            s.submit(new TestThread(10), 10);

        for(int i=0;i<10;i++)
            s.submit(new TestThread(5), 5);
//
//        while (true){
//
//
//
//
//            for(int i=0;i<10;i++)
//                s.submit(new TestThread(3), 3);
//
//               Thread.sleep(10000);
//        }



    }
    private static class TestThread implements Runnable
    {
        int priority;
        TestThread(int priority)
        {
            this.priority = priority;
        }
        @Override
        public void run()
        {
            System.out.println("Thread Id: "+Thread.currentThread().getId()+"| Original Task Priority: "+priority+"| Current Task priority: "+Thread.currentThread().getPriority());

            try
            {
                Thread.sleep((long)(Math.random() * 1000));
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }


}
