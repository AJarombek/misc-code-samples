/**
 * Investigate Iterators in C#
 * Author: Andrew Jarombek
 * Date: 5/30/2019
 */

using System;
using System.Collections.Generic;
using static System.Diagnostics.Debug;

namespace Iterator
{
    class Program
    {
        /// <summary>
        /// Custom iterator for distances run.  The C# compiler converts this function into a private class that
        /// implements IEnumerable [Source: C# 7.0 In a Nutshell Pg. 170]
        /// </summary>
        /// <param name="weekCount">
        /// The number of weeks to return.  If this value is 1, only last weeks runs are returned.
        /// </param>
        /// <returns>Yields floating point values.</returns>
        static IEnumerable<double> RunsPastWeeks(int weekCount)
        {
            var lastWeek = new double[] { 0, 4.39, 4.38, 4.39, 5.67, 7.58, 12.2 };
            var secondToLastWeek = new double[] { 3.1, 2.55, 3.62, 3.26, 4.13, 1.74, 9.35 };

            foreach (var run in lastWeek)
            {
                yield return run;
            }
            
            if (weekCount == 1)
                yield break;

            foreach (var run in secondToLastWeek)
            {
                yield return run;
            }
        }
        
        static void Main(string[] args)
        {
            var forEachResult = "";
            var enumeratorResult = "";
            
            // foreach loops operate on instances of an IEnumerable implementation.  This 'using' block shows how an
            // iterator works under the covers.  GetEnumerator() returns an instance of CharEnumerator,
            // which implements IEnumerable
            using (var enumerator = "andy".GetEnumerator())
                while (enumerator.MoveNext())
                {
                    var element = enumerator.Current;
                    enumeratorResult += element;
                }

            
            foreach (var item in "andy")
            {
                forEachResult += item;
            }

            // Prove that both the above iterators operate the same
            Assert(forEachResult == "andy");
            Assert(enumeratorResult == "andy");
            Assert(forEachResult == enumeratorResult);
            
            // Prove that the custom iterator works as expected
            var count = 0;
            var mileage = 0.0;
            
            foreach (var run in RunsPastWeeks(1))
            {
                count++;
                mileage += run;
            }
            
            Assert(count == 7);
            Assert(mileage == 38.61);
        }
    }
}