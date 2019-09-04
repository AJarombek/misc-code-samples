-- Execute queries against the SQL Server database.
-- Author: Andrew Jarombek
-- Date: 8/29/2019

USE LinqDemo

SELECT * FROM Language;
SELECT * FROM CodeWritten;

-- Select the oldest language name.
SELECT TOP 1 Name
FROM Language
ORDER BY ReleaseYear;

-- Select the newest language name.
SELECT TOP 1 Name
FROM Language
ORDER BY ReleaseYear DESC;

-- Retrieve the code written statistics for this year, ordered by the most coded languages.
SELECT * FROM CodeWritten
WHERE Year = 2019
ORDER BY LinesWritten DESC;

-- The same as the previous query except it only returns the most used language.
SELECT TOP 1 * FROM CodeWritten
WHERE Year = 2019
ORDER BY LinesWritten DESC;

-- Get the release year of the most used language of 2019 by inner joining the CodeWritten and Language tables.
SELECT TOP 1 ReleaseYear FROM CodeWritten
    INNER JOIN Language
        ON CodeWritten.Language = Language.Name
WHERE Year = 2019
ORDER BY LinesWritten DESC;

-- Get all the code written statistics for the Java programming language.
SELECT * FROM CodeWritten WHERE Language = 'Java';