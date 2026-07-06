CREATE TABLE Product (
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    price    NUMERIC(10, 2) NOT NULL
             CHECK (price >= 0)
);

INSERT INTO Product (name, price) VALUES
    ('Ноутбук', 55000.00),
    ('Мышь', 799.90),
    ('Клавиатура', 1990.00);

SELECT * FROM Product;
