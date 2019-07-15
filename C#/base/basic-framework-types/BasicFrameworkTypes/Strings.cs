/**
 * Investigate the basic String type found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 229-]
 * Author: Andrew Jarombek
 * Date: 7/14/2019
 */

using System;
using System.Globalization;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class Strings
    {
        public static void Execute()
        {
            // char is an Alias for System.Char.  Characters are 16 bits (UTF-16 encoded)
            char c1 = 'a';
            System.Char c2 = 'a';
            Assert(c1 == c2);
            
            // Unlike Java, characters aren't primitives.  They have methods and static functions.
            Assert(char.IsLetterOrDigit(c1));
            Assert(c1.ToString().Equals("a"));

            // Culture rules are applied to convert characters to uppercase or lowercase.  This is because in different
            // languages, uppercase letters can be different.  CurrentCulture is determined by the host machine, while
            // InvariantCulture is english.
            char c3 = char.ToUpper(c1, CultureInfo.CurrentCulture);
            char c4 = char.ToUpper(c1, CultureInfo.InvariantCulture);
            Assert(c3 == c4 && c4 == 'A');
            
            // string is an Alias for System.String
            string s1 = "andy";
            System.String s2 = "andy";
            Assert(s1.Equals(s2));

            unsafe
            {
                
            }
        }
    }
}