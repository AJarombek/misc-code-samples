package collections;

import java.util.*;
import java.util.function.Consumer;

/**
 * Create a custom List class which implements Iterable<T>.
 * @author Andrew Jarombek
 * @since 7/31/2019
 */

class AndyList<T> implements Iterable<T> {

    private final List<T> internalList;

    /**
     * Construct a custom list representation.  Stores a list internally using object composition.
     * @param iterable takes in any iterable as an argument and coverts it into a list.
     */
    public AndyList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        internalList = list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new AndyListIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        // Same behavior as the default method.
        Objects.requireNonNull(action);
        for (T item: this) {
            action.accept(item);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Spliterator<T> spliterator() {
        return new AndyListSpliterator<>(internalList, 0, internalList.size());
    }

    /**
     * Static inner class for a custom spliterator implementation for AndyList.
     * @param <T> the generic type of the list the spliterator acts upon.
     */
    private static class AndyListSpliterator<T> implements Spliterator<T> {

        // List to iterate over.  Passed in through the constructor.
        final List<T> list;

        // The current index being iterated on.
        int origin;

        // One past the greatest index in the list.  The size() of the list.
        int fence;

        AndyListSpliterator(List<T> list, int origin, int fence) {
            this.list = list;
            this.origin = origin;
            this.fence = fence;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            if (origin < fence) {
                action.accept(list.get(origin));
                origin =+ 1;
                return true;
            } else {
                return false;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Spliterator<T> trySplit() {
            int low = origin;
            int high = fence;
            int mid = (low + high) >>> 1; // Divide the lowest index plus the highest index in the spliterator in half
            if (low < mid) {
                origin = mid;
                return new AndyListSpliterator<>(list, low, mid);
            } else {
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long estimateSize() {
            return (fence - origin) / 2;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int characteristics() {
            return IMMUTABLE | SIZED;
        }
    }

    /**
     * Non-static inner class which represents an iterator over the list.  Since the class isn't static,
     * it's able to access AndyList object instance variables.
     */
    private class AndyListIterator implements Iterator<T> {

        int index = 0;

        /**
         * Explicitly show that AndyListIterator has a default empty constructor.  It accesses the instance variables
         * of its parent object directly.
         */
        AndyListIterator() {}

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return index < internalList.size();
        }

        /**
         * Simplified version of {@code next()} that doesn't take into consideration concurrent programming.
         *
         * {@inheritDoc}
         */
        @Override
        public T next() {
            if (index >= internalList.size()) {
                throw new NoSuchElementException();
            }

            T current = internalList.get(index);
            index++;
            return current;
        }

        /**
         * This Iterator does not support the {@code remove()} method.
         *
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        /**
         * Simplified version of {@code forEachRemaining()} that doesn't take into consideration concurrent programming.
         *
         * {@inheritDoc}
         */
        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            // If the action lambda is null, throw a NullPointerException.
            Objects.requireNonNull(action);
            if (index < internalList.size()) {
                for (int i = index; i < internalList.size(); i++) {
                    action.accept(internalList.get(i));
                }
            }
        }
    }
}
