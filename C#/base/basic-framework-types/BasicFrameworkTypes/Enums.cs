/**
 * Investigate enums found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 278-282]
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

        [Flags]
        enum Song
        {
            ME = 1, YouNeedToCalmDown = 2, TheArcher = 4 
        }
        
        public static void Execute()
        {
            // An enum is of type Enum and its specific type (in this case Album)
            Enum newest = Album.Lover;
            Assert(newest.GetType() == typeof(Album));
            Assert(newest is Album);
            
            Assert(Album.TaylorSwift.ToString().Equals("TaylorSwift"));

            // Enums that use the Flags decorator can be converted to integers
            int archerInt = (int) Song.TheArcher;
            Assert(archerInt == 4);

            Enum archerEnum = Song.TheArcher;
            int archerInt2 = (int) (object) archerEnum; // Enum can't directly be converted to int.
            Assert(archerInt2 == 4);

            var songs = Enum.GetValues(typeof(Song));
            Assert(songs.Length == 3);

            // The underlying type of enum's is an integer.  We can freely add and subtract integers from enums.
            // ~~that's the fun of me~~
            var me = Song.ME;
            var firstTwoSingles = me + 2;
            Assert(firstTwoSingles == (Song.ME | Song.YouNeedToCalmDown));
        }
    }
}