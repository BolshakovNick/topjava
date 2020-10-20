DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (id, user_id, date_time, description, calories)
VALUES (1, 100000, TIMESTAMP '2020-1-30 10:00', 'Завтрак', 500),
       (2, 100000, TIMESTAMP '2020-1-30 13:00', 'Обед', 1000),
       (3, 100001, TIMESTAMP '2015-6-1 14:00', 'Админ ланч', 510),
       (4, 100001, TIMESTAMP '2015-6-1 14:00', 'Админ ужин', 1500);