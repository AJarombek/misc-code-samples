/**
 * Investigate number types found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 276-278]
 * Author: Andrew Jarombek
 * Date: 7/23/2019
 */

using System;
using System.Numerics;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class Numbers
    {
        public static void Execute()
        {
            // Similar to Java, big numbers are handled with the BigInteger class
            var quadrillion = BigInteger.Pow(10, 18);
            Assert(quadrillion.ToString("N0").Equals("1,000,000,000,000,000,000"));
            
            // .NET 4.0 introduced the ability to work with complex numbers
            Complex complex = new Complex(3, 4);
            Assert(complex.Real == 3);
            Assert(complex.Imaginary == 4);
            
            // Random numbers with the same seed will produce the same result value.
            // Different seeds will not guarantee the same value.
            var random1 = new Random(1); // 1 is the seed
            var random2 = new Random(1);
            
            Assert(random1.Next() == random2.Next());
            
            // Random() isn't very secure.  For programs in need of beefed-up security, use the following class:
            var secureRandom = System.Security.Cryptography.RandomNumberGenerator.Create();
            
            // To get random numbers, a byte array is filled
            byte[] randomBytes = new byte[32];
            secureRandom.GetBytes(randomBytes);
            Console.WriteLine(string.Join(",", randomBytes));
        }
    }
}