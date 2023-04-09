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
income_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(40) NOT NULL,
FOREIGN KEY(user_id) REFERENCES login(user_id),
income_date DATE,
income_amount DECIMAL(10,2)
);

CREATE TABLE expenditure
(
expenditure_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(40) NOT NULL,
FOREIGN KEY(user_id) REFERENCES login(user_id),
expenditure_date DATE,
expenditure_amount DECIMAL(10,2)
);

CREATE TABLE category
(
category_id INT PRIMARY KEY AUTO_INCREMENT,
expenditure_tag ENUM ('food', 'utilities', 'housing', 'transportation', 'education', 'clothing', 'medical', 'insurance', 'household', 'personal', 'debt', 'donation', 'entertainment'),
expenditure_id INT NOT NULL,
FOREIGN KEY(expenditure_id) REFERENCES expenditure(expenditure_id)
);

-- Drop Table
Drop Table category;
Drop Table expenditure;
Drop table income;
Drop table login;

-- Delete rows from table
DELETE FROM login WHERE user_id = 'random';

-- Select Statements
SELECT * FROM login;
SELECT * FROM income;

-- Describe Table
DESC LOGIN;