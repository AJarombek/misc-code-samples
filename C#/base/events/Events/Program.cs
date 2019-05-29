/**
 * Investigate Events in C#
 * Sources: [https://stackoverflow.com/a/803320]
 * Author: Andrew Jarombek
 * Date: 5/29/2019
 */

using System;

namespace Events
{
    class Program
    {
        // Delegate to used as the signature for the event
        public delegate void FloatHandler(double num);

        static void Main(string[] args)
        {
            Broadcaster.Run(5.21); // I Ran 5.21 Miles
        }
    }

    class Broadcaster
    {
        // Event which holds a single delegate instance - RunOccurred
        public static event Program.FloatHandler RunHandler = RunOccurred;

        /// <summary>
        /// Function which prints out the number of miles run.  Conforms to the FloatHandler delegate signature.
        /// </summary>
        /// <param name="num">The distance run.</param>
        private static void RunOccurred(double num) => Console.Write($"I Ran {num} Miles");

        /// <summary>
        /// Function which triggers the event with a certain run distance.
        /// </summary>
        /// <param name="num">The distance run.</param>
        public static void Run(double num)
        {
            RunHandler(num);
        }
    }
}
