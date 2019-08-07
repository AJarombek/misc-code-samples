/**
 * Investigate the interfaces that make up collections in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 301-313]
 * Author: Andrew Jarombek
 * Date: 7/29/2019
 */

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using static System.Diagnostics.Debug;

namespace collections
{
    /// <summary>
    /// My custom implementation of a List that is enumerable.  It has an inner Enumerator class.
    /// </summary>
    /// <typeparam name="T">Generic type for the internal list.</typeparam>
    class AndyList<T> : IEnumerable<T>, IEnumerable
    {
        private readonly List<T> internalList;

        /// <summary>
        /// Constructor for a list which takes any generic enumerable object as an argument.  This object is converted
        /// to a list structure.
        /// </summary>
        /// <param name="enumerable">An object which implements IEnumerable&lt;T&gt;</param>
        public AndyList(IEnumerable<T> enumerable)
        {
            internalList = enumerable.ToList();
        }

        /// <summary>
        /// Constructor for a list which takes any non-generic enumerable object as an argument.  This object is
        /// converted to a list structure.
        /// </summary>
        /// <param name="enumerable">An object which implements IEnumerable</param>
        public AndyList(IEnumerable enumerable)
        {
            internalList = enumerable.Cast<T>().ToList();
        }
        
        /// <summary>
        /// Get the Enumerator inner class which implements IEnumerator&lt;T&gt;.  The generic version of
        /// GetEnumerator() is implemented explicitly.
        /// </summary>
        /// <returns>An enumerator that implements the IEnumerator&lt;T&gt; interface methods.</returns>
        public IEnumerator<T> GetEnumerator()
        {
            return new Enumerator(internalList);
        }

        /// <summary>
        /// Get the Enumerator inner class which implements IEnumerator.  This is the non-generic version of
        /// GetEnumerator().  Per convention [pg. 307], the non-generic version is implemented implicitly. 
        /// </summary>
        /// <returns>An enumerator that implements the IEnumerator interface methods.</returns>
        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
        
        /// <summary>
        /// An enumerator which is used to iterate over the AndyList class.
        /// </summary>
        public struct Enumerator : IEnumerator<T>, IEnumerator, IDisposable
        {
            private readonly List<T> _list;
            private int _index;
            public T Current { get; private set; }

            /// <summary>
            /// Construct an enumerator for iterating over a list.  The list should be the one held internally
            /// inside the AndyList object instance.
            /// </summary>
            /// <param name="list">The list to iterate over.</param>
            internal Enumerator(List<T> list)
            {
                _list = list;
                _index = 0;
                Current = default(T);
            }
            
            /// <summary>
            /// Move to the next index in the list (if there is another index to navigate to).
            /// </summary>
            /// <returns>
            /// <code>true</code> if the next index is within the bounds of the list, <code>false</code> otherwise.
            /// </returns>
            public bool MoveNext()
            {
                if (_index >= _list.Count)
                {
                    _index = _list.Count + 1;
                    Current = default(T);
                    return false;
                }
                else
                {
                    Current = _list[_index];
                    _index++;
                    return true;
                }
            }

            /// <summary>
            /// Reset the current index in the list to the start of the list and the current value to the generic
            /// type's default value.
            /// </summary>
            public void Reset()
            {
                _index = 0;
                Current = default(T);
            }

            /// <summary>
            /// Retrieve the value at the index the enumerator is currently pointing to.
            /// </summary>
            object IEnumerator.Current => Current;

            /// <summary>
            /// There are no resources to dispose when done iterating over a list, so this method is empty.
            /// </summary>
            public void Dispose() {}
        }
    }
    
    public static class CollectionInterfaces
    {
        public static void Execute()
        {
            // Collections implement the IEnumerable and IEnumerable<T> interfaces.  They specify a single method
            // GetEnumerator(), which returns a class that implements IEnumerable and IEnumerable<T>.  IEnumerable
            // specifies three methods - MoveNext(), Current(), and Reset().
            // Java = [https://bit.ly/2YnRP7Q]
            var list = new List<int> {2, 4, 6};
            IEnumerator<int> enumerator = list.GetEnumerator();
            
            // Current is set to "default(T)" if MoveNext() hasn't been called yet or if MoveNext() is called beyond
            // the last list index.
            Assert(enumerator.Current == 0);
            
            // default(int) is 0, which is the same as default(T) for my generic integer list
            Assert(default(int) == 0);
            
            int count = 0;
            while (enumerator.MoveNext())
            {
                count += enumerator.Current;
            }

            Assert(count == 12);
            Assert(enumerator.Current == 0);
            
            // The IEnumerator implementation ran out of indexes in the list to enumerate over.
            Assert(enumerator.MoveNext() == false);
            
            // The only way to iterate over the list again is to reset the IEnumerator.
            enumerator.Reset();
            bool enumeratorHasNext = enumerator.MoveNext();
            Assert(enumeratorHasNext);
            
            // To dispose of an IEnumerator after use, the "using" code block can be used.
            count = 0;
            using (enumerator)
                while (enumerator.MoveNext())
                {
                    count += enumerator.Current;
                }
            
            Assert(count == 10);
            
            // For type List, the implicit call to Dispose() at the end of the "using" block does nothing.
            Assert(enumerator.MoveNext() == false);
            
            // foreach is a syntactic shortcut for IEnumerator
            count = 0;
            foreach (var item in list)
            {
                count += item;
            }
            Assert(count == 12);
            
            // Since Queue<T> implements IEnumerable<T>, it can be passed to AndyList() as an argument.
            var enumerableQueue = new Queue<int>();
            enumerableQueue.Enqueue(8);
            enumerableQueue.Enqueue(10);
            enumerableQueue.Enqueue(12);
            
            AndyList<int> andyListFromQueue = new AndyList<int>(enumerableQueue);
            IEnumerator<int> andyListEnumerator = andyListFromQueue.GetEnumerator();

            count = 0;
            using (andyListEnumerator)
            {
                while (andyListEnumerator.MoveNext())
                {
                    count += andyListEnumerator.Current;
                }
            }
            Assert(count == 30);
            
            // Countable collections of objects implement the ICollection<T> interface along with
            // its non-generic counterpart ICollection.
            var countableList = new List<int> {2, 4};
            ICollection<int> iCollection = countableList;
            Assert(iCollection.Count == 2);

            ICollection iCollectionNonGeneric = countableList;
            Assert(iCollectionNonGeneric.Count == 2);
            
            // Collections indexed by position implement IList<T> and IList.  IList<T> extends ICollection<T>,
            // IEnumerable<T>, and IEnumerable.  IList extends ICollection and IEnumerable.
            IList<int> iList = countableList;
            IList iListNonGeneric = countableList;
            Assert(iList.IndexOf(2) == 0 && iList[1] == 4);
            Assert(iListNonGeneric.IndexOf(2) == 0 && Equals(iListNonGeneric[1], 4));

            // Setting the compile time type to IReadOnlyList<T> means I can't use methods that modify the list.
            IReadOnlyList<int> _ = countableList;
            
            IDictionary<string, int> linesWritten2019 = new Dictionary<string, int>();
            
            // Top 3 languages of the year so far.  Demonstrate the methods implemented from IDictionary.
            linesWritten2019.Add("Python", 7921);
            linesWritten2019.Add("JavaScript", 3974);
            linesWritten2019.Add("HCL", 3450);
            linesWritten2019.Add("Markdown", 2751);

            linesWritten2019.Remove("Markdown");
            
            var keys = linesWritten2019.Keys;
            var values = linesWritten2019.Values;
            Assert(values.Count == 3 && keys.Count == 3);
            Assert(linesWritten2019["Python"] == 7921);
        }
    }
}