CREATE DATABASE finance_tracker;
USE finance_tracker;

CREATE TABLE login
(
email_id VARCHAR(40) PRIMARY KEY,
password VARCHAR(25)
);

CREATE TABLE income
(
income_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(40),
FOREIGN KEY(user_id) REFERENCES login(email_id),
income_date DATE,
income_amount DECIMAL(10,2)
);

CREATE TABLE expenditure
(
expenditure_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(40),
FOREIGN KEY(user_id) REFERENCES login(email_id),
expenditure_date DATE,
expenditure_amount DECIMAL(10,2),
expenditure_tag ENUM ('food', 'utilities', 'housing', 'transportation', 'education', 'clothing', 'medical', 'insurance', 'household', 'personal', 'debt', 'donation', 'entertainment')
);

CREATE TABLE category
(
category_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(40),
FOREIGN KEY(user_id) REFERENCES login(email_id),
expenditure_tag ENUM ('food', 'utilities', 'housing', 'transportation', 'education', 'clothing', 'medical', 'insurance', 'household', 'personal', 'debt', 'donation', 'entertainment'),
expenditure_id INT,
FOREIGN KEY(expenditure_id) REFERENCES expenditure(expenditure_id)
);

-- Select Statements

SELECT * FROM login;

DESC LOGIN;