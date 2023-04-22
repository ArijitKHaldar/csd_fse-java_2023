CREATE DATABASE finance_tracker;
USE finance_tracker;

CREATE TABLE login
(
user_id VARCHAR(40) PRIMARY KEY,
email_id VARCHAR(40) NOT NULL,
UNIQUE(email_id),
password VARCHAR(25) NOT NULL
);

CREATE TABLE income
(
income_id INT PRIMARY KEY,
user_id VARCHAR(40) NOT NULL,
FOREIGN KEY(user_id) REFERENCES login(user_id),
income_date DATE,
income_amount DECIMAL(10,2)
);

CREATE TABLE expenditure
(
expenditure_id INT PRIMARY KEY,
user_id VARCHAR(40) NOT NULL,
FOREIGN KEY(user_id) REFERENCES login(user_id),
expenditure_date DATE,
expenditure_amount DECIMAL(10,2)
);

CREATE TABLE category
(
category_id INT PRIMARY KEY,
expenditure_tag ENUM ('FOOD', 'UTILITIES', 'HOUSING', 'TRANSPORTATION', 'EDUCATION', 'CLOTHING', 'MEDICAL', 'INSURANCE', 'HOUSEHOLD', 'PERSONAL', 'DEBT', 'DONATION', 'ENTERTAINMENT'),
-- expenditure_tag VARCHAR(20),
expenditure_id INT NOT NULL,
FOREIGN KEY(expenditure_id) REFERENCES expenditure(expenditure_id)
);

-- Drop Table
Drop Table category;
Drop Table expenditure;
Drop table income;
Drop table login;

-- Delete rows from table
DELETE FROM login WHERE user_id = 'a2b0c20300414183344';
DELETE FROM income WHERE income_id = '2';
DELETE FROM expenditure WHERE expenditure_id > 1;
DELETE FROM category WHERE expenditure_id = 1;

-- Select Statements
SELECT * FROM finance_tracker.login;
SELECT * FROM finance_tracker.income;
SELECT * FROM finance_tracker.expenditure;
SELECT * FROM finance_tracker.category;

-- Describe Table
DESCRIBE LOGIN;
DESCRIBE income;
DESCRIBE CATEGORY; 