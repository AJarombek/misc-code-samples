/**
 * Learn how to use Threads in the .NET Framework and C#
 * Sources: [C# 7.0 In a Nutshell: Page 559-]
 * Author: Andrew Jarombek
 * Date: 10/1/2019
 */

using System;
using System.Threading;
using static System.Diagnostics.Debug;

namespace concurrency
{
    public static class Threading
    {
        private static int _count;
        private static readonly object _locker = new object();
        
        public static void ExecuteBasicThread()
        {
            Thread thread = new Thread(() => Print('1'));
            Assert(!thread.IsAlive);
            thread.Start();

            Print('0');
        }

        /// <summary>
        /// Function called by a thread which prints out a character 50 times.  Each thread gets its own memory stack,
        /// so each gets its own variable 'i'.
        /// </summary>
        /// <param name="c">The character to print out.</param>
        static void Print(char c)
        {
            for (int i = 0; i < 50; i++)
            {
                Console.Write(c);
            }
        }

        public static void ExecuteThread()
        {
            Thread thread = new Thread(() => {Thread.Sleep(500);});
            thread.Start();
            Assert(thread.IsAlive);
            
            // Wait for the thread to end with Join().
            thread.Join();
            
            Assert(!thread.IsAlive);
            
            // Multi-threading is dangerous when two or more threads share mutable state.  In this case,
            // the mutable state is 'count'.
            Thread dangerousThread = new Thread(() => {
                for (int i = 0; i < 10000; i++) { _count++; }
            });
            dangerousThread.Start();
            
            for (int i = 0; i < 10000; i++) { _count++; }

            dangerousThread.Join();
            
            // 'count' will not always print out 20000.  To avoid this issue, use locking.  Or if possible don't
            // introduce mutable shared state.
            Console.WriteLine($"\nUnsafe Count: {_count}"); 
            
            // This example performs the same operation in a thread-safe manner with a locking object.
            _count = 0;
            Thread safeThread = new Thread(Add);
            safeThread.Start();
            Add();

            safeThread.Join();
            Console.WriteLine($"Safe Count: {_count}"); 
        }

        /// <summary>
        /// Increment a shared integer in a thread-safe manner using locks.
        /// </summary>
        static void Add()
        {
            for (int i = 0; i < 10000; i++)
            {
                lock (_locker)
                {
                    _count++;
                }
            }
        }
    }
}