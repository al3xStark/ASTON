package module_four;

import java.util.concurrent.atomic.AtomicBoolean;

public class LiveLock {

    private static final AtomicBoolean thread1WantsResource = new AtomicBoolean(false);
    private static final AtomicBoolean thread2WantsResource = new AtomicBoolean(false);

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true) {
                thread1WantsResource.set(true);

                while (thread2WantsResource.get()) {
                    System.out.println("[thread-1] Уступает...");
                    thread1WantsResource.set(false);
                    // sleep(50);
                    thread1WantsResource.set(true);
                }

                thread1WantsResource.set(false);
            }
        }, "thread-1");

        Thread thread2 = new Thread(() -> {
            while (true) {
                thread2WantsResource.set(true);

                while (thread1WantsResource.get()) {
                    System.out.println("[thread-2] Уступает...");
                    thread2WantsResource.set(false);
                    // sleep(50);
                    thread2WantsResource.set(true);
                }

                thread2WantsResource.set(false);
            }
        }, "thread-2");

        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        sleep(4000);
        System.out.println("\nПрошло 4 секунды - LIVELOCK подтверждён.");
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}