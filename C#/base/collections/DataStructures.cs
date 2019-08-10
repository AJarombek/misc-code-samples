/**
 * Investigate data structures in C# and the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 322-350]
 * Author: Andrew Jarombek
 * Date: 8/4/2019
 */

using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.Linq;
using System.Text.RegularExpressions;
using static System.Diagnostics.Debug;

namespace collections
{
    
    /// <summary>
    /// Create a custom equality and hash code comparer for string types.  This custom comparer is used with the
    /// Dictionary type.  EqualityComparer implements the generic IEqualityComparer&ltT&gt; interface and non-generic
    /// IEqualityComparer interface.
    /// </summary>
    internal class CaseInsensitiveEqualityComparer : EqualityComparer<string>
    {
        /// <summary>
        /// Override the Equals() method that compares two strings.  Before comparing their values, turn their
        /// characters into lowercase.
        /// </summary>
        /// <param name="x">The first string to compare.</param>
        /// <param name="y">The second string to compare.</param>
        /// <returns>
        /// <code>true</code> if the two strings lowercase values are equal, <code>false</code> otherwise.
        /// </returns>
        public override bool Equals(string x, string y)
        {
            if (x == null || y == null) return false;
            
            // This can be rewritten string.Equals(x.ToLower(), y.ToLower())
            return string.Equals(x, y, StringComparison.CurrentCultureIgnoreCase);
        }

        /// <summary>
        /// Get the hash code of a strings lowercase value.
        /// </summary>
        /// <param name="obj">A string that will be treated in a case insensitive manner.</param>
        /// <returns>The hash code of a strings lowercase value.</returns>
        public override int GetHashCode(string obj)
        {
            return obj.ToLower().GetHashCode();
        }
    }

    /// <summary>
    /// Create a custom comparator between two types.  This comparator is primarily used for sorting a sortable
    /// data structure such as a list.  Comparer&lt;T&gt; implements IComparer and IComparer&lt;T&gt;, which have a
    /// single compare() method.
    /// </summary>
    internal class YarnColorComparer : Comparer<dynamic>
    {
        /// <summary>
        /// Compares the values of two objects whose types aren't known until runtime.  In practice, the type is always
        /// an object literal representing a ball of yarn.  The two balls of yarn are compared on their 'color'
        /// property, which is a string.
        /// </summary>
        /// <param name="x">The first dynamic object (representing yarn) to compare.</param>
        /// <param name="y">The second dynamic object (representing yarn) to compare.</param>
        /// <returns>0 if the two objects are equal, 1 if x is greater than y, -1 if x is less than y.</returns>
        public override int Compare(dynamic x, dynamic y)
        {
            if (Equals(x, y)) return 0;
            return x.color.CompareTo(y.color);
        }
    }
    
    public static class DataStructures
    {
        public static void Execute()
        {
            // The List<T> data structure in C# is similar to the ArrayList<T> data structure in Java.  In Java,
            // List<T> is an interface implemented by ArrayList<T>.  Both List in C# and ArrayList in Java are backed
            // by arrays which are resized when they run out of space.
            
            // C# doesn't support the diamond operator in generic type object definitions like Java.  If it did,
            // the bellow statement would look like the following:
            // var yarns = new List<> {"cotton", "wool", "linen", "silk"};
            // Java - []
            var yarns = new List<string> {"cotton", "wool", "linen", "silk"};
            
            // Appending an item to the end of the list is usually O(1), except for when the underlying array has to
            // be resized.  In that case it's O(n).
            yarns.Add("recycled");
            
            // Inserting an item at an index is expensive (up to O(n)) since every item in the list at a greater or
            // equal index has to be shifted over by one location.  This is the same as Java's ArrayList<T> class.
            yarns.Insert(1, "cashmere");
            
            // Retrieval by index is O(1).
            Assert(yarns[2].Equals("wool"));
            
            // Retrieval by item is O(log n) if the list is sorted, O(n) otherwise.
            var match = yarns.Find(item =>
            {
                var regex = new Regex("[wW].+");
                return regex.IsMatch(item);
            });
            Assert(match.Equals("wool"));
            
            yarns.Sort(); // mutates the list in-place.
            int silkIndex = yarns.BinarySearch("silk");
            Assert(silkIndex == 4);
            
            // ReadOnlyCollection<T> is a proxy to a collection.  It creates a read-only view of that collection.
            ReadOnlyCollection<string> readOnlyYarns = new ReadOnlyCollection<string>(yarns);

            for (var i = 0; i < yarns.Count; i++)
            {
                Assert(readOnlyYarns[i] == yarns[i]);
            }

            // Before C# introduced generics, a class called ArrayList was used for list data structures.  It isn't
            // recommended to use this class anymore.
            ArrayList arrayList = new ArrayList(2);
            arrayList.Add("Hello");
            string firstItem = (string) arrayList[0];
            Assert(firstItem.Equals("Hello"));
            
            // LinkedList<> is a double linked list just like its Java counterpart.  Unlike List<>, inserting into
            // the middle of a linked list is a O(1) operation instead of O(n).
            var linkedList = new LinkedList<string>();
            linkedList.AddFirst("Tod's Point");
            linkedList.AddAfter(linkedList.First, "Mianus River Park");
            linkedList.AddLast("Rockefeller Park");
            linkedList.AddAfter(linkedList.First.Next, "Babcock Preserve");

            // Indexing and searching are O(n) since the list has to be traversed across the links.
            LinkedListNode<string> node = linkedList.First.Next.Next;
            var babcockPreserve = node.Value;
            
            Assert(linkedList.Count == 4);
            Assert(babcockPreserve.Equals("Babcock Preserve"));
            
            // Queue<T> and Stack<T> are internally implemented as resizable arrays.  Usually insertion and deletion
            // operations are O(1) except for when the internal array needs to be resized.  In that case they take O(n).
            var queue = new Queue<int>();
            queue.Enqueue(1);
            Assert(queue.Peek() == 1);
            Assert(queue.Dequeue() == 1);
            Assert(queue.Count == 0);
            
            var tasks = new Stack<string>();
            tasks.Push("Finish Knitting Blanket");
            Assert(tasks.Peek() == "Finish Knitting Blanket");
            Assert(tasks.Pop() == "Finish Knitting Blanket");
            Assert(tasks.Count == 0); // Prove I finished knitting the blanket wedding gift
            
            // A BitArray stores a single bit at each index instead of an entire byte used by booleans
            var bitArray = new BitArray(new [] {true, false});
            Assert(bitArray.Length == 2);
            Assert(bitArray[0] && !bitArray[1]);

            // NOT bitwise operation: ~10 = 01
            bitArray.Not();
            Assert(!bitArray[0] && bitArray[1]);

            // AND bitwise operation: 01 & 10 = 00
            bitArray.And(new BitArray(new [] {true, false}));
            Assert(!bitArray[0] && !bitArray[1]);

            // OR bitwise operation: 00 | 10 = 10
            bitArray.Or(new BitArray(new[] {true, false}));
            Assert(bitArray[0] && !bitArray[1]);

            // XOR bitwise operation: 10 ⊕ 11 = 01
            bitArray.Xor(new BitArray(new[] {true, true}));
            Assert(!bitArray[0] && bitArray[1]);
            
            // C# has two main set data structures - HashSet<t> and SortedSet<T>.  HashSet is backed by a hash table
            // and SortedSet by a red-black tree.
            HashSet<sbyte> sbytes = new HashSet<sbyte> {2, 3, 4};
            SortedSet<byte> bytes = new SortedSet<byte> {5, 6, 7};
            
            // Set Union: {2, 3, 4} ∪ {4, 5} = {2, 3, 4, 5}
            // Use SequenceEqual() because Equals() checks for reference equality.
            sbytes.UnionWith(new sbyte[] {4, 5});
            Assert(sbytes.Count == 4);
            Assert(sbytes.SequenceEqual(new HashSet<sbyte> {2, 3, 4, 5})); 
            
            // Set Intersect: {5, 6, 7} ∩ {6, 7, 8} = {6, 7}
            bytes.IntersectWith(new byte[] {6, 7, 8});
            Assert(bytes.Count == 2);
            Assert(bytes.SequenceEqual(new SortedSet<byte> {6, 7}));
            
            HashSet<ulong> ulongs = new HashSet<ulong> {10, 100, 1_000};
            SortedSet<long> longs = new SortedSet<long> {10_000, 100_000};

            // Subset: {10, 100, 1_000} ⊆ {1, 10, 100, 1_000} = true
            var subsetOf = ulongs.IsSubsetOf(new ulong[] {1, 10, 100, 1_000});
            Assert(subsetOf);
            
            // Proper Subset (not equal sets): {10, 100, 1_000} ⊂ {1, 10, 100, 1_000} = true
            var properSubsetOf = ulongs.IsProperSubsetOf(new ulong[] {1, 10, 100, 1_000});
            Assert(properSubsetOf);
            
            // Proper Subset (not equal sets): {10, 100, 1_000} ⊂ {10, 100, 1_000} = false
            var properSubsetOf2 = ulongs.IsProperSubsetOf(new ulong[] {10, 100, 1_000});
            Assert(!properSubsetOf2);

            // Superset: {10_000, 100_000} ⊇ {10_000} = true
            var supersetOf1 = longs.IsSupersetOf(new long[] {10_000});
            Assert(supersetOf1);
            
            // Superset: {10_000, 100_000} ⊇ {10_000, 100_000} = true
            var supersetOf2 = longs.IsSupersetOf(new long[] {10_000, 100_000});
            Assert(supersetOf2);
            
            // Proper Superset (not equal sets): {10_000, 100_000} ⊃ {10_000} = true
            var properSupersetOf1 = longs.IsProperSupersetOf(new long[] {10_000});
            Assert(properSupersetOf1);
            
            // Proper Superset (not equal sets): {10_000, 100_000} ⊃ {10_000, 100_000} = false
            var properSupersetOf2 = longs.IsProperSupersetOf(new long[] {10_000, 100_000});
            Assert(!properSupersetOf2);
            
            // The main dictionary type (Dictionary<K, V>) in C# uses an underlying hash table.  Average (best) case
            // hash tables are very fast - O(1) insert, O(1) retrieval, O(1) delete.  All operations are O(n)
            // worst case.
            var dict = new Dictionary<DateTime, double>();
            
            dict.Add(new DateTime(2019, 02, 26), 12.31);
            dict.Add(new DateTime(2019, 12, 31), 26.2);
            
            // C# has a number of alternative dictionaries.
            // Hashtable is the legacy and non-generic version of Dictionary<K,V>.
            Hashtable _ = new Hashtable();
            
            // OrderedDictionary is a combination of a hash table and array.  It maintains the order in which items
            // are added to the dictionary.  This comes at a small performance and memory usage penalty.
            OrderedDictionary orderedDictionary = new OrderedDictionary();
            orderedDictionary.Add(2014, "Java");
            orderedDictionary.Add(2015, "Java");
            orderedDictionary.Add(2016, "Java");
            orderedDictionary.Add(2017, "Java");
            orderedDictionary.Add(2018, "JavaScript");
            orderedDictionary.Add(2019, "Python");

            var first = orderedDictionary[0];
            var last = orderedDictionary[orderedDictionary.Count - 1];
            Assert(first.Equals("Java") && last.Equals("Python"));
            
            // ListDictionary is backed by a singly linked list.  It is very slow except for lists of size < 10.
            ListDictionary __ = new ListDictionary();
            
            // SortedDictionary<K, V> is always sorted by key and is backed by a red-black tree.  Therefore its
            // retrieval and insertion times are O(log n).  That performance hit is worthwhile if sorted order
            // is crucial.
            SortedDictionary<int, string> agileSprintTasks = new SortedDictionary<int, string>();
            agileSprintTasks.Add(1, "C# Code Samples Pg. 325-250");
            agileSprintTasks.Add(3, "Publish Haskell Applicatives Article to jarombek.com");
            agileSprintTasks.Add(2, "Proof Read Haskell Applicatives Article");

            // Prove the dictionary key -> value pairs are sorted by the integer key ascending.
            var it = agileSprintTasks.GetEnumerator();
            it.MoveNext();
            Assert(it.Current.Value.Equals("C# Code Samples Pg. 325-250"));
            it.MoveNext();
            Assert(it.Current.Value.Equals("Proof Read Haskell Applicatives Article"));
            it.MoveNext();
            Assert(it.Current.Value.Equals("Publish Haskell Applicatives Article to jarombek.com"));
            it.Dispose();
            
            // Dictionaries can be searched with custom equality logic.
            var animals = new Dictionary<string, string>();
            animals["dotty"] = "horse";
            animals["lily"] = "bear";
            
            // Dictionary string keys are case sensitive by default.
            Assert(animals.ContainsKey("dotty"));
            Assert(!animals.ContainsKey("Dotty"));

            var caseInsensitiveEqualityComparer = new CaseInsensitiveEqualityComparer();
            var animalsCaseInsensitive = new Dictionary<string, string>(animals, caseInsensitiveEqualityComparer);
            
            // With the custom equality comparer, dictionary string keys are case insensitive.
            Assert(animalsCaseInsensitive.ContainsKey("dotty"));
            Assert(animalsCaseInsensitive.ContainsKey("Dotty"));

            // Data structures can be sorted with a custom comparator as well.
            // Create yarn objects with C# object literal notation.
            var yarn1 = new { id = 1, fiber = "Polyester", color = "Pitter Patter", yards = 220 };
            var yarn2 = new { id = 2, fiber = "Polyester", color = "Baby Lilac", yards = 220 };
            var yarn3 = new { id = 3, fiber = "Polyester", color = "Vanilla", yards = 70 };
            
            var yarnList = new List<object> {yarn1, yarn2, yarn3};

            // Sorting an object literal without a custom comparator throws an exception.
            try
            {
                yarnList.Sort();
                Assert(false);
            }
            catch (InvalidOperationException ex)
            {
                Assert(true);
            }

            // Using the custom comparator sorts the object literals by their 'color' string.
            yarnList.Sort(new YarnColorComparer());
            Assert(yarnList[0].Equals(yarn2) && yarnList[1].Equals(yarn1) && yarnList[2].Equals(yarn3));
        }
    }
}