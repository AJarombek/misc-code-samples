-- Question: https://leetcode.com/problems/big-countries/
-- A country is big if it has an area of bigger than 3 million square km or a population of more than 25 million.
-- Write a SQL solution to output big countries' name, population and area.
-- Author: Andrew Jarombek
-- Date: 10/23/2019

DROP TABLE IF EXISTS World;

CREATE TABLE World(
    name VARCHAR(63),
    continent VARCHAR(63),
    area INT,
    population INT,
    gdp INT
);

INSERT INTO World (name, continent, area, population, gdp) VALUES ('Afghanistan', 'Asia', 652230, 25500100, 20343000);
INSERT INTO World (name, continent, area, population, gdp) VALUES ('Albania', 'Europe', 28748, 2831741, 12960000);
INSERT INTO World (name, continent, area, population, gdp) VALUES ('Algeria', 'Africa', 2381741, 37100000, 188681000);
INSERT INTO World (name, continent, area, population, gdp) VALUES ('Andorra', 'Europe', 468, 78115, 3712000);
INSERT INTO World (name, continent, area, population, gdp) VALUES ('Angola', 'Africa', 1246700, 20609294, 100990000);

SELECT name, population, area
FROM World
WHERE population > 25000000
OR area > 3000000