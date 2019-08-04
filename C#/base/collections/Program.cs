/**
 * Investigate the basic collections found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Chapter 7]
 * Author: Andrew Jarombek
 * Date: 7/28/2019
 */

namespace collections
{
    static class Program
    {
        static void Main(string[] args)
        {
            // Execute all the classes containing type exploration code
            CollectionInterfaces.Execute();
            Arrays.Execute();
            DataStructures.Execute();
        }
    }
}