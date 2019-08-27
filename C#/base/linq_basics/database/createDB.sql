-- Create tables and populate them with data.  Database used to test remote LINQ queries in C#.
-- Author: Andrew Jarombek
-- Date: 8/25/2019

CREATE DATABASE LinqDemo;

USE LinqDemo;

CREATE TABLE Language (
    Name VARCHAR(31) NOT NULL PRIMARY KEY,
    ReleaseYear INT NOT NULL 
);

CREATE TABLE CodeWritten (
    ID INT NOT NULL PRIMARY KEY,
    Language VARCHAR(31) NOT NULL,
    Year INT NOT NULL,
    LinesWritten INT NOT NULL,
    CONSTRAINT CodeWrittenLanguageFK 
        FOREIGN KEY (Language) REFERENCES Language(Name) ON DELETE CASCADE 
);

INSERT INTO Language (Name, ReleaseYear) VALUES ('Python', 1991);
INSERT INTO Language (Name, ReleaseYear) VALUES ('JavaScript', 1995);
INSERT INTO Language (Name, ReleaseYear) VALUES ('HCL', 2015);
INSERT INTO Language (Name, ReleaseYear) VALUES ('HTML', 1993);
INSERT INTO Language (Name, ReleaseYear) VALUES ('C#', 2000);
INSERT INTO Language (Name, ReleaseYear) VALUES ('Markdown', 2004);
INSERT INTO Language (Name, ReleaseYear) VALUES ('Groovy', 2003);
INSERT INTO Language (Name, ReleaseYear) VALUES ('Swift', 2014);
INSERT INTO Language (Name, ReleaseYear) VALUES ('YAML', 2001);
INSERT INTO Language (Name, ReleaseYear) VALUES ('Bash', 1989);
INSERT INTO Language (Name, ReleaseYear) VALUES ('Sass', 2006);
INSERT INTO Language (Name, ReleaseYear) VALUES ('Java', 1995);
INSERT INTO Language (Name, ReleaseYear) VALUES ('JSON', 2002);
INSERT INTO Language (Name, ReleaseYear) VALUES ('CSS', 1996);
INSERT INTO Language (Name, ReleaseYear) VALUES ('TypeScript', 2012);
INSERT INTO Language (Name, ReleaseYear) VALUES ('PHP', 1995);
INSERT INTO Language (Name, ReleaseYear) VALUES ('SQL', 1974);
INSERT INTO Language (Name, ReleaseYear) VALUES ('XML', 1996);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Python', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Python', 2015, 931);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Python', 2016, 1122);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Python', 2017, 1288);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Python', 2018, 1975);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Python', 2019, 8316);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JavaScript', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JavaScript', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JavaScript', 2016, 2008);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JavaScript', 2017, 6663);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JavaScript', 2018, 16414);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JavaScript', 2019, 5617);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HCL', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HCL', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HCL', 2016, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HCL', 2017, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HCL', 2018, 3801);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HCL', 2019, 3713);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HTML', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HTML', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HTML', 2016, 1413);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HTML', 2017, 2289);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HTML', 2018, 10833);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('HTML', 2019, 3185);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('C#', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('C#', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('C#', 2016, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('C#', 2017, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('C#', 2018, 325);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('C#', 2019, 3047);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Markdown', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Markdown', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Markdown', 2016, 16);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Markdown', 2017, 113);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Markdown', 2018, 439);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Markdown', 2019, 2956);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Groovy', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Groovy', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Groovy', 2016, 179);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Groovy', 2017, 113);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Groovy', 2018, 2164);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Groovy', 2019, 2324);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Swift', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Swift', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Swift', 2016, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Swift', 2017, 10726);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Swift', 2018, 698);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Swift', 2019, 2074);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('YAML', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('YAML', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('YAML', 2016, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('YAML', 2017, 33);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('YAML', 2018, 258);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('YAML', 2019, 2004);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Bash', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Bash', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Bash', 2016, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Bash', 2017, 129);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Bash', 2018, 1344);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Bash', 2019, 1815);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Sass', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Sass', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Sass', 2016, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Sass', 2017, 163);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Sass', 2018, 4198);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Sass', 2019, 1520);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Java', 2014, 4282);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Java', 2015, 1585);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Java', 2016, 12962);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Java', 2017, 12113);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Java', 2018, 4769);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('Java', 2019, 1390);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JSON', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JSON', 2015, 32);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JSON', 2016, 820);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JSON', 2017, 1019);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JSON', 2018, 364);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('JSON', 2019, 604);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('CSS', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('CSS', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('CSS', 2016, 1223);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('CSS', 2017, 1654);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('CSS', 2018, 594);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('CSS', 2019, 563);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('TypeScript', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('TypeScript', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('TypeScript', 2016, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('TypeScript', 2017, 133);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('TypeScript', 2018, 2375);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('TypeScript', 2019, 361);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('PHP', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('PHP', 2015, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('PHP', 2016, 5433);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('PHP', 2017, 3670);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('PHP', 2018, 356);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('PHP', 2019, 357);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('SQL', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('SQL', 2015, 325);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('SQL', 2016, 942);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('SQL', 2017, 812);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('SQL', 2018, 1392);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('SQL', 2019, 380);

INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('XML', 2014, 0);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('XML', 2015, 42);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('XML', 2016, 2646);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('XML', 2017, 5815);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('XML', 2018, 111);
INSERT INTO CodeWritten (Language, Year, LinesWritten) VALUES ('XML', 2019, 12);