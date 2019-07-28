/**
 * Investigate the interfaces that make up collections in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 301-]
 * Author: Andrew Jarombek
 * Date: 7/29/2019
 */

using System.Collections.Generic;
using static System.Diagnostics.Debug;

namespace collections
{
    public static class CollectionInterfaces
    {
        public static void Execute()
        {
            // Collections implement the IEnumerable and IEnumerable<T> interfaces.  They specify a single method
            // GetEnumerator(), which returns a class that implements IEnumerable and IEnumerable<T>.  IEnumerable
            // specifies three methods - MoveNext(), Current(), and Reset().
            var list = new List<int> {1, 2, 3};
            IEnumerator<int> enumerator = list.GetEnumerator();

            int count = 0;
            while (enumerator.MoveNext())
            {
                count += enumerator.Current;
            }
            
            Assert(count == 6);
        }
    }
}