INSERT INTO tbl_categoria (id, name) VALUES (1, 'Shoes');
INSERT INTO tbl_categoria (id, name) VALUES (2, 'Books');
INSERT INTO tbl_categoria (id, name) VALUES (3, 'Electronics Devices');

INSERT INTO tbl_producto (id, name, description, stock, price, status, create_at, idcategoria)
VALUES (1, 'Adidas', 'Adidas Shoes', 5, 250900, 'A', '2022-05-25', 1);

INSERT INTO tbl_producto (id, name, description, stock, price, status, create_at, idcategoria)
VALUES (2, 'Big Data', 'Books', 5, 85900, 'A', '2022-05-25', 1);

INSERT INTO tbl_producto (id, name, description, stock, price, status, create_at, idcategoria)
VALUES (3, 'Laptop Mac Pro', 'Electronics', 5, 6085900, 'A', '2022-05-25', 3);