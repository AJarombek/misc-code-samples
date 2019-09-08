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
using System.Linq.Expressions;
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
        public static void ExecuteBasics()
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

                var mostLinesCodedThisYear = 
                    from written in context.CodeWrittenSet
                    where written.Year == 2019
                    orderby written.LinesWritten descending
                    select written;

                // [SQL Lines 21-23]
                var languageCodedMostThisYear = mostLinesCodedThisYear.First();
                
                // [SQL Lines 26-28]
                Assert(languageCodedMostThisYear.Language == "Python");
                // [SQL Lines 31-35]
                Assert(languageCodedMostThisYear.LanguageReference.ReleaseYear == 1991);
            }
        }

        public static void ExecuteLocalInterpretedComparison()
        {
            using (var context = new LanguageContext())
            {
                // Unlike local queries which return an instance of IEnumerable<T>, interpreted queries return an
                // instance of IQueryable<T>.
                // [SQL Line 38]
                IQueryable<CodeWritten> sqlJavaCodeWritten =
                    from written in context.CodeWrittenSet
                    where written.Language == "Java"
                    select written;
                
                // IQueryable<T> extends the IEnumerable<T> interface, so interpreted queries can be assigned to type
                // IEnumerable<T> without error.
                IEnumerable<CodeWritten> sqlJavaCodeWrittenAgain =
                    from written in context.CodeWrittenSet
                    where written.Language == "Java"
                    select written;
                
                Assert(sqlJavaCodeWritten.Count() == 6);
                Assert(sqlJavaCodeWrittenAgain.Count() == 6);
                
                // Create a local list with the same data on Java code usage as the SQL Server database.
                List<CodeWritten> localJavaCodeWrittenList = new List<CodeWritten>
                {
                    new CodeWritten {Id = 67, Language = "Java", LinesWritten = 4282, Year = 2014},
                    new CodeWritten {Id = 68, Language = "Java", LinesWritten = 1585, Year = 2015},
                    new CodeWritten {Id = 69, Language = "Java", LinesWritten = 12962, Year = 2016},
                    new CodeWritten {Id = 70, Language = "Java", LinesWritten = 12113, Year = 2017},
                    new CodeWritten {Id = 71, Language = "Java", LinesWritten = 4769, Year = 2018},
                    new CodeWritten {Id = 72, Language = "Java", LinesWritten = 1390, Year = 2019}
                };

                // Prove the local query returns an instance of IEnumerable<T>.
                IEnumerable<CodeWritten> localJavaCodeWritten = localJavaCodeWrittenList.Select(cw => cw);
                
                Assert(localJavaCodeWritten.Count() == 6);
                
                // Assigning the local query to type IQueryable<T> results in a compile time error.
                // IQueryable<CodeWritten> localJavaCodeWritten = localJavaCodeWrittenList.Select(cw => cw);
                
                // Local queries use delegates while interpreted queries use expression trees.  Both appear as lambda
                // functions usually, however you can also explicitly type them.
                var localBestYear = localJavaCodeWritten.First(cw => cw.Year == 2016);
                var sqlBestYear = sqlJavaCodeWritten.First(cw => cw.Year == 2016);
                
                Assert(localBestYear.LinesWritten == sqlBestYear.LinesWritten);
                Assert(localBestYear.LinesWritten == 12962);
                
                // ... Now explicitly typed
                Func<CodeWritten, bool> predicateYear2016 = cw => cw.Year == 2016;
                localBestYear = localJavaCodeWritten.First(predicateYear2016);

                Expression<Func<CodeWritten, bool>> expressionYear2016 = cw => cw.Year == 2016;
                sqlBestYear = sqlJavaCodeWritten.First(expressionYear2016);

                Assert(localBestYear.LinesWritten == sqlBestYear.LinesWritten);
                Assert(localBestYear.LinesWritten == 12962);
                
                // Using an Expression in place of a Func for a local query causes a compilation error.
                // localBestYear = localJavaCodeWritten.First(expressionYear2016);
                
                // ... However for an interpreted query a Func can be used in place of an Expression.
                sqlBestYear = sqlJavaCodeWritten.First(predicateYear2016);
                Assert(sqlBestYear.LinesWritten == 12962);
                
                // An Expression<> can be converted to a Func<> with the Compile() method.
                localBestYear = localJavaCodeWritten.First(expressionYear2016.Compile());
                Assert(localBestYear.LinesWritten == 12962);
                
                // Or AsQueryable() can be used to wrap a local query in an instance of IQueryable<T>
                localBestYear = localJavaCodeWritten.AsQueryable().First(expressionYear2016);
                Assert(localBestYear.LinesWritten == 12962);
            }
        }

        public static void ExecuteAdvancedQueries()
        {
            using (var context = new LanguageContext())
            {
                // Use EF.Functions.Like to perform a SQL style 'LIKE' operation to match a pattern.
                // [SQL Line 41]
                var jLanguages =
                    from codeWritten in context.CodeWrittenSet
                    where EF.Functions.Like(codeWritten.Language, "J%") 
                    select codeWritten;
                
                Assert(jLanguages.Count() == 18);

                // Using a 'where' clause followed by a Contains() on a collection/array
                // is the same as an 'IN' SQL clause.
                // [SQL Line 44]
                var javaLanguagesArray = new [] {"Java", "JavaScript"};
                var javaLanguages = 
                    from codeWritten in context.CodeWrittenSet
                    where javaLanguagesArray.Contains(codeWritten.Language)
                    select codeWritten;
                
                Assert(javaLanguages.Count() == 12);

                // Perform the equivalent of an INNER JOIN with a select clause.
                // [SQL Lines 46-51]
                var highLinesWrittenJoin =
                    from written in context.CodeWrittenSet
                    where written.LinesWritten > 10000
                    orderby written.LinesWritten descending 
                    select new
                    {
                        written.LinesWritten,
                        written.LanguageReference.Name,
                        written.LanguageReference.ReleaseYear
                    };
                
                Assert(highLinesWrittenJoin.Count() == 5);

                var mostLinesWrittenEver = new {Name = "JavaScript", ReleaseYear = 1995, LinesWritten = 16414};
                Assert(highLinesWrittenJoin.First().LinesWritten == mostLinesWrittenEver.LinesWritten);

                // Query the languages and return just the names in alphabetical order.
                var languageNames =
                    from language in context.LanguageSet
                    orderby language.Name
                    select language.Name;

                Assert(languageNames.First() == "Bash" && languageNames.Last() == "YAML");

                // Query the code written statistics and group by the language name.  Retrieve the sum of the
                // lines of code written for each language.
                var languageTotalStats =
                    from codeWritten in context.CodeWrittenSet
                    orderby codeWritten.Language
                    group codeWritten.LinesWritten by codeWritten.Language
                    into totals 
                    select totals.Sum();
                
                Assert(languageTotalStats.First() == 3288);

                // Zip the two previous queries to associate the language with the total statistics
                var languagesZipped = languageNames.ToArray().Zip(languageTotalStats.ToArray(), (name, total) => $"{name} = {total}");

                foreach (var languageTotals in languagesZipped)
                {
                    Console.WriteLine(languageTotals);
                }
                
                Assert(languagesZipped.First() == "Bash = 3288");
            }
        }
    }
}