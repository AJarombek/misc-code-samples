/*
 * @author Andrew Jarombek
 * @since 11/5/2020
 */

@ThreadSafe
public class ThreadSafeCounter {
    private int value;

    public synchronized int incrementAndReturn() {
        return value++;
    }
}
