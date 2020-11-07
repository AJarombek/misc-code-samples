/*
 * Class that increments a single state value in a thread safe manner using an AtomicInteger.  Performs
 * the same logic as ThreadSafeCounter.
 * @author Andrew Jarombek
 * @since 11/6/2020
 */

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger value = new AtomicInteger(0);

    /**
     * Return the value of an integer and then increment it by one.  Retrieving and incrementing the value
     * is performed in an atomic manner (both fully execute or neither has).
     * @return The existing value of the instance variable 'value'.
     */
    public int returnAndIncrement() {
        return value.getAndIncrement();
    }
}
