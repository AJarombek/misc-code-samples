/**
 * Investigate how Unicode is handled in the .NET Framework
 * Author: Andrew Jarombek
 * Date: 8/27/2019
 */

using System;
using System.Text;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class Unicode
    {
        public static void Execute()
        {
            var b = "beyonce\u0301";
            var b2 = "beyonc\u00E9";
            
            // While both strings appear the same when printed (usually, sometimes variable 'b' appears as beyonce'
            // instead of beyoncé) ...
            Console.WriteLine(b);
            Console.WriteLine(b2);
            
            // ... their values are not equal.
            Assert(b != b2 && !b.Equals(b2));
            
            // Their lengths also are not equal.
            Assert(b.Length == 8);
            Assert(b2.Length == 7);

            // Normalize the strings using NFC
            string bNormalized = b.Normalize(NormalizationForm.FormC);
            string b2Normalized = b.Normalize(NormalizationForm.FormC);
            
            // The normalized strings will now have equal values
            Assert(bNormalized == b2Normalized);

            // Emojis appear to be a single character, but have a length greater than 1.
            var smiley = "😊";
            Assert(smiley.Length == 2);
            
            var aug28 = new [] { "Bound 2", "Released 6 Years Ago" };
            var aug28Again = new [] { "Cantu", "In My Bathroom Closet 1 Year ago" };
            
            var daylight = "Daylight - Taylor Swift";
            
            Console.WriteLine($"{aug28[0]}, {aug28Again[0]}, {daylight.Split()[0]}");
        }
    }
}