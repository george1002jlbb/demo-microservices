DROP TABLE IF EXISTS tbl_region;

CREATE TABLE tbl_region (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS tbl_cliente;

CREATE TABLE tbl_cliente (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    idnumber VARCHAR(8) NOT NULL,
    nombre VARCHAR(250) NOT NULL,
    apellido VARCHAR(250) NOT NULL,
    correo VARCHAR(250),
    status VARCHAR(250) NOT NULL,
    idregion BIGINT
);