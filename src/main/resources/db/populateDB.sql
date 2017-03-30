DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurant (name) VALUES
  ('Mak'),
  ('Плакучая ива'),
  ('Столовая');

INSERT INTO users (name, email, password, registered)
VALUES ('User', 'user@ya.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', '2017-01-10 12:00:00');

INSERT INTO users (name, email, password, registered)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju', '2017-01-09 12:00:00');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100003),
  ('ROLE_USER', 100004),
  ('ROLE_ADMIN', 100004);


INSERT INTO meals (description, price, restaurant_id, inmenu) VALUES
  ('Гамбургер', 200, 100000, TRUE),
  ('Чизбургер', 1050, 100000, FALSE),
  ('Картошка', 945, 100000, TRUE),
  ('Стейк', 200, 100001, TRUE),
  ('Паста', 250, 100001, TRUE),
  ('Дичь', 250, 100001, FALSE),
  ('Завтрак', 190, 100002, TRUE),
  ('Обед', 90, 100002, TRUE),
  ('Комплекс', 290, 100002, FALSE),
  ('Кампот', 390, 100002, TRUE ),
  ('Ужин', 490, 100002, TRUE ),
  ('Кефир', 590, 100002, TRUE );

INSERT INTO votes (VOTED_DATE, USER_ID, RESTAURANT_ID) VALUES
  ('2017-01-11 10:00:00',100003,100001),
  ('2017-01-12 10:00:00',100003,100001),
  ('2017-01-13 10:00:00',100003,100001),
  ('2017-01-14 10:00:00',100003,100000),
  ('2017-01-15 10:00:00',100003,100002),
  ('2017-01-16 10:00:00',100003,100002),

  ('2017-01-11 10:00:00',100004,100001),
  ('2017-01-12 10:00:00',100004,100002),
  ('2017-01-13 10:00:00',100004,100001),
  ('2017-01-14 10:00:00',100004,100001),
  ('2017-01-15 10:00:00',100004,100002),
  ('2017-01-16 10:00:00',100004,100001),
  ('2017-01-17 10:00:00',100004,100000);


