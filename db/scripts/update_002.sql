CREATE TABLE IF NOT EXISTS categories
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS items_categories
(
    item_id       INTEGER NOT NULL REFERENCES items (id),
    categories_id INTEGER NOT NULL REFERENCES categories (id)
);

INSERT INTO categories(name)
VALUES ('Программирование'),
       ('Дом'),
       ('Учёба'),
       ('Тренажёрный зал'),
       ('Семья')