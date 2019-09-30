/**
 * Investigate disposal mechanisms in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 513-519]
 * Author: Andrew Jarombek
 * Date: 9/21/2019
 */

using System;
using static System.Diagnostics.Debug;

namespace garbage_collection
{
    /// <summary>
    /// Object representing a door that can be opened and closed.  The door is closed by default.
    /// </summary>
    sealed class Door : IDisposable
    {
        private bool isOpen;
        public bool IsOpen
        {
            get
            {
                if (!IsDisposed)
                {
                    return isOpen;
                }
                
                // Rule #1 of C# disposal is that a disposed object can't be reactivated and must throw an
                // ObjectDisposedException if someone tries to access it.
                throw new ObjectDisposedException("door");
            }

            private set => isOpen = value;
        }

        /// <summary>
        /// Metadata indicating if the object was disposed.
        /// </summary>
        public bool IsDisposed { get; private set; }

        /// <summary>
        /// Open the door.  Throws an exception if the object was disposed.
        /// </summary>
        public void Open()
        {
            if (IsDisposed) throw new ObjectDisposedException("door");
            IsOpen = true;
        }

        /// <summary>
        /// Close the door.  Throws an exception if the object was disposed.
        /// </summary>
        public void Close()
        {
            if (IsDisposed) throw new ObjectDisposedException("door");
            IsOpen = false;
        }

        /// <summary>
        /// Finalizer (destructor) which also calls finalize on the Door object.  The finalizer runs implicitly before
        /// the object is released from memory.
        /// </summary>
        ~Door()
        {
            Dispose();
        }
        
        /// <summary>
        /// Dispose the <code>Door</code> instance by making sure it's closed first.
        /// </summary>
        public void Dispose()
        {
            if (IsDisposed)
            {
                Console.WriteLine("Door instance already disposed.");
                return;
            }
            
            Console.WriteLine("Disposing a Door instance...");
            if (IsOpen)
            {
                Console.WriteLine("Close the Open Door before leaving...");
                Close();
            }

            IsDisposed = true;
        }
    }
    
    public class Disposal
    {
        public static void Execute()
        {
            var carDoor = new Door();
            Assert(!carDoor.IsOpen);
            carDoor.Open();
            
            // Invoking dispose will print the following:
            // Disposing a Door instance...
            // Close the Open Door before leaving...
            carDoor.Dispose();
            
            // Invoking a property on a disposed object throws an ObjectDisposedException exception.
            try
            {
                Console.WriteLine(carDoor.IsOpen);
                Assert(false); // Not Reached
            }
            catch (ObjectDisposedException e)
            {
                Assert(e.ObjectName == "door");
            }
            
            // Invoking a method on a disposed object also throws an ObjectDisposedException exception.
            try
            {
                carDoor.Close();
                Assert(false); // Not Reached
            }
            catch (ObjectDisposedException e)
            {
                Assert(e.ObjectName == "door");
            }
            
            // However, Dispose() can be invoked multiple times.
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
            
            // The 'using' block is nice because the variable isn't even in scope after its disposed.
        }
    }
}