/**
 * Investigate the interfaces that make up collections in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 301-]
 * Author: Andrew Jarombek
 * Date: 7/29/2019
 */

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using static System.Diagnostics.Debug;

namespace collections
{
    class AndyList<T> : IEnumerable<T>
    {
        private readonly List<T> internalList;

        public AndyList(IEnumerable<T> enumerable)
        {
            internalList = enumerable.ToList();
        }

        public AndyList(IEnumerable enumerable)
        {
            internalList = enumerable.Cast<T>().ToList();
        }
        
        public IEnumerator<T> GetEnumerator()
        {
            throw new NotImplementedException();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
        
        public struct Enumerator : IEnumerator<T>
        {
            public T Current { get; }
            
            public bool MoveNext()
            {
                throw new NotImplementedException();
            }

            public void Reset()
            {
                throw new NotImplementedException();
            }

            object IEnumerator.Current => Current;

            public void Dispose() {}
        }
    }
    
    public static class CollectionInterfaces
    {
        public static void Execute()
        {
            // Collections implement the IEnumerable and IEnumerable<T> interfaces.  They specify a single method
            // GetEnumerator(), which returns a class that implements IEnumerable and IEnumerable<T>.  IEnumerable
            // specifies three methods - MoveNext(), Current(), and Reset().
            var list = new List<int> {2, 4, 6};
            IEnumerator<int> enumerator = list.GetEnumerator();
            
            // Current is set to "default(T)" if MoveNext() hasn't been called yet or if MoveNext() is called beyond
            // the last list index.
            Assert(enumerator.Current == 0);
            
            // default(int) is 0, which is the same as default(T) for my generic integer list
            Assert(default(int) == 0);
            
            int count = 0;
            while (enumerator.MoveNext())
            {
                count += enumerator.Current;
            }

            Assert(count == 12);
            Assert(enumerator.Current == 0);
            
            // The IEnumerator implementation ran out of indexes in the list to enumerate over.
            Assert(enumerator.MoveNext() == false);
            
            // The only way to iterate over the list again is to reset the IEnumerator.
            enumerator.Reset();
            bool enumeratorHasNext = enumerator.MoveNext();
            Assert(enumeratorHasNext);
            
            // To dispose of an IEnumerator after use, the "using" code block can be used.
            count = 0;
            using (enumerator)
                while (enumerator.MoveNext())
                {
                    count += enumerator.Current;
                }
            
            Assert(count == 10);
            
            // For type List, the implicit call to Dispose() at the end of the "using" block does nothing.
            Assert(enumerator.MoveNext() == false);
            
            // foreach is a syntactic shortcut for IEnumerator
            count = 0;
            foreach (var item in list)
            {
                count += item;
            }
            Assert(count == 12);
        }
    }
}