DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurant (name, votes) VALUES
  ('Р1 Mak', 2),
  ('Р2 Плакучая ива', 12),
  ('Р3 Бистро', 7);

INSERT INTO users (name, email, password, registered, voted_id, voted_date)
VALUES ('User', 'user@ya.ru', 'password', '2017-01-10 12:00:00', 100000, '2017-01-30 10:00:00');

INSERT INTO users (name, email, password, registered, voted_id, voted_date)
VALUES ('Admin', 'admin@gmail.com', 'admin', '2017-01-09 12:00:00', 100002, '2017-01-20 11:00:00');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100003),
  ('ROLE_USER', 100004),
  ('ROLE_ADMIN', 100004);



INSERT INTO meals (description, price, restaurant_id, inmenu) VALUES
  ('Р1 Завтрак в меню', 200, 100000, TRUE),
  ('Р1 Обед не в меню', 1050, 100000, FALSE),
  ('Р1 Ужин в меню', 945, 100000, TRUE),
  ('Р2 Завтрак в меню', 200, 100001, TRUE),
  ('Р2 Обед в меню', 250, 100001, TRUE),
  ('Р2 Ужин не в меню', 250, 100001, FALSE),
  ('Р3 Завтрак-б в меню', 90, 100002, TRUE),
  ('Р3 Обед-б в меню', 90, 100002, TRUE),
  ('Р3 Ужин-б не в меню', 90, 100002, FALSE);

