/**
 * Investigate LINQ basics in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 351-]
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
            var anyDeer = from spottedDeer in deerSpottedToday 
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
            var greaterThanTwoMilesAgain = from distance in walksPastWeek
                where distance > 2
                orderby distance
                select Math.Round(distance);
            
            Assert(greaterThanTwoMilesAgain.Count() == 5);
            Assert(greaterThanTwoMilesAgain.First() == 2);

            // Additional Linq queries can be executed on the result of previous queries.
            var greaterThanTwoMilesDesc = greaterThanTwoMiles.OrderByDescending(miles => miles);
            
            Assert(greaterThanTwoMilesDesc.First() == 4);

            // OrderByDescending is also supported by query syntax.
            var greaterThanTwoMilesDescAgain = from miles in greaterThanTwoMiles 
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

            var deerQuery = from deerSpotted in deerSpottedThisWeek 
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
            var sameDeerQuery = from deerSpotting in deerSpottedThisWeek
                where deerSpotting.Value >= numOfDeer
                select deerSpotting;
            
            // When numOfDeer is 2, there are two locations that match the query.
            Assert(sameDeerQuery.Count() == 2);

            numOfDeer = 3;
            
            // When numOfDeer is 3, there are no locations that match the query.
            Assert(sameDeerQuery.Count() == 0);
        }
    }
}