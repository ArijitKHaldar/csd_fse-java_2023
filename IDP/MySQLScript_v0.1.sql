-- -----------------------------------------------------
-- Database finance_tracker
-- -----------------------------------------------------
CREATE DATABASE finance_tracker;
USE finance_tracker;

-- -----------------------------------------------------
-- Table finance_tracker.login
-- -----------------------------------------------------
CREATE TABLE finance_tracker.login (
    user_id VARCHAR(255) NOT NULL,
    email_id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (email_id)
);

-- -----------------------------------------------------
-- Table finance_tracker.income
-- -----------------------------------------------------
CREATE TABLE finance_tracker.income (
    income_id INT NOT NULL AUTO_INCREMENT,
    income_amount DECIMAL(10 , 2 ),
    income_date DATE,
    user_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (income_id),
    FOREIGN KEY (user_id)
        REFERENCES finance_tracker.login (user_id)
);

-- -----------------------------------------------------
-- Table finance_tracker.expenditure
-- -----------------------------------------------------
CREATE TABLE finance_tracker.expenditure (
    expenditure_id INT NOT NULL AUTO_INCREMENT,
    category_id INT,
    expenditure_amount DECIMAL(10 , 2 ),
    expenditure_date DATE,
    user_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (expenditure_id),
    FOREIGN KEY (user_id)
        REFERENCES finance_tracker.login (user_id),
    FOREIGN KEY (category_id)
        REFERENCES finance_tracker.category (category_id)
);

-- -----------------------------------------------------
-- Table finance_tracker.category
-- -----------------------------------------------------
CREATE TABLE finance_tracker.category (
    category_id INT NOT NULL AUTO_INCREMENT,
    expenditure_tag VARCHAR(255),
    PRIMARY KEY (category_id)
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
DESCRIBE expenditure;
DESCRIBE CATEGORY; 