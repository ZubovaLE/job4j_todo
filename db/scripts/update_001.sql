CREATE TABLE IF NOT EXISTS items
(
    id          SERIAL PRIMARY KEY,
    description TEXT,
    created     TIMESTAMP,
    done        BOOLEAN
);