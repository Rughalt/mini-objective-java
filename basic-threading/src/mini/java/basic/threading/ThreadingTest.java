package mini.java.basic.threading;

import mini.java.basic.interfaces.test.StringRandomizer;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadingTest {
    final Object sync = new Object();

    @org.junit.Test
    public void streamThreading() {
        ArrayList<String> unsafeArrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            unsafeArrayList.add("A");
        }
        // Nie polecam
        unsafeArrayList.parallelStream().forEach(p -> {
            System.out.println(p);
        });
    }
    @org.junit.Test
    public void basicThreading() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            Runnable sleepThread = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
                    synchronizationTest();
                }
            };
            executorService.submit(sleepThread);
        }
        awaitTerminationAfterShutdown(executorService);
        System.out.println("Reached end of method");
    }

    private void synchronizationTest() {
        synchronized (sync) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }

    class CallableWithParameter implements Callable<String> {

        @Override
        public String call() throws Exception {
            return null;
        }
    }

    @org.junit.Test
    public void callableTest() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ArrayList<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Callable<String> callable = new Callable<String>() {
                StringRandomizer stringRandomizer = new StringRandomizer();
                @Override
                public String call() throws Exception {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        throw new IllegalStateException(ex);
                    }
                    return stringRandomizer.next(10);
                }
            };
            callables.add(callable);
        }
        var futures = executorService.invokeAll(callables);
        awaitTerminationAfterShutdown(executorService);
        System.out.println("Reached end of thread");
        for (Future<String> stringFuture : futures) {
            System.out.println(stringFuture.get());
        }
    }

    public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @org.junit.Test
    public void unsafeCollectionAdd() {
        ArrayList<String> unsafeArrayList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            Runnable addItemsThread = new Runnable() {
                @Override
                public void run() {
                    StringRandomizer stringRandomizer = new StringRandomizer();
                    for (int i = 0; i < 100; i++) {
                        unsafeArrayList.add(stringRandomizer.next(5));
                    }
                }
            };
            executorService.submit(addItemsThread);
        }
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Reached end of method");
    }

    @org.junit.Test
    public void unsafeCollectionDelete() {
        StringRandomizer stringRandomizer = new StringRandomizer();
        ArrayList<String> unsafeArrayList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            unsafeArrayList.add(stringRandomizer.next(5));
        }

        for (String s : unsafeArrayList) {
            unsafeArrayList.remove(s);
        }


        System.out.println("Reached end of method");
    }

    /***
     * Przechwytywanie sygnału stop oraz wychodzenie z wątku
     */
    @org.junit.Test
    public void handledInterruptException() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        var runnables = new ArrayList<Runnable>();
        for (int i = 0; i < 10; i++) {
            Runnable iterationsRunnable = new Runnable() {
                @Override
                public void run() {
                    StringRandomizer stringRandomizer = new StringRandomizer();
                    for (int i = 0; i < 10000; i++) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            // We got forcibly interrupted while working
                            System.out.println("Received interrupt signal, wait got interrupted, exiting");
                            return;
                        }
                        // We checked if we got intterupt signal
                        if (Thread.currentThread().isInterrupted()) {
                            System.out.println("Received interrupt signal, exiting");
                            return;
                        }
                    }
                    System.out.println("Finished");
                }
            };
            runnables.add(iterationsRunnable);
        }
        // Wait 1 second, then stop all threads
        try {
            runnables.forEach(r -> executorService.submit(r));
            TimeUnit.SECONDS.sleep(1);
            executorService.shutdownNow();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void handledInterruptNoException() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        var runnables = new ArrayList<Runnable>();
        for (int i = 0; i < 10; i++) {
            // This should run long enough that it will be interrupted
            Runnable iterationsRunnable = new Runnable() {
                @Override
                public void run() {
                    StringRandomizer stringRandomizer = new StringRandomizer();
                    for (int i = 0; i < 1000000000; i++) {
                        atomicInteger.incrementAndGet();
                        // We checked if we got intterupt signal
                        if (Thread.currentThread().isInterrupted()) {
                            System.out.println("Received interrupt signal, exiting");
                            return;
                        }
                    }
                    System.out.println("Finished");
                }
            };
            runnables.add(iterationsRunnable);
        }
        // Wait 1 second, then stop all threads
        try {
            runnables.forEach(r -> executorService.submit(r));
            TimeUnit.SECONDS.sleep(1);
            executorService.shutdownNow();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
