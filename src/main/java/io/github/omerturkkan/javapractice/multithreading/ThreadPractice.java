package io.github.omerturkkan.javapractice.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPractice {
    private static final int THREADS = 4;
    private static final int INCREMENTS_PER_THREAD = 100_000;

    // Shared mutable state: the source of every race condition
    private static int unsafeCounter = 0;
    private static int syncedCounter = 0;
    private static final AtomicInteger atomicCounter = new AtomicInteger();

    private static synchronized void incrementSynced() {
        syncedCounter++;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // A Runnable is just a task; the Thread is what runs it
        Thread worker = new Thread(() -> System.out.printf("  inside %s%n", Thread.currentThread().getName()), "worker-1");
        System.out.println("thread basics:");
        System.out.printf("  state before start : %s%n", worker.getState());
        worker.start();
        worker.join();                       // wait for it to finish
        System.out.printf("  state after join   : %s%n", worker.getState());
        System.out.printf("  main thread        : %s%n", Thread.currentThread().getName());

        // Running several threads over the same counters
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    unsafeCounter++;                 // read-modify-write, not atomic
                    incrementSynced();               // guarded by a lock
                    atomicCounter.incrementAndGet(); // lock-free, still atomic
                }
            }, "counter-" + i);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) thread.join();

        int expected = THREADS * INCREMENTS_PER_THREAD;
        System.out.printf("%nexpected    : %d%n", expected);
        System.out.printf("unsafe      : %d  (lost %d updates)%n", unsafeCounter, expected - unsafeCounter);
        System.out.printf("synchronized: %d  (correct: %b)%n", syncedCounter, syncedCounter == expected);
        System.out.printf("atomic      : %d  (correct: %b)%n", atomicCounter.get(), atomicCounter.get() == expected);

        // An executor reuses a fixed pool instead of creating a thread per task
        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            // Callable returns a value and may throw; Runnable does neither
            List<Callable<String>> jobs = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                int id = i;
                jobs.add(() -> {
                    Thread.sleep(50L * id);          // pretend this is real work
                    return String.format("job-%d finished on %s", id, Thread.currentThread().getName());
                });
            }

            System.out.println("\ninvokeAll waits for every task:");
            for (Future<String> future : pool.invokeAll(jobs)) {
                System.out.println("  " + future.get());
            }

            // submit returns immediately; get() blocks until the result is ready
            Future<Integer> slowSum = pool.submit(() -> {
                int total = 0;
                for (int i = 1; i <= 1000; i++) total += i;
                return total;
            });
            System.out.printf("%nsubmit returned immediately, done: %b%n", slowSum.isDone());
            System.out.printf("get() -> %d, done now: %b%n", slowSum.get(), slowSum.isDone());

            // An exception inside a task surfaces when get() is called
            Future<String> failing = pool.submit(() -> {
                throw new IllegalStateException("task blew up");
            });
            try {
                failing.get();
            } catch (ExecutionException e) {
                System.out.printf("task failure : %s -> %s%n",
                        e.getClass().getSimpleName(), e.getCause().getMessage());
            }
        } finally {
            // A pool keeps the JVM alive until it is shut down
            pool.shutdown();
            boolean finished = pool.awaitTermination(5, TimeUnit.SECONDS);
            System.out.printf("%npool shutdown: %b, terminated: %b%n", pool.isShutdown(), finished);
        }
    }
}
