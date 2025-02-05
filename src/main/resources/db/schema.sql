CREATE TABLE IF NOT EXISTS users
(
    user_id    INT AUTO_INCREMENT PRIMARY KEY,
    user_name  VARCHAR(255)        NOT NULL,
    user_email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id   INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS authorities
(
    authority_id   INT AUTO_INCREMENT PRIMARY KEY,
    authority_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS roles_authorities
(
    role_id      INT,
    authority_id INT,
    PRIMARY KEY (role_id, authority_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE,
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cars
(
    car_id            INT AUTO_INCREMENT PRIMARY KEY,
    car_model         VARCHAR(255)   NOT NULL,
    car_brand         VARCHAR(255)   NOT NULL,
    car_price_per_day DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS rentals
(
    rental_id         INT AUTO_INCREMENT PRIMARY KEY,
    rental_start_date DATE           NOT NULL,
    rental_end_date   DATE           NOT NULL,
    rental_total_cost DECIMAL(10, 2) NOT NULL,
    user_id           INT,
    car_id            INT,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE SET NULL,
    FOREIGN KEY (car_id) REFERENCES cars (car_id) ON DELETE SET NULL
);