/**
 * Investigate Unsafe Code and Pointers in C#
 * Author: Andrew Jarombek
 * Date: 5/30/2019
 */

using static System.Diagnostics.Debug;

namespace Unsafe
{
    class Program
    {
        // Pointers are not permitted on reference types, so all the types in a struct must be primitive types.
        // For example, to use the Run struct with pointers, none of the types can be 'string' since its a reference
        // type.  Primitive types in this context are referred to as unmanaged-types.
        // Source: [https://stackoverflow.com/a/42155049]
        struct Run
        {
            public double Miles;
            public int Minutes;
            public int Seconds;
        }
        
        static void Main(string[] args)
        {
            // The unsafe code block permits the use of pointer types and pointer operations
            unsafe
            {
                var array = new int[] {5, 6, 7};
                
                // The fixed statement pins an object to an address in the heap.  Otherwise, it may get moved around.
                fixed (int* ap = array)
                {
                    // Loop through an array using a pointer to the array index addresses
                    for (var i = 0; i < array.Length; i++)
                    {
                        Assert(ap[i] == i + 5);
                    }
                }
            }

            // Demonstrate working with structs & pointers & the pointer-to-member operator
            unsafe
            {
                var me = new Run {Miles = 4.0, Minutes = 29, Seconds = 0};
                Run* p = &me;
                Assert(p->Minutes + p->Seconds == 29);
            }
        }
    }
}