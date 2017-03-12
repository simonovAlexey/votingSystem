DROP TABLE user_roles
IF EXISTS;
DROP TABLE meals
IF EXISTS;
DROP TABLE votes
IF EXISTS;
DROP TABLE users
IF EXISTS;
DROP TABLE restaurant
IF EXISTS;
DROP SEQUENCE global_seq
IF EXISTS;

CREATE SEQUENCE global_seq
  AS INTEGER
    START WITH 100000;

CREATE TABLE restaurant
(
  id    INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name  VARCHAR(255) NOT NULL,
);

CREATE TABLE users
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOLEAN   DEFAULT TRUE
);

CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE votes
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  voted_date    TIMESTAMP DEFAULT NULL,
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurant (id)
    ON DELETE CASCADE
);
CREATE UNIQUE INDEX user_votes_unique_date_idx
  ON votes (user_id,voted_date);

CREATE TABLE meals
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  description   VARCHAR(255) NOT NULL,
  price         INT          NOT NULL,
  restaurant_id INTEGER      NOT NULL,
  inmenu        BOOLEAN DEFAULT FALSE,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurant (id)
    ON DELETE CASCADE
);
CREATE INDEX meals_in_menu_idx
  ON meals (inmenu, restaurant_id);