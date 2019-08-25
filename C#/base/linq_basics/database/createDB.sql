-- Create tables and populate them with data.  Database used to test remote LINQ queries in C#.
-- Author: Andrew Jarombek
-- Date: 8/25/2019

CREATE TABLE Language (
    Name VARCHAR(31) NOT NULL PRIMARY KEY,
    ReleaseYear INT NOT NULL 
);

CREATE TABLE CodeWritten (
    ID INT NOT NULL PRIMARY KEY,
    Language VARCHAR(31) NOT NULL,
    LinesWritten INT NOT NULL,
    CONSTRAINT CodeWrittenLanguageFK 
        FOREIGN KEY (Language) REFERENCES Language(Name) ON DELETE CASCADE 
);