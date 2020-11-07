/*
 * Class that increments a single state value in a thread safe manner using the synchronized keyword.
 * @author Andrew Jarombek
 * @since 11/5/2020
 */

@ThreadSafe
public class ThreadSafeCounter {
    @GuardedBy("this") private int value;

    /**
     * Return the value of an integer and then increment it by one.  This function synchronized access
     * to the 'value' variable across multiple threads.
     * @return The existing value of the instance variable 'value'.
     */
    public synchronized int returnAndIncrement() {
        return value++;
    }
}
