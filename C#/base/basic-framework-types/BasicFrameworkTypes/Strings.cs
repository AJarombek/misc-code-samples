/**
 * Investigate the basic String type found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 229-242]
 * Author: Andrew Jarombek
 * Date: 7/14/2019
 */

using System.Globalization;
using System.IO;
using System.Text;
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

            // strings can be constructed from pointers to characters
            unsafe
            {
                char[] charArray = new char[] {'a', 'b', 'c'};
                fixed (char* cp = charArray)
                {
                    string s = new string(cp);
                    Assert(s.Equals("abc"));
                }
            }
            
            // Empty strings
            Assert(string.IsNullOrEmpty("") && string.IsNullOrEmpty(null));
            Assert(string.Empty.Length == 0);

            // String interpolation is favored over string.Format().  string interpolation was added in C# 6.0
            var desc1 = string.Format("My name is {0}.  I'm {1} years old.", "Andy", 24);

            var name = "Andy";
            var age = 24;
            var desc2 = $"My name is {name}.  I'm {age} years old.";
            Assert(desc1.Equals(desc2));
            
            // C# has StringBuilder's just like Java
            StringBuilder sb = new StringBuilder();
            sb.Append("Hi");
            sb.Append("There");
            Assert(sb.ToString().Equals("HiThere"));
            
            // Encode a file in UTF-32.  Then prove that the file has the expected encoding
            // https://stackoverflow.com/a/30393739
            Encoding utf32 = Encoding.UTF32;
            System.IO.File.WriteAllText("utf32.txt", "Hello in UTF-32!", utf32);

            using (var reader = new StreamReader("utf32.txt", Encoding.ASCII, true))
            {
                reader.Peek();
                var encoding = reader.CurrentEncoding;
                Assert(encoding.Equals(Encoding.UTF32));
            }

            // Create arrays of bytes using different encodings.
            byte[] utf8BA = Encoding.UTF8.GetBytes("Hello World!");
            byte[] utf32BA = Encoding.UTF32.GetBytes("Hello World!");
            byte[] asciiBA = Encoding.ASCII.GetBytes("Hello World!");
            Assert(utf8BA.Length == 12 && utf8BA.Length == asciiBA.Length && utf32BA.Length == 48);
        }
    }
}