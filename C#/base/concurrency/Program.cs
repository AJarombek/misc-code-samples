/**
 * Investigate concurrency and asynchronous code in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Chapter 14]
 * Author: Andrew Jarombek
 * Date: 10/1/2019
 */

namespace concurrency
{
    internal static class Program
    {
        internal static void Main(string[] args)
        {
            Threading.ExecuteBasicThread();
            Threading.ExecuteThread();
        }
    }
}