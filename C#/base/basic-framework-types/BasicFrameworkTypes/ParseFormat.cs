/**
 * Investigate the parsing and formatting types found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 257-266]
 * Author: Andrew Jarombek
 * Date: 7/17/2019
 */

using System;
using System.Globalization;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class ParseFormat
    {
        public static void Execute()
        {
            // Objects in C# have a Parse() static method for converting from a string 
            int age = int.Parse("24");
            Assert(age == 24);
            
            // Parse throws an exception when it fails
            try
            {
                bool isExcitedForTSwiftAlbum = bool.Parse("of course!"); // Should be bool.Parse("true")
                
                // This assertion is never reached
                Assert(false);
            }
            catch (Exception e)
            {
                Assert(e is FormatException);
                Assert(true);
            }

            // Parse accepts culture info.  For example, in Germany a '.' is used as a thousands separator (Pg. 257)
            var year = double.Parse("2019");
            var yearGerman = double.Parse("2.019", CultureInfo.GetCultureInfo("de-DE"));
            Assert(yearGerman == 2019 && year == yearGerman);
            
            // Format numbers with NumberFormatInfo.  Set Positive Infinity to the
            // Unicode infinity character - https://www.fileformat.info/info/unicode/char/221e/index.htm
            var nf = new NumberFormatInfo();
            nf.PositiveInfinitySymbol = "\u221E";
            double infinity = double.PositiveInfinity;
            Assert(infinity.ToString(nf).Equals("∞"));
            
            // Parse a hexadecimal number and currency
            // Java - []
            var twentySix = int.Parse("1A", NumberStyles.HexNumber);
            Assert(twentySix == 26);

            var twoDollarsFiftyCents = double.Parse("$2.50", NumberStyles.Currency);
            Assert(twoDollarsFiftyCents == 2.50);
            
            // You can also use System.Convert for hexadecimal and other bases (such as octal)
            var twentySixAgain = Convert.ToInt32("1A", 16);
            Assert(twentySix == twentySixAgain);

            var twentySixOctal = Convert.ToInt32("32", 8);
            Assert(twentySixOctal == twentySix);
        }
    }
}