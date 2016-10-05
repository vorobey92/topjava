DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime ,description, calories, user_id)
VALUES ('2016-10-05 10:00:00', 'админ завтрак', 10, 100001),
       ('2016-10-05 19:00:00', 'админ ужин', 3000, 100001),
       ('2016-10-04 13:00:00', 'user break', 500, 100000),
       ('2016-10-05 18:00:00', 'user dinner', 1500, 100000);
