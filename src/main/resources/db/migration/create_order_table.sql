CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    order_date DATE NOT NULL,
    total_value DOUBLE NOT NULL
);