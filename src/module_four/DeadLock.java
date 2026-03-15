package module_four;


public class DeadLock {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("[thread-1] Захватил lockA");
                sleep(100);

                System.out.println("[thread-1] Ожидает lockB...");
                synchronized (lockB) {
                    System.out.println("[thread-1] Захватил lockB");
                }
            }
        }, "thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("[thread-2] Захватил lockB");
                sleep(100);

                System.out.println("[thread-2] Ожидает lockA...");
                synchronized (lockA) {
                    System.out.println("[thread-2] Захватил lockA");
                }
            }
        }, "thread-2");

        thread1.setDaemon(true);
        thread2.setDaemon(true);
        
        thread1.start();
        thread2.start();

        sleep(3000);
        System.out.println("\nПрошло 3 секунды - DEADLOCK подтверждён");
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
