import com.util.concurrent.PriorityExecutorService;

import java.util.concurrent.ThreadLocalRandom;

import static com.util.concurrent.Executors.newPriorityFixedThreadPool;

public class CalculateFibonacci {
    private CalculateFibonacci() {
    }

    public static void main(String[] args) {
        PriorityExecutorService executorService =  newPriorityFixedThreadPool(10);

        System.out.println(Thread.MIN_PRIORITY);
        System.out.println(Thread.MAX_PRIORITY);

        for (int i = 10; i < 25; i++) {
            int p = ThreadLocalRandom.current().nextInt(1, 11);
            executorService.submit(new Fibonacci(i * 4, p), p);
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
            System.out.println("Started calculating Fibonacci for " + number + "!! Priority is " + priority);
            System.out.println("Fibonacci of " + number + " is " + fibonacci(number) + ". Calculated with priority " + priority);
        }

        private int fibonacci(int n){
            if (n <= 1) return n;
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }


}
