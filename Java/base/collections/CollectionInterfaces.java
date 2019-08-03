package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

/**
 * Revisiting the interfaces which make up the collections framework.
 * @author Andrew Jarombek
 * @since 7/31/2019
 */

class CollectionInterfaces {
    static void execute() {
        // Collections implement the Iterable<T> interface and specify iterator(), forEach(),
        // and spliterator() methods.  iterator() returns an instance that implements Iterator<T> which is used
        // for iterating over a collection.
        // C# - [https://bit.ly/2T4k2KN]
        List<Integer> list = List.of(2, 4, 6);
        Iterator<Integer> it = list.iterator();

        var count = 0;
        while (it.hasNext()) {
            count += it.next();
        }
        assert count == 12;

        List<Float> floatList = List.of(2.0f, 2.1f, 2.2f, 2.3f, 2.4f);
        Spliterator<Float> spliterator = floatList.spliterator();

        // Get the characteristics of a List<T> spliterator.
        int characteristics = spliterator.characteristics();
        assert characteristics == (Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED);

        long estimatedSize = spliterator.estimateSize();
        assert estimatedSize == 5;

        int iterations = 0;
        while (spliterator.tryAdvance((item) -> System.out.println("Advancing past " + item))) {
            iterations++;
        }
        assert iterations == 5;

        // Splitting up the workload.
        var split1 = floatList.spliterator();
        var split2 = split1.trySplit();

        iterations = 0;
        while (split1.tryAdvance((item) -> System.out.println("Split #1 Advancing past " + item))) {
            iterations++;
        }
        assert iterations == 3;

        iterations = 0;
        while (split2.tryAdvance((item) -> System.out.println("Split #2 Advancing past " + item))) {
            iterations++;
        }
        assert iterations == 2;

        // Working with the custom AndyList and its inner Iterator and Spliterator classes.
        AndyList<Integer> andyList = new AndyList<>(List.of(1,2,3,4));
        Iterator<Integer> andyListIterator = andyList.iterator();

        // AndyList iterator works just like a regular List iterator.
        count = 0;
        while (andyListIterator.hasNext()) {
            count += andyListIterator.next();
        }
        assert count == 10;

        try {
            // This always throws an error
            andyListIterator.remove();
            assert false;
        } catch (UnsupportedOperationException e) {
            assert true;
        }

        // Construct a new list using the forEach() method in AndyList
        var andyListDuplicate = new ArrayList<>();
        andyList.forEach(andyListDuplicate::add);
        assert andyListDuplicate.size() == 4;

        // Append to the new list using the forEachRemaining() method in the AndyListIterator
        andyListIterator = andyList.iterator();
        andyListIterator.forEachRemaining(andyListDuplicate::add);
        assert andyListDuplicate.size() == 8;

        // Finally Java provides a syntactical shortcut for iterators with the for-each loop
        for (var item : andyList) {
            andyListDuplicate.add(item);
        }
        assert andyListDuplicate.size() == 12;

        // Test out the custom AndyListSpliterator designed for the AndyList class
        Spliterator<Integer> andyListSpliterator = andyList.spliterator();

        assert andyListSpliterator.estimateSize() == 4;
        assert andyListSpliterator.characteristics() == (Spliterator.IMMUTABLE | Spliterator.SIZED);

        iterations = 0;
        while (andyListSpliterator.tryAdvance((item) -> System.out.println("AndyList Advancing past " + item))) {
            iterations++;
        }
        assert iterations == 4;

        var andyListSplit1 = andyList.spliterator(); // mid-high (indexes 2-3)
        var andyListSplit2 = andyListSplit1.trySplit(); // low-mid (indexes 0-1)

        iterations = 0;
        while (andyListSplit1.tryAdvance((item) -> System.out.println("AndyList Split #1 Advancing past " + item))) {
            iterations++;
        }
        assert iterations == 2;

        iterations = 0;
        while (andyListSplit2.tryAdvance((item) -> System.out.println("AndyList Split #2 Advancing past " + item))) {
            iterations++;
        }
        assert iterations == 2;
    }
}
