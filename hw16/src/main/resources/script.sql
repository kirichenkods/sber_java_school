CREATE TABLE IF NOT EXISTS recipe
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(200) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE IF NOT EXISTS ingredient
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS recipe_body
(
    id            SERIAL PRIMARY KEY,
    recipe_id     INTEGER,
    ingredient_id INTEGER,
    quantity      NUMERIC     NOT NULL,
    unit          VARCHAR(50) NOT NULL
);

ALTER TABLE recipe_body
    ADD CONSTRAINT fk_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (id);

ALTER TABLE recipe_body
    ADD CONSTRAINT fk_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE recipe_body
    ADD CONSTRAINT uq_recipe_body UNIQUE (recipe_id, ingredient_id);