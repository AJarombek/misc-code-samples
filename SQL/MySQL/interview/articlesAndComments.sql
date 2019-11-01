-- Interview-type SQL question, described in the inline documentation.
-- Author: Andrew Jarombek
-- Date: 10/31/2019

DROP TABLE IF EXISTS posts;

-- This table contains two types of information - articles and comments.  Articles populate 'id' with the article
-- identifier and 'parent_id' with NULL.  Comments populate 'id' with the comment identifier and 'parent_id' with
-- the identifier of the article the comment was posted.
CREATE TABLE article_attribute (
    id INT NOT NULL,
    parent_id INT
);

ALTER TABLE article_attribute
MODIFY COLUMN id INT
COMMENT 'Contains either an article ID or a comment ID.  If the parent_id is NULL, then its an article ID.  Otherwise, its a comment ID.';

ALTER TABLE article_attribute
MODIFY COLUMN parent_id INT
COMMENT 'If the row represents an article, this column is NULL.  If this row represents a comment, this column is the ID of the corresponding article.';

INSERT INTO article_attribute (id, parent_id) VALUES (1, NULL);
INSERT INTO article_attribute (id, parent_id) VALUES (2, NULL);
INSERT INTO article_attribute (id, parent_id) VALUES (2, NULL); -- Duplicate
INSERT INTO article_attribute (id, parent_id) VALUES (10, NULL);
INSERT INTO article_attribute (id, parent_id) VALUES (3, NULL);
INSERT INTO article_attribute (id, parent_id) VALUES (4, 1);
INSERT INTO article_attribute (id, parent_id) VALUES (4, 1); -- Duplicate
INSERT INTO article_attribute (id, parent_id) VALUES (5, 2);
INSERT INTO article_attribute (id, parent_id) VALUES (6, 3);
INSERT INTO article_attribute (id, parent_id) VALUES (7, 1);
INSERT INTO article_attribute (id, parent_id) VALUES (8, 3);
INSERT INTO article_attribute (id, parent_id) VALUES (9, 3);

-- Description: We need a list of all the unique article IDs and the number of comments on each article.
-- The 'article_attribute' table includes some duplicates which should not be included in the unique article IDs and
-- should not count towards the number of comments.  If the article has zero comments, it should still be included
-- in the final result.

-- Answer:

SELECT article_id, SUM(count) AS count FROM (
    SELECT DISTINCT id AS article_id, 0 AS count
    FROM article_attribute
    WHERE parent_id IS NULL
    UNION ALL
    SELECT parent_id as article_id, COUNT(*) AS count
    FROM (
        SELECT DISTINCT id, parent_id
        FROM article_attribute
        WHERE parent_id IS NOT NULL
    ) AS a
    GROUP BY parent_id
) AS b
GROUP BY article_id;

-- Helpful Queries:

SELECT * FROM article_attribute;

SELECT DISTINCT id AS article_id, 0 AS count
FROM article_attribute
WHERE parent_id IS NULL;

SELECT DISTINCT id, parent_id
FROM article_attribute
WHERE parent_id IS NOT NULL;

SELECT parent_id as article_id, COUNT(*) AS count
FROM (
     SELECT DISTINCT id, parent_id
     FROM article_attribute
     WHERE parent_id IS NOT NULL
) AS a
GROUP BY parent_id;