package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Comparing Java's threading functionality to languages I'm learning.
 * @author Andrew Jarombek
 * @since 10/4/2019
 */

class Threading {

    private static int count;
    private static final Lock LOCK = new ReentrantLock();

    static void executeBasicThread() {
        var thread = new Thread(() -> print('1'));
        assert !thread.isAlive();
        thread.start();

        print('0');
    }

    /**
     * Function called by a thread which prints out a character 50 times.
     * @param c The character to print out.
     */
    private static void print(char c) {
        for (int i = 0; i < 50; i++)
        {
            System.out.print(c);
        }
    }

    static void executeThread() {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                assert false;
            }
        });

        thread.start();
        assert thread.isAlive();

        try {
            thread.join();
            assert !thread.isAlive();
        } catch (InterruptedException e) {
            assert false;
        }

        // This thread isn't safe because it mutates the 'count' variable which is shared with other threads.
        Thread dangerousThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) { count++; }
        });

        dangerousThread.start();
        for (int i = 0; i < 10000; i++) { count++; }

        try {
            dangerousThread.join();

            // This won't always print 20000.
            System.out.println("\nUnsafe Count: " + count);
        } catch (InterruptedException e) {
            assert false;
        }

        count = 0;

        // This example performs the same operation in a thread-safe manner with a re-entrant locking object
        Thread safeThread = new Thread(Threading::add);
        safeThread.start();
        add();

        try {
            safeThread.join();
            System.out.println("Safe Count: " + count);
        } catch (InterruptedException e) {
            assert false;
        }
    }

    /**
     * Increment a shared integer in a thread-safe manner using a re-entrant lock.
     */ 
    private static void add() {
        for (int i = 0; i < 10000; i++) {
            LOCK.lock();
            count++;
            LOCK.unlock();
        }
    }
}
