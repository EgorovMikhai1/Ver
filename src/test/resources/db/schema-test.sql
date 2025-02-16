SET REFERENTIAL_INTEGRITY FALSE;

DROP TABLE IF EXISTS rentals;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS roles_authorities;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id       INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name     VARCHAR(255)        NOT NULL,
    user_email    VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(255)        NOT NULL
);

CREATE TABLE roles
(
    role_id   INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users_roles
(
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE
);

CREATE TABLE authorities
(
    authority_id   INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    authority_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE roles_authorities
(
    role_id      INT,
    authority_id INT,
    PRIMARY KEY (role_id, authority_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE,
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id) ON DELETE CASCADE
);

CREATE TABLE cars
(
    car_id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    car_model         VARCHAR(255)   NOT NULL,
    car_brand         VARCHAR(255)   NOT NULL,
    car_price_per_day DECIMAL(10, 2) NOT NULL
);

CREATE TABLE rentals
(
    rental_id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    rental_start_date DATE           NOT NULL,
    rental_end_date   DATE           NOT NULL,
    rental_total_cost DECIMAL(10, 2) NOT NULL,
    user_id           INT,
    car_id            INT,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE SET NULL,
    FOREIGN KEY (car_id) REFERENCES cars (car_id) ON DELETE SET NULL
);

SET REFERENTIAL_INTEGRITY TRUE;