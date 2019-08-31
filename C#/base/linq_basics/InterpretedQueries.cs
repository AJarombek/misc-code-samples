/**
 * Investigate LINQ Interpreted (Remote) Queries in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 379-]
 * Author: Andrew Jarombek
 * Date: 8/25/2019
 */

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using static System.Diagnostics.Debug;

namespace linq_basics
{
    public class LanguageContext : DbContext
    {
        public DbSet<Language> LanguageSet { get; set; }
        public DbSet<CodeWritten> CodeWrittenSet { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(
                @"Server=localhost;Database=LinqDemo;User Id=sa;Password=LinqDemo1"
            );
        }
    }
    
    [Table("Language")]
    public class Language
    {
        [Key]
        [Required]
        [Column("Name", TypeName = "VARCHAR(31)")]
        public string Name { get; set; }
        
        [Required]
        [Column("ReleaseYear", TypeName = "INT")]
        public int ReleaseYear { get; set; }
        
        public List<CodeWritten> CodeWrittenReferences { get; set; }
    }
    
    [Table("CodeWritten")]
    public class CodeWritten
    {
        [Key]
        [Required]
        [Column("ID", TypeName = "INT")]
        public int Id { get; set; }

        [Required]
        [Column("Year", TypeName = "INT")]
        public int Year { get; set; }
        
        [Required]
        [Column("LinesWritten", TypeName = "INT")]
        public int LinesWritten { get; set; }
        
        [Required]
        [Column("Language", TypeName = "VARCHAR(31)")]
        public string Language { get; set; }
        
        [ForeignKey("Language")]
        public Language LanguageReference { get; set; }
    }
    
    public class InterpretedQueries
    {
        public static void Execute()
        {
            using (var context = new LanguageContext())
            {
                var languages = context.LanguageSet.ToList();
                var codeWritten = context.CodeWrittenSet.ToList();
                
                Assert(languages.Count == 18);
                Assert(codeWritten.Count == 18 * 6);
                
                // The LINQ queries in this section will have corresponding SQL queries in database/queries.sql.
                // They are referenced in brackets above the query.  Ex: [SQL Lines 1-4]

                // Using the database context cache for the Language table, retrieve the oldest and newest languages.
                // Since a cache is used, any database updates made after the context was created will not be
                // represented in the results.
                // [SQL Lines 11-13]
                var oldestLanguage = context.LanguageSet.OrderBy(language => language.ReleaseYear).First();
                Assert(oldestLanguage.Name == "SQL");
                
                // [SQL Lines 16-18]
                var newestLanguage = context.LanguageSet.OrderByDescending(language => language.ReleaseYear).First();
                Assert(newestLanguage.Name == "HCL");
            }
        }
    }
}