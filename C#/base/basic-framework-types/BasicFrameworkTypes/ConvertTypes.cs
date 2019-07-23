/**
 * Investigate the parsing and formatting types found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 270-]
 * Author: Andrew Jarombek
 * Date: 7/20/2019
 */

using System;
using System.Linq;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class ConvertTypes
    {
        public static void Execute()
        {
            // Use Convert.ToBoolean to determine truthy and falsy values
            Assert(Convert.ToBoolean("true"));
            Assert(Convert.ToBoolean(1));
            Assert(!Convert.ToBoolean(0));

            // Convert an object between types.  The new type isn't known until runtime.
            Type doubleType = typeof(double);
            var bdayString = "2.26";
            
            // At compile time, the changed type is object.  At runtime, it will be double
            object bdayDouble = Convert.ChangeType(bdayString, doubleType);
            Assert(object.Equals(bdayDouble, 2.26)); // object can be omitted
            Assert(bdayDouble.GetType() == typeof(double));
            Assert(bdayDouble is double);
            
            // Convert a type to bytes.  Integer conversions are simple
            byte[] int16Byte = BitConverter.GetBytes((Int16) 2);
            byte[] int32Byte = BitConverter.GetBytes(2); // Integers default to Int32
            byte[] int64Byte = BitConverter.GetBytes((Int64) 2);
            Assert(int16Byte.Length == 2);
            Assert(int32Byte.Length == 4);
            Assert(int64Byte.Length == 8);
            Assert(int16Byte[0] == 2 && int16Byte[1] == 0);
            
            // Double conversions are more complex.  They are stored in 8 bytes (64 bits) using the IEEE standard.
            // https://en.wikipedia.org/wiki/Double-precision_floating-point_format
            byte[] zeroBytes = BitConverter.GetBytes(0.0);
            byte[] oneBytes = BitConverter.GetBytes(1.0);
            byte[] twoBytes = BitConverter.GetBytes(2.0);
            byte[] twoPointTwoBytes = BitConverter.GetBytes(2.2);
            string zeroByteString = "";
            string oneByteString = "";
            string twoByteString = "";
            string twoPointTwoByteString = "";
            
            foreach (var i in Enumerable.Range(0, 8))
            {
                zeroByteString += $"{zeroBytes[i]} ";
                oneByteString += $"{oneBytes[i]} ";
                twoByteString += $"{twoBytes[i]} ";
                twoPointTwoByteString += $"{twoPointTwoBytes[i]} ";
            }
            Assert(zeroByteString.Equals("0 0 0 0 0 0 0 0 "));
            Assert(oneByteString.Equals("0 0 0 0 0 0 240 63 "));
            Assert(twoByteString.Equals("0 0 0 0 0 0 0 64 "));
            Assert(twoPointTwoByteString.Equals("154 153 153 153 153 153 1 64 "));
        }
    }
}