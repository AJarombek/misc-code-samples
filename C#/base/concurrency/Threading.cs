/**
 * Learn how to use Threads in the .NET Framework and C#
 * Sources: [C# 7.0 In a Nutshell: Page 559-]
 * Author: Andrew Jarombek
 * Date: 10/1/2019
 */

using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using static System.Diagnostics.Debug;
using System.Linq;

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

        public static void ExecuteThreadSignal()
        {
            // Signals are used to send notifications to child threads.  WaitOne() tells a child thread to wait until
            // its signaled from the main thread to continue execution.  From the main thread, Set() sends a signal
            // to a child thread telling it to stop waiting and continue execution.  Reset() revokes the signal, meaning 
            // a child thread needs to stop executing when WaitOne() is invoked again.
            var signal = new ManualResetEvent(false);
            
            var thread = new Thread(() =>
            {
                Console.WriteLine("Waiting for initial signal");
                signal.WaitOne();
                Console.WriteLine("Got initial signal");
                Thread.Sleep(500);
                Console.WriteLine("Waiting for final signal");
                signal.WaitOne();
                Console.WriteLine("Got final signal");
                signal.Dispose();
            });
            
            thread.Start();
            signal.Set();
            signal.Reset();
            Thread.Sleep(500);
            signal.Set();
        }

        public static void ExecuteTasks()
        {
            // A Task is a higher-level construct than a Thread.  You can think of a Task as a concurrent operation that
            // may be backed by a thread (Pg. 577).
            
            // These are both comparable operations, however the Task is executed as part of a thread pool.
            Task.Run(() => Console.WriteLine("Written in a Task."));
            new Thread(() => Console.WriteLine("Written in a Task.")).Start();
            
            // Calling Wait() on a task is equivalent to calling Join() on a thread
            var task = Task.Run(() => { });
            task.Wait();
            Console.WriteLine("Finished waiting for Task.");
            
            // Tasks can return results to the caller.
            Task<int> multTask = Task.Run(() => 2 * 2);
            Assert(multTask.Result == 4);
            
            // Unlike threads, tasks propagate exceptions
            try
            {
                Task excpetionTask = Task.Run(() => throw new Exception("Exception from Task!"));
                excpetionTask.Wait();
            }
            catch (Exception e)
            {
                Console.WriteLine("Task Threw Exception in Parent.");
                Assert(e.InnerException.Message == "Exception from Task!");
            }
            
            // Continuations assign code to run after a task completes.  This is comparable to then() methods for
            // JavaScript promises.  Continuations promise (exceptions aside) that code will run after task completes.
            Task<List<string>> toLowerTask = Task.Run(() =>
            {
                var list = "ANDY".Split().ToList();
                return (from character in list select character.ToLower()).ToList();
            });

            var awaiter = toLowerTask.GetAwaiter();
            
            awaiter.OnCompleted(() =>
            {
                Console.WriteLine("Inside Task Continuation");
                
                List<string> resultList = awaiter.GetResult();
                var resultString = string.Join("", resultList);
                Assert(resultString == "andy");
            });
        }
    }
}