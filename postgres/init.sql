CREATE SEQUENCE product_id_seq;

CREATE TABLE product (
    id VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('PROD_', LPAD(NEXTVAL('product_id_seq')::text, 10, '0')),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INTEGER NOT NULL,
    crtuser VARCHAR(20),
    crttime TIMESTAMP DEFAULT NOW(),
    upduser VARCHAR(20),
    updtime TIMESTAMP 
);

CREATE TABLE product_image (
    id SERIAL PRIMARY KEY,
    image BYTEA,
    product_id VARCHAR(12) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO product (name, description, price, quantity, crtuser, crttime) VALUES
('Product 1', 'This is product 1', 100.00, 10, 'user1', NOW()),
('Product 2', 'This is product 2', 200.00, 20, 'user2', NOW()),
('Product 3', 'This is product 3', 300.00, 30, 'user3', NOW()),
('Product 4', 'This is product 4', 400.00, 40, 'user4', NOW()),
('Product 5', 'This is product 5', 500.00, 50, 'user5', NOW());





