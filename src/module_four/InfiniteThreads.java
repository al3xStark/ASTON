package module_four;


public class InfiniteThreads {

    private static final Object lock = new Object();

    private static volatile int turn = 1;

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (turn != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    System.out.println("1");

                    turn = 2;
                    lock.notifyAll();
                }
            }
        }, "thread-1");

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (turn != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    System.out.println("2");

                    turn = 1;
                    lock.notifyAll();
                }
            }
        }, "thread-2");


        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nДемонстрация завершена.");
    }
}
