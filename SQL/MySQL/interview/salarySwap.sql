-- Question: https://leetcode.com/problems/swap-salary/
-- Description: Given a table salary, such as the one below, that has m=male and f=female values. Swap all f and m
-- values (i.e., change all f values to m and vice versa) with a single update statement and no intermediate temp table.
-- Note that you must write a single update statement, DO NOT write any select statement for this problem.
--
-- Result: [FAILURE: 1:15]
-- Author: Andrew Jarombek
-- Date: 11/11/2019

DROP TABLE IF EXISTS salary;

CREATE TABLE salary (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(10),
    gender CHAR(1),
    salary INT
);

DELETE FROM salary WHERE id IS NOT NULL;

INSERT INTO salary(id, name, gender, salary) VALUES (1, 'A', 'm', 2500);
INSERT INTO salary(id, name, gender, salary) VALUES (2, 'B', 'f', 1500);
INSERT INTO salary(id, name, gender, salary) VALUES (3, 'C', 'm', 5500);
INSERT INTO salary(id, name, gender, salary) VALUES (4, 'D', 'f', 500);

-- The answer.  I didn't realize you could use CASE statements within an UPDATE clause.
-- Redemption for women with lower wages.
UPDATE salary
SET salary = CASE
    WHEN salary = 'm' THEN 'f'
    ELSE 'm' END;

-- Test queries used to work towards the answer.

SELECT * FROM salary;

SELECT * FROM salary AS f_salary, salary AS m_salary;

SELECT * FROM salary AS f_salary
INNER JOIN salary AS m_salary
ON f_salary.id = m_salary.id;

SELECT * FROM salary AS f_salary
LEFT OUTER JOIN salary AS m_salary
ON f_salary.id = m_salary.id
AND m_salary.gender = 'm';

-- These two update statements work if there are both male and females in the table.
-- This is the closest I got to the answer.
UPDATE
    salary AS f_salary, salary AS m_salary
SET
    f_salary.gender = m_salary.gender,
    m_salary.gender = f_salary.gender
WHERE (f_salary.gender IS NULL OR f_salary.gender = 'f')
AND (m_salary.gender IS NULL OR m_salary.gender = 'm');

UPDATE
    salary AS f_salary, salary AS m_salary
SET
    f_salary.gender = 'm',
    m_salary.gender = 'f'
WHERE (f_salary.gender IS NULL OR f_salary.gender = 'f')
AND (m_salary.gender IS NULL OR m_salary.gender = 'm');