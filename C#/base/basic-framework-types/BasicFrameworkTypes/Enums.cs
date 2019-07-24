/**
 * Investigate enums found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 278-]
 * Author: Andrew Jarombek
 * Date: 7/23/2019
 */

using System;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class Enums
    {
        enum Album
        {
            Lover, Reputation, _1989, Red, SpeakNow, Fearless, TaylorSwift
        }
        
        public static void Execute()
        {
            // An enum is of type Enum and its specific type (in this case Album)
            var newest = Album.Lover;
            Assert(newest.GetType() == typeof(Album));
            Assert(newest is Album && newest is Enum);
            
            Assert(Album.TaylorSwift.ToString().Equals("TaylorSwift"));
        }
    }
}