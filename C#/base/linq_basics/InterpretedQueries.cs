/**
 * Investigate LINQ Interpreted (Remote) Queries in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 379-]
 * Author: Andrew Jarombek
 * Date: 8/25/2019
 */

//using System.ComponentModel.DataAnnotations.Schema;

using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using static System.Diagnostics.Debug;

namespace linq_basics
{
    [Table("Language")]
    public class Language
    {
        [Column("Name")]
        public string Name { get; set; }
        
        [Column("ReleaseYear")]
        public int ReleaseYear { get; set; }
    }
    
    [Table("CodeWritten")]
    public class CodeWritten
    {
        [Column("ID")]
        public int Id { get; set; }
        
        [Column("Language")]
        public string Language { get; set; }
        
        [Column("Year")]
        public int Year { get; set; }
        
        [Column("LinesWritten")]
        public int LinesWritten { get; set; }
    }
    
    public class InterpretedQueries
    {
        public static void Execute()
        {
             
        }
    }
}