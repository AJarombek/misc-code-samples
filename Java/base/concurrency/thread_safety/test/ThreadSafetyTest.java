/*
 * Test the thread safety approaches written in the source code.
 * @author Andrew Jarombek
 * @since 11/5/2020
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadSafetyTest {

    @Test
    public void testThreadSafeCounter() {
        var counter = new ThreadSafeCounter();

        Runnable increment = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.returnAndIncrement();
            }
        };

        Thread thread1 = new Thread(increment);
        Thread thread2 = new Thread(increment);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            assertEquals(2_000_000, counter.returnAndIncrement());
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    public void testAtomicCounter() {
        var counter = new AtomicCounter();

        Runnable increment = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.returnAndIncrement();
            }
        };

        Thread thread1 = new Thread(increment);
        Thread thread2 = new Thread(increment);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            assertEquals(2_000_000, counter.returnAndIncrement());
        } catch (InterruptedException e) {
            fail();
        }
    }
}
