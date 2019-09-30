/**
 * Investigate garbage collection in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Chapter 12]
 * Author: Andrew Jarombek
 * Date: 9/21/2019
 */

namespace garbage_collection
{
    static class Program
    {
        static void Main(string[] args)
        {
            Disposal.Execute();
            GarbageCollector.Execute();
        }
    }
}