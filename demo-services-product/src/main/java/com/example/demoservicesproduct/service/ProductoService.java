package com.example.demoservicesproduct.service;

import com.example.demoservicesproduct.model.Categoria;
import com.example.demoservicesproduct.model.Producto;

import java.util.List;

public interface ProductoService {

    public Producto save(Producto producto);
    public Producto update(Producto producto);
    public Producto get(long id);
    public Producto delete(long id);
    public List<Producto> findByCategoria(Categoria categoria);
    public Producto updateStock(long id, Double cantidad);
    public List<Producto> listAll();
}
