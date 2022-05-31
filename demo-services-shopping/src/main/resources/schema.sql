DROP TABLE IF EXISTS tbl_factura;

CREATE TABLE tbl_factura (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  nroFactura VARCHAR(250) NOT NULL,
  idcliente BIGINT NOT NULL,
  descripcion VARCHAR(250) NOT NULL,
  create_at TIMESTAMP,
  status VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS tbl_factura_item;

CREATE TABLE tbl_factura_item (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  cantidad DOUBLE,
  precio DOUBLE,
  idproducto BIGINT,
  iditem BIGINT
);