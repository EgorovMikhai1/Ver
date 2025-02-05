-- Очистка таблиц перед вставкой данных
DELETE FROM users_roles;
DELETE FROM roles_authorities;
DELETE FROM rentals;
DELETE FROM cars;
DELETE FROM users;
DELETE FROM roles;
DELETE FROM authorities;

-- Insert users
INSERT INTO users (user_name, user_email)
VALUES ('John Doe', 'john@example.com'),
       ('Jane Smith', 'jane@example.com'),
       ('Alice Johnson', 'alice@example.com'),
       ('Bob Brown', 'bob@example.com');

-- Insert roles
INSERT INTO roles (role_name)
VALUES ('ADMIN'),
       ('USER'),
       ('MANAGER'),
       ('GUEST');

-- Assign roles to users
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);

-- Insert authorities
INSERT INTO authorities (authority_name)
VALUES ('READ_PRIVILEGES'),
       ('WRITE_PRIVILEGES'),
       ('DELETE_PRIVILEGES'),
       ('EXECUTE_PRIVILEGES');

-- Assign authorities to roles
INSERT INTO roles_authorities (role_id, authority_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 3);

-- Insert cars
INSERT INTO cars (car_model, car_brand, car_price_per_day)
VALUES ('X5', 'BMW', 100.00),
       ('Corsa', 'OPEL', 50.00),
       ('A4', 'AUDI', 80.00),
       ('Model 3', 'TESLA', 120.00);

-- Insert rentals
INSERT INTO rentals (rental_start_date, rental_end_date, rental_total_cost, user_id, car_id)
VALUES ('2024-02-01', '2024-02-05', 500.00, 1, 1),
       ('2024-02-10', '2024-02-15', 250.00, 2, 2),
       ('2024-03-01', '2024-03-05', 400.00, 3, 3),
       ('2024-04-01', '2024-04-05', 600.00, 4, 4);