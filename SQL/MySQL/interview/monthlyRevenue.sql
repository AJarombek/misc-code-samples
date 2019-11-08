-- Question: https://leetcode.com/problems/reformat-department-table/
-- Write an SQL query to reformat the table such that there is a department id column and a revenue column for
-- each month.
-- Author: Andrew Jarombek
-- Date: 11/7/2019

DROP TABLE IF EXISTS Department;

CREATE TABLE Department (
    id INT NOT NULL,
    revenue INT NOT NULL,
    month CHAR(3) NOT NULL,
    PRIMARY KEY (id, revenue)
);

INSERT INTO Department (id, revenue, month) VALUES (1, 8000, 'Jan');
INSERT INTO Department (id, revenue, month) VALUES (2, 9000, 'Jan');
INSERT INTO Department (id, revenue, month) VALUES (3, 10000, 'Feb');
INSERT INTO Department (id, revenue, month) VALUES (1, 7000, 'Feb');
INSERT INTO Department (id, revenue, month) VALUES (1, 6000, 'Mar');

SELECT id,
    max(CASE WHEN month = 'Jan' THEN revenue END) AS Jan_Revenue,
    max(CASE WHEN month = 'Feb' THEN revenue END) AS Feb_Revenue,
    max(CASE WHEN month = 'Mar' THEN revenue END) AS Mar_Revenue,
    max(CASE WHEN month = 'Apr' THEN revenue END) AS Apr_Revenue,
    max(CASE WHEN month = 'May' THEN revenue END) AS May_Revenue,
    max(CASE WHEN month = 'Jun' THEN revenue END) AS Jun_Revenue,
    max(CASE WHEN month = 'Jul' THEN revenue END) AS Jul_Revenue,
    max(CASE WHEN month = 'Aug' THEN revenue END) AS Aug_Revenue,
    max(CASE WHEN month = 'Sep' THEN revenue END) AS Sep_Revenue,
    max(CASE WHEN month = 'Oct' THEN revenue END) AS Oct_Revenue,
    max(CASE WHEN month = 'Nov' THEN revenue END) AS Nov_Revenue,
    max(CASE WHEN month = 'Dec' THEN revenue END) AS Dec_Revenue
FROM Department
GROUP BY id;