/**
 * Investigate data structures in C# and the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 322-]
 * Author: Andrew Jarombek
 * Date: 8/4/2019
 */

using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using static System.Diagnostics.Debug;

namespace collections
{
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
        }
    }
}