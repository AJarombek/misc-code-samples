/**
 * Investigate concurrency and asynchronous code in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Chapter 14]
 * Author: Andrew Jarombek
 * Date: 10/1/2019
 */

using System.Threading;

namespace concurrency
{
    internal static class Program
    {
        internal static void Main()
        {
            Threading.ExecuteBasicThread();
            Threading.ExecuteThread();
            Threading.ExecuteThreadSignal();
            Threading.ExecuteTasks();
            Threading.ExecuteTaskCombinators();
            Async.Execute();
            
            // Sleep so that the program won't end before the asynchronous calls return.
            Thread.Sleep(30000);
        }
    }
}