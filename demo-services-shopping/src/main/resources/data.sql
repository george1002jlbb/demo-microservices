INSERT INTO tbl_factura (id, nroFactura, idcliente, descripcion, create_at, status)
VALUES(1, '0001', 1, 'invoice office items', NOW(),'A');

INSERT INTO tbl_factura_item ( id, cantidad, precio, idproducto, iditem) VALUES(1, 10 , 17.889, 1, 1);
INSERT INTO tbl_factura_item ( id, cantidad, precio, idproducto, iditem)  VALUES(2, 12 , 12.255, 2, 1);
INSERT INTO tbl_factura_item ( id, cantidad, precio, idproducto, iditem)  VALUES(3, 14 , 40.506, 3, 1);
