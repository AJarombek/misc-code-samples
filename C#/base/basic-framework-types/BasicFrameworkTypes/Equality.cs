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
    /// <summary>
    /// Class representing a ball of yarn.
    /// </summary>
    class Yarn : IEquatable<Yarn>
    {
        public readonly int Length;
        public readonly YarnWeight Weight;

        public Yarn(int length, YarnWeight weight)
        {
            this.Length = length;
            this.Weight = weight;
        }
        
        public enum YarnWeight
        {
            Lace = 0, SuperFine = 1, Fine = 2, LightWeight = 3, 
            Medium = 4, Bulky = 5, SuperBulky = 6, Jumbo = 7
        }
        
        public override bool Equals(object obj)
        {
            // Shorthand without casting for: obj is Yarn && Equals((Yarn) obj)
            return obj is Yarn yarn && Equals(yarn);
        }

        public bool Equals(Yarn other) => Length == other.Length && Weight.Equals(other.Weight);

        public override int GetHashCode()
        {
            var hash = 17;
            hash = hash * 31 + Length.GetHashCode();
            hash = hash * 31 + Weight.GetHashCode();
            return hash;
        }
    }
    
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
            
            Assert(objOne != objTwo);
            Assert(objOne.Equals(objTwo) == false);
            
            Assert(objOne != objOneAgain); // Since the integers are boxed, == checks for reference equality.
            Assert(objOne.Equals(objOneAgain)); // And Equals() checks for value equality.
            
            // The differences between Equals() and == aren't as straightforward in C# as they are in Java.
            // For example, with the URI reference type == checks for value equality.  This is because C# allows for
            // operator overloading.  Java does not permit operator overloading.
            Uri jarombekCom = new Uri("https://jarombek.com");
            Uri jarombekCom2 = new Uri("https://jarombek.com");
            Assert(jarombekCom == jarombekCom2); // If this was reference equality, it would return false.
            
            // Similar to Java, object has a static method for computing null safe equality with the Equals() method.
            object obj1 = null;
            object obj2 = new object();

            try
            {
                // This command should always fail with a NullReferenceException
                obj1.Equals(obj2);
                
                Assert(false);
            }
            catch (NullReferenceException e)
            {
                Assert(true);
            }
            
            // The static method is null-safe.  However, it boxes value types, making it a
            // costly operation performance wise.  For basic types, creating a similar static Equals method with
            // the IEquatable interface is a faster workaround.
            bool isEqual = object.Equals(obj1, obj2);
            bool isRefEqual = ReferenceEquals(obj1, obj2);
            Assert(!isEqual && !isRefEqual);
            
            // Yarn is a custom class which implements IEquatable<T>.  It also overrides object.Equals().
            Yarn pinkYarn = new Yarn(210, Yarn.YarnWeight.SuperBulky);
        }
    }
}