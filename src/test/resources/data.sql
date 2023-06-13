CREATE TABLE IF NOT EXISTS passenger (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    surname VARCHAR(100),
    birth_date DATE
);

INSERT INTO passenger (id, name, surname, birth_date) VALUES(1, 'Ivan', 'Ivanov', '2005-05-12');

