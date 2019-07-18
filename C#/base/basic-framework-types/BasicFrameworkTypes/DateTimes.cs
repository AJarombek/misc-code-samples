/**
 * Investigate the date and time types found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 243-255]
 * Author: Andrew Jarombek
 * Date: 7/16/2019
 */

using System;
using System.Globalization;
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
            var today = new DateTime(2019, 7, 17); // DateTime.Today
            Assert(today.IsDaylightSavingTime());
            
            // Different DateTimeKind's can be specified
            var dateLocal = new DateTime(2019, 7, 17, 7, 27, 0, DateTimeKind.Utc);
            var dateUtc = new DateTime(2019, 7, 17, 7, 27, 0, DateTimeKind.Local);
            
            // DateTimeOffset has an additional offset argument from UTC
            var todayAgain = new DateTimeOffset(today, TimeSpan.FromHours(4));
            Assert(todayAgain.ToString().Equals("7/17/2019 12:00:00 AM +04:00"));
            
            // You can add DateTime, DateTimeOffset, and TimeSpan objects together using operators
            var yesterday = today - TimeSpan.FromDays(1);
            Assert(yesterday.ToString(CultureInfo.InvariantCulture).Equals("07/16/2019 00:00:00"));

            var yesterdayAgain = todayAgain - TimeSpan.FromDays(1);
            Assert(yesterdayAgain.ToString(CultureInfo.InvariantCulture).Equals("07/16/2019 00:00:00 +04:00"));

            TimeSpan zero = yesterday - yesterday;
            Assert(zero.Equals(TimeSpan.Zero));
            
            // C# Has a class specifically for time zones
            var utcTZ = TimeZoneInfo.Utc;
            var gmtTZ = TimeZoneInfo.FindSystemTimeZoneById("Greenwich Standard Time");
            var estTZ = TimeZoneInfo.FindSystemTimeZoneById("Eastern Standard Time");
            
            Assert(estTZ.BaseUtcOffset.Equals(TimeSpan.FromHours(-5)));
            Assert(gmtTZ.BaseUtcOffset.Equals(TimeSpan.Zero));
            Assert(utcTZ.BaseUtcOffset.Equals(TimeSpan.Zero));
            
            // Despite having the same UTC offset, UTC and GMT are not equal objects
            Assert(!utcTZ.Equals(gmtTZ));
        }
    }
}