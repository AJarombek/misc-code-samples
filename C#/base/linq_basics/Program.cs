/**
 * Investigate the language integrated query (LINQ) framework found in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Chapter 8-9]
 * Author: Andrew Jarombek
 * Date: 8/9/2019
 */

namespace linq_basics
{
    static class Program
    {
        static void Main(string[] args)
        {
            Queries.Execute();
            InterpretedQueries.ExecuteBasics();
            InterpretedQueries.ExecuteLocalInterpretedComparison();
            InterpretedQueries.ExecuteAdvancedQueries();
        }
    }
}