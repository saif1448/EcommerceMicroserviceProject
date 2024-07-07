-- Insert data into category table
INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Electronic devices', 'Electronics');

INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Clothing and Accessories', 'Fashion');

-- Insert data into product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Smartphone with 6GB RAM', 'Smartphone', 100, 299.99, (SELECT id FROM category WHERE name = 'Electronics'));

INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Laptop with i7 processor', 'Laptop', 50, 799.99, (SELECT id FROM category WHERE name = 'Electronics'));

INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Cotton T-Shirt', 'T-Shirt', 200, 19.99, (SELECT id FROM category WHERE name = 'Fashion'));

INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Leather Jacket', 'Jacket', 100, 99.99, (SELECT id FROM category WHERE name = 'Fashion'));
