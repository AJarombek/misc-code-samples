/**
 * Investigate disposal mechanisms in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 513-]
 * Author: Andrew Jarombek
 * Date: 9/21/2019
 */

using System;
using static System.Diagnostics.Debug;

namespace garbage_collection
{
    sealed class Door : IDisposable
    {
        public bool IsOpen { get; private set; }

        public void Open() => IsOpen = true;

        public void Close() => IsOpen = false;
        
        public void Dispose()
        {
            Console.WriteLine("Disposing a Door instance...");
            if (IsOpen)
            {
                Console.WriteLine("Close the Open Door before leaving...");
                Close();
            }
        }
    }
    
    public class Disposal
    {
        public static void Execute()
        {
            var carDoor = new Door();
            carDoor.Open();
            
            // Invoking dispose will print the following:
            // Disposing a Door instance...
            // Close the Open Door before leaving...
            carDoor.Dispose();
            
            // A syntactic shortcut in C# for Dispose() is the 'using' clause.  Without invoking Dispose() directly,
            // the following two lines will be printed again after the code block completes:
            // Disposing a Door instance...
            // Close the Open Door before leaving...
            using (var officeDoor = new Door())
            {
                officeDoor.Open();
                Assert(officeDoor.IsOpen);
            }
        }
    }
}