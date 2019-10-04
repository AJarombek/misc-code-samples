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
        public static void ExecuteBasicThread()
        {
            Thread thread = new Thread(() => Print('1'));
            Assert(!thread.IsAlive);
            thread.Start();

            Print('0');
        }

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
        }
    }
}