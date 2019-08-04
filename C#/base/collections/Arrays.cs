/**
 * Investigate Arrays in C#
 * Sources: [C# 7.0 In a Nutshell: Page 313-321]
 * Author: Andrew Jarombek
 * Date: 8/3/2019
 */

using System;
using static System.Diagnostics.Debug;

namespace collections
{
    public static class Arrays
    {
        public static void Execute()
        {
            // Array is a class in C#.  When initialized, its given a contiguous slice of memory.  Just like Java,
            // C# arrays can't be resized once initialized.
            int[] intArray = {1, 2, 3};
            Assert(intArray.Length == 3);
            
            // Shallow clone an array.
            int[] intArray2 = (int[]) intArray.Clone();
            
            // Distinct arrays fail equality tests, even Equals() for value equality.
            Assert(!intArray.Equals(intArray2) && intArray != intArray2);
            
            // The static Array.CreateInstance() static factory method can also be used to create an array.
            // While this proves that Array is a class, the built-in array creation expression should be used instead.
            Array doubleArray = Array.CreateInstance(typeof(double), 2);
            doubleArray.SetValue(2.2, 0);
            
            Assert((double) doubleArray.GetValue(0) == 2.2 && (double) doubleArray.GetValue(1) == 0.0);
            
            // Cast Array to double[]
            double[] doubles = (double[]) doubleArray;
            Assert(doubles[0] == 2.2 && doubles[1] == 0.0);

            // Arrays have a static ForEach() method for iteration.  You can also use a for-each loop.
            var count = 0;
            Array.ForEach(intArray, i => count += i);
            Assert(count == 6);
            
            // Perform an o(log n) binary search on a sorted array.  There is also a Find() method for un-sorted arrays.
            int index = Array.BinarySearch(intArray, 2);
            Assert(index == 1);

            // Sort the integer array in-place (mutate) so the integers are in descending order.
            Array.Sort(intArray, (x, y) => x == y ? 0 : x < y ? 1 : -1);
            Assert(intArray[0] == 3 && intArray[2] == 1);
        }
    }
}