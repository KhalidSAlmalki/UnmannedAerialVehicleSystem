import com.util.concurrent.PriorityExecutorService;

import java.util.concurrent.ThreadLocalRandom;

import static com.util.concurrent.Executors.newPriorityFixedThreadPool;

public class CalculateFibonacci {
    private CalculateFibonacci() {
    }

    public static void main(String[] args) {
        PriorityExecutorService executorService =  newPriorityFixedThreadPool(10);


        for (int i = 50; i < 80; i++) {

            int p = ThreadLocalRandom.current().nextInt(1, 11);
            System.out.println("scheduling  "+p+"  "+i);

            executorService.submit(new Fibonacci(i, p), p);
        }
    }

    private static class Fibonacci implements Runnable {
        int number, priority;

        Fibonacci(int number, int priority) {

            this.number = number;
            this.priority = priority;
        }

        @Override
        public void run() {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("Thread Id "+Thread.currentThread().getId()+" Started calculating Fibonacci for " + number + "!! Priority is " + priority);
            System.out.println("Fibonacci of " + number + " is " + fibonacci(number) + ". Calculated with priority " + priority);
        }

        private int fibonacci(int n){
            if (n <= 1) return n;
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }


}
