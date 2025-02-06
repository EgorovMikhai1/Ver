INSERT INTO users (user_name, user_email)
VALUES ('Alice Johnson', 'alice@example.com'),
       ('Bob Smith', 'bob@example.com'),
       ('Charlie Davis', 'charlie@example.com');

INSERT INTO roles (role_name)
VALUES ('ADMIN'),
       ('USER'),
       ('MANAGER');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO authorities (authority_name)
VALUES ('READ_PRIVILEGES'),
       ('WRITE_PRIVILEGES'),
       ('DELETE_PRIVILEGES');

INSERT INTO roles_authorities (role_id, authority_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (3, 1),
       (3, 2);

INSERT INTO cars (car_model, car_brand, car_price_per_day)
VALUES ('Model S', 'Tesla', 120.00),
       ('Civic', 'Honda', 50.00),
       ('Camry', 'Toyota', 65.00),
       ('Mustang', 'Ford', 100.00),
       ('Golf', 'Volkswagen', 55.00);

INSERT INTO rentals (rental_start_date, rental_end_date, rental_total_cost, user_id, car_id)
VALUES ('2024-02-01', '2024-02-05', 480.00, 1, 1),
       ('2024-02-03', '2024-02-07', 200.00, 2, 2),
       ('2024-02-10', '2024-02-15', 325.00, 3, 3),
       ('2024-02-12', '2024-02-14', 200.00, 1, 4),
       ('2024-02-15', '2024-02-20', 275.00, 2, 5);