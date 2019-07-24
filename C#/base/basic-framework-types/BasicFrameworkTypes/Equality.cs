/**
 * Investigate equality among reference types and value types in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 282-]
 * Author: Andrew Jarombek
 * Date: 7/24/2019
 */

using System;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class Equality
    {
        public static void Execute()
        {
            // Value types can only use value equality (==).  This is similar to how Java primitives
            // can only use the == operator.
            int one = 1;
            int two = 2;
            Assert(one != two);
            
            // Unlike Java, int is an alias for Int32, which is a struct.  Therefore, it can use the Equals() method.
            // In Java, int is a primitive value and doesn't have any methods.  To add to the confusion, in C# value
            // types can also be called primitive types since they are handled as basic assembly code
            // instructions [pg. 29]  Value (primitive) types in C# include int, double, char, bool, byte, etc.
            Assert(!one.Equals(two));
            
            // However, Equals() for value types always use value equality unless the type is boxed.
            object objOne = one;
            object objTwo = two;

            int anotherOne = 1;
            object objOneAgain = anotherOne;
            
            Assert(objOne.Equals(objTwo) == false);
            Assert(objOne.Equals(objOneAgain)); // Interestingly, this is true even when boxed.
        }
    }
}