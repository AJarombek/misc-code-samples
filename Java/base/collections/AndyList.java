package collections;

import java.util.*;
import java.util.function.Consumer;

/**
 * Create a custom List class which implements Iterable<T>.
 * @author Andrew Jarombek
 * @since 7/31/2019
 */

class AndyList<T> implements Iterable<T> {

    private List<T> internalList;

    public AndyList(Iterable<T> iterable) {
        internalList = new ArrayList<>();
        iterable.forEach(internalList::add);
    }

    @Override
    public Iterator<T> iterator() {
        return new AndyListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    private class AndyListIterator implements Iterator<T> {

        int index = 0;

        AndyListIterator() {}

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            if (index >= internalList.size()) {
                throw new NoSuchElementException();
            }

            T current = internalList.get(index);
            index++;
            return current;
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {

        }
    }
}
