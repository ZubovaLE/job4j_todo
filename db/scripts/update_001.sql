CREATE TABLE IF NOT EXISTS items
(
    id          SERIAL PRIMARY KEY,
    name        TEXT,
    description TEXT,
    created     TIMESTAMP,
    done        BOOLEAN
);

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    email    TEXT,
    password TEXT,
    item_id  INTEGER REFERENCES items (id)
);

ALTER TABLE users
    ADD CONSTRAINT unique_email UNIQUE (email),
    DROP COLUMN item_id;

DELETE
FROM items;

ALTER TABLE items
    ADD COLUMN user_id INTEGER NOT NULL REFERENCES users (id);