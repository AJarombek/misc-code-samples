/**
 * Create and test an unsorted array data structure.
 * @author Andrew Jarombek
 * @since 11/2/2019
 */

public class UnsortedArray<T> {
    private int capacity;
    private int size;
    private T[] content;

    public UnsortedArray() {
        capacity = 0;
        size = 0;
        content = null;
    }

    public UnsortedArray(int length) {
        capacity = length;
        size = 0;
        content = (T[]) new Object[length];
    }
}
