/**
 * Investigate programmatic intervention with the Garbage Collector in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 513-537]
 * Author: Andrew Jarombek
 * Date: 9/21/2019
 */

using System;
using System.Diagnostics;
using System.Runtime;
using System.Text;
using System.Threading;
using static System.Diagnostics.Debug;

namespace garbage_collection
{
    public static class GarbageCollector
    {
        public static void Execute()
        {
            // Check the name of this C# process.
            var processName = Process.GetCurrentProcess().ProcessName;
            Assert(processName == "dotnet");

            // Most modern programming languages (not just object-oriented languages) have a garbage collector
            // running while a program executes.  Many languages don't give programmers much control over how the
            // garbage collector functions.  C# provides some methods to interact with the garbage collector.

            // GC uses a separate heap for large objects.  The large heap's memory isn't compacted on
            // garbage collection by default ...
            Assert(GCSettings.LargeObjectHeapCompactionMode == GCLargeObjectHeapCompactionMode.Default);

            // However it can be enabled.  NOTE:  Moving large objects in memory is a slow operation.
            GCSettings.LargeObjectHeapCompactionMode = GCLargeObjectHeapCompactionMode.CompactOnce;

            // The C# garbage collector has three heap divisions - gen0, gen1, and gen2.  Newly allocated objects are
            // in gen0.  Objects which survived a single garbage collection are in gen1.  All other objects are in gen2.
            Assert(GC.MaxGeneration == 2);

            // You can force the CLR to run a garbage collection cycle by calling Collect().
            GC.Collect();

            // You can specify which generation is collected by passing an argument to Collect().
            GC.Collect(0);
            GC.Collect(1);
            GC.Collect(2);

            // Finalizers delay garbage collection of objects.  You can force these objects to be collected by calling
            // Collect() after WaitForPendingFinalizers().
            GC.WaitForPendingFinalizers();
            GC.Collect();

            // For diagnosis purposes, we can check how much memory is used by the C# process
            Console.WriteLine(GC.GetTotalMemory(true));
        }
    }
}