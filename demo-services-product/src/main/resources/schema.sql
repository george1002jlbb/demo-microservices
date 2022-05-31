DROP TABLE IF EXISTS tbl_categoria;

CREATE TABLE tbl_categoria (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS tbl_producto;

CREATE TABLE tbl_producto (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  stock DOUBLE,
  price DOUBLE,
  status VARCHAR(250) NOT NULL,
  create_at TIMESTAMP,
  idcategoria BIGINT
);