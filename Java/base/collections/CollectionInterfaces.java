package collections;

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
        // C# - []
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
    }
}
