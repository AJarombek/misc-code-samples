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