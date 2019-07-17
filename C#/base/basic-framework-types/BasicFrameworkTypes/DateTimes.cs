/**
 * Investigate the date and time types found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 243-]
 * Author: Andrew Jarombek
 * Date: 7/16/2019
 */

using System;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class DateTimes
    {
        public static void Execute()
        {
            var hoursWorkedToday = new TimeSpan(1, 25, 0);
            Assert(hoursWorkedToday.ToString().Equals("01:25:00"));
            
            // TimeSpan accepts ticks (1 tick = 100ns)
            var oneSecond = new TimeSpan(10_000_000);
            var alsoOneSecond = new TimeSpan(0, 0, 1);
            Assert(TimeSpan.Equals(oneSecond, alsoOneSecond));
            
            // TimeSpan overloads the + and - operators
            var twoSeconds = TimeSpan.FromMilliseconds(1_000) + TimeSpan.FromSeconds(1);
            Assert(twoSeconds.Equals(TimeSpan.FromSeconds(2)));

            var twentyHours = TimeSpan.FromDays(1) - TimeSpan.FromHours(4);
            Assert(TimeSpan.Equals(twentyHours, TimeSpan.FromHours(20)));

            // DateTime is the legacy date and time class. DateTimeOffset is new to .NET Framework 3.5
            var today = DateTime.Today;
            Assert(today.IsDaylightSavingTime());
        }
    }
}