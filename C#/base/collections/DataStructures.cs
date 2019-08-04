/**
 * Investigate data structures in C# and the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 322-]
 * Author: Andrew Jarombek
 * Date: 8/4/2019
 */

using System.Collections;
using System.Collections.Generic;
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
        }
    }
}