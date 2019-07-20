/**
 * Investigate the parsing and formatting types found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 270-]
 * Author: Andrew Jarombek
 * Date: 7/20/2019
 */

using System;
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
        }
    }
}