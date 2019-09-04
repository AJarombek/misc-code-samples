/**
 * Investigate LINQ basics in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 351-379]
 * Author: Andrew Jarombek
 * Date: 8/9/2019
 */

using System;
using System.Linq;
using System.Collections.Generic;
using static System.Diagnostics.Debug;

namespace linq_basics
{
    public static class Queries
    {
        public static void Execute()
        {
            // LINQ (which stands for Language Integrated Query) enabled querying over enumerable data types and
            // remote data.  LINQ takes in an input sequence and produces an output sequence.  The following dictionary
            // is an example of an input sequence. 
            Dictionary<string, int> deerSpottedToday = new Dictionary<string, int>
            {
                {"Mianus River Park", 2},
                {"Cognewaugh Road", 1}
            };

            // A LINQ query can be created with System.Linq.Enumerable.Where.  It takes an input sequence and a lambda
            // function as arguments.  The lambda function is called for each item in the input sequence.  If the result
            // of the lambda function is 'true', the item is passed onto the output sequence.
            IEnumerable<KeyValuePair<string, int>> multipleDeer = Enumerable.Where(
                deerSpottedToday, 
                spot => spot.Value > 1
            );
            Assert(multipleDeer.Count() == 1);

            // Query operators are also extension methods for IEnumerable.  An extension method is one added to an
            // existing type without modifying the existing types definition.  In the example of query operators,
            // methods such as Where() are written in System.Linq.Enumerable but extended onto the
            // System.Collections.Generic.IEnumerable<T> interface.  Since Dictionary<K, V> implements IEnumerable<T>,
            // it can use the Where() extension method.
            var singleDeer = deerSpottedToday.Where(spot => spot.Value == 1);
            Assert(singleDeer.Count() == 1);

            // The previous two Linq examples used 'fluent syntax', which is made up of chained method calls.  C# also
            // provides a 'query syntax' which uses native keywords to build queries.  At first glance query syntax has
            // noticeable similarities to SQL.
            var anyDeer = 
                from spottedDeer in deerSpottedToday 
                where spottedDeer.Value >= 1 
                select spottedDeer;
            Assert(anyDeer.Count() == 2);

            // Walks I've gone on the past week as my knee recovers.  I'll use this array in the upcoming Linq queries.
            double[] walksPastWeek = { 3.5, 2.48, 3.6, 3.98, 3.59, 1.74, 1.54 };

            // Linq query with additional OrderBy() and Select() query operator methods.
            // Select() is basically a map() function.
            var greaterThanTwoMiles = walksPastWeek.Where(miles => miles > 2)
                                                   .OrderBy(miles => miles)
                                                   .Select(miles => Math.Round(miles));
            
            Assert(greaterThanTwoMiles.Count() == 5);
            Assert(greaterThanTwoMiles.First() == 2);

            // The same Linq query written in fluent syntax above can be rewritten in query syntax:
            var greaterThanTwoMilesAgain = 
                from distance in walksPastWeek
                where distance > 2
                orderby distance
                select Math.Round(distance);
            
            Assert(greaterThanTwoMilesAgain.Count() == 5);
            Assert(greaterThanTwoMilesAgain.First() == 2);

            // Additional Linq queries can be executed on the result of previous queries.
            var greaterThanTwoMilesDesc = greaterThanTwoMiles.OrderByDescending(miles => miles);
            
            Assert(greaterThanTwoMilesDesc.First() == 4);

            // OrderByDescending is also supported by query syntax.
            var greaterThanTwoMilesDescAgain = 
                from miles in greaterThanTwoMiles 
                orderby miles descending 
                select miles;
            
            Assert(greaterThanTwoMilesDescAgain.First() == 4);

            // Take the first two elements from the sorted enumerable sequence of walks.
            var twoLongestWalks = walksPastWeek.OrderByDescending(miles => miles).Take(2);
            Assert(twoLongestWalks.First() == 3.98 && twoLongestWalks.Last() == 3.6);
            Assert(twoLongestWalks.ElementAt(1) == twoLongestWalks.Last());
            
            // There are one or more walks in the sequence.
            Assert(twoLongestWalks.Any());
            
            // Query operators aren't executed when constructed.  They are executed when enumerated.  This can be
            // proved by altering a data structure after creating a LINQ query for it.
            var deerSpottedThisWeek = deerSpottedToday;

            var deerQuery = 
                from deerSpotted in deerSpottedThisWeek 
                where deerSpotted.Value > 1 
                select deerSpotted;
            
            // Right now, there is only one location where more than one deer was spotted.
            Assert(deerQuery.Count() == 1);
            
            // I saw another deer on Cognewaugh Road today.
            deerSpottedThisWeek["Cognewaugh Road"] = 2;
            
            // Due to delayed execution, now there are two locations where more than one deer was spotted.
            Assert(deerQuery.Count() == 2);
            
            // You can prohibit delayed execution by immediately assigning a query to a list.
            var deerQueryList = deerQuery.ToList();
            
            Assert(deerQueryList.Count == 2);

            // I didn't see a million deer in my dreams, although I wouldn't complain.
            deerSpottedThisWeek["My Dreams"] = 1_000_000;
            
            // The count in the list is still 2, while the count from the original query is now 3.
            Assert(deerQueryList.Count == 2);
            Assert(deerQuery.Count() == 3);

            deerSpottedThisWeek.Remove("My Dreams");
            Assert(deerQuery.Count() == 2);
            
            // If the values of lexical scope variables referenced in a query change before execution,
            // the query will honor that change.
            int numOfDeer = 2;
            var sameDeerQuery = 
                from deerSpotting in deerSpottedThisWeek
                where deerSpotting.Value >= numOfDeer
                select deerSpotting;
            
            // When numOfDeer is 2, there are two locations that match the query.
            Assert(sameDeerQuery.Count() == 2);

            numOfDeer = 3;
            
            // When numOfDeer is 3, there are no locations that match the query.
            Assert(sameDeerQuery.Count() == 0);

            var trailsWalkedThisWeekend = new [] 
            {
                (TrailName: "Main Road", Park: "Mianus River Park", Date: new DateTime(2019, 8, 23)),
                (TrailName: "Swamp Trail", Park: "Mianus River Park", Date: new DateTime(2019, 8, 23)),
                (TrailName: "Peak Trail", Park: "Mianus River Park", Date: new DateTime(2019, 8, 23)),
                (TrailName: "Laurel Trail", Park: "Mianus River Park", Date: new DateTime(2019, 8, 23)),
                (TrailName: "Main Road", Park: "Mianus River Park", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Dam Trail", Park: "Mianus River Park", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Cave Trail", Park: "Mianus River Park", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Pine Hill Road", Park: "Mianus River Park", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Fisherman's Loop Trail", Park: "Mianus River Park", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Cabin Trail", Park: "Mianus River Park", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Swingset Trail", Park: "Mianus River Park", Date: new DateTime(2019, 8, 24)),
                (TrailName: "GLT Trail", Park: "Babcock Preserve", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Red Maple Trail", Park: "Babcock Preserve", Date: new DateTime(2019, 8, 24)),
                (TrailName: "White Oak Trail", Park: "Babcock Preserve", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Yellow Birch Trail", Park: "Babcock Preserve", Date: new DateTime(2019, 8, 24)),
                (TrailName: "Red Maple Trail", Park: "Babcock Preserve", Date: new DateTime(2019, 8, 25)),
                (TrailName: "Yellow Birch Trail", Park: "Babcock Preserve", Date: new DateTime(2019, 8, 25))
            };

            // Demonstrate subqueries with fluent syntax ...
            var timesWalkedMostRecentTrail = trailsWalkedThisWeekend
                .Where(walk => walk.TrailName.Equals(trailsWalkedThisWeekend
                                   .OrderByDescending(subWalk => subWalk.Date).First().TrailName))
                .OrderBy(walk => walk.Date);

            // ... and again with query syntax.  Both queries produce the same result sequence.
            var timesWalkedMostRecentTrailAgain =
                from walk in trailsWalkedThisWeekend
                where walk.TrailName ==
                      (from subWalk in trailsWalkedThisWeekend 
                          orderby subWalk.Date descending 
                          select subWalk).First()
                      .TrailName
                orderby walk.Date
                select walk;

            var firstTimeWalked = "(Red Maple Trail, Babcock Preserve, 8/24/2019 12:00:00 AM)";
            var lastTimeWalked = "(Red Maple Trail, Babcock Preserve, 8/25/2019 12:00:00 AM)";
            
            // Prove that both queries with subqueries produce the same result.
            Assert(timesWalkedMostRecentTrail.Count() == 2);
            Assert(timesWalkedMostRecentTrail.First().ToString() == firstTimeWalked);
            Assert(timesWalkedMostRecentTrail.Last().ToString() == lastTimeWalked);
            Assert(timesWalkedMostRecentTrailAgain.Count() == 2);
            Assert(timesWalkedMostRecentTrailAgain.First().ToString() == firstTimeWalked);
            Assert(timesWalkedMostRecentTrailAgain.Last().ToString() == lastTimeWalked);
            
            // In query syntax, you can chain two queries together with the 'into' keyword.
            var walkQueryContinuation =
                from walk in trailsWalkedThisWeekend
                select (TrailName: walk.TrailName, Park: walk.Park, Date: walk.Date.Ticks)
                into walk2
                where walk2.TrailName.Contains("Road") 
                orderby walk2.Date
                select walk2;

            var firstWalkTicks = "(Main Road, Mianus River Park, 637021152000000000)";
            var thirdWalkTicks = "(Pine Hill Road, Mianus River Park, 637022016000000000)";
            
            Assert(walkQueryContinuation.Count() == 3);
            Assert(walkQueryContinuation.First().ToString().Equals(firstWalkTicks));
            Assert(walkQueryContinuation.Last().ToString().Equals(thirdWalkTicks));
            
            // The same query can be written as a wrapped query.
            var walkQueryWrapped =
                from walk in (
                    from walk2 in trailsWalkedThisWeekend
                    select (TrailName: walk2.TrailName, Park: walk2.Park, Date: walk2.Date.Ticks)
                )
                where walk.TrailName.Contains("Road")
                orderby walk.Date
                select walk;
            
            Assert(walkQueryWrapped.Count() == 3);
            Assert(walkQueryWrapped.First().ToString().Equals(firstWalkTicks));
            Assert(walkQueryWrapped.Last().ToString().Equals(thirdWalkTicks));
            
            // You can also create new anonymous types in a query.
            var walkTimes =
                from walk in trailsWalkedThisWeekend
                select new
                {
                    Date = walk.Date,
                    Name = walk.TrailName.Replace("Trail", "").Replace("Road", "").Trim()
                }
                into walkTime
                orderby walkTime.Date descending 
                select walkTime;

            var firstWalkTime = walkTimes.First();
            
            Assert(firstWalkTime.Date.ToString() == "8/25/2019 12:00:00 AM");
            Assert(firstWalkTime.Name == "Red Maple");
            
            // Instead of using 'select ... into' to create a new anonymous type, the 'let' keyword can be used.
            var walkTimesAgain =
                from walk in trailsWalkedThisWeekend
                let walkTime = new
                {
                    Date = walk.Date,
                    Name = walk.TrailName.Replace("Trail", "").Replace("Road", "").Trim()
                }
                orderby walkTime.Date descending 
                select walkTime;
            
            firstWalkTime = walkTimesAgain.First();
            
            Assert(firstWalkTime.Date.ToString() == "8/25/2019 12:00:00 AM");
            Assert(firstWalkTime.Name == "Red Maple");

            // Demonstrate how the GroupBy operator works.  It creates an IGrouping collection where each element is
            // all the items that match the key selector.
            var groupedWalks = trailsWalkedThisWeekend.GroupBy(
                trail => trail.TrailName
            );

            foreach (IGrouping<string,(string TrailName, string Park, DateTime Date)> grouping in groupedWalks)
            {
                Console.WriteLine($"Key: {grouping.Key}");
                foreach ((string TrailName, string Park, DateTime Date) tuple in grouping)
                {
                    Console.WriteLine($"    Item: {tuple.TrailName}, {tuple.Park}, {tuple.Date.ToString()}");
                }
            }

            var fromMyHeart = "she deserves whatever will bring her joy and happiness";

            var deserves =
                from word in fromMyHeart.Split(" ").Select((item, index) => new {item, index})
                where word.index > 5 && word.index % 2 == 0
                select word.item;
            
            Assert(deserves.First() == "joy");
            Assert(deserves.Last() == "happiness");
            Assert(deserves.Count() == 2);
        }
    }
}