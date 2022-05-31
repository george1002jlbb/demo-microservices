package com.example.demoservicesproduct.service;

import com.example.demoservicesproduct.model.Categoria;
import com.example.demoservicesproduct.model.Producto;
import com.example.demoservicesproduct.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private final ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        producto.setStatus("A");
        producto.setCreateAt(new Date());
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Producto producto) {
        Producto productom = get(producto.getId());
        if(productom == null){
            return null;
        }
        productom.setName(producto.getName());
        productom.setDescription(producto.getDescription());
        productom.setCategoria(producto.getCategoria());
        productom.setPrice(producto.getPrice());
        return productoRepository.save(productom);
    }

    @Override
    public Producto get(long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto delete(long id) {
        Producto productoe = get(id);
        if(productoe == null){
            return null;
        }
        productoe.setStatus("E");
        return productoRepository.save(productoe);
    }
    @Override
    public List<Producto> findByCategoria(Categoria categoria){
        return productoRepository.findByCategoria(categoria);
    }

    @Override
    public Producto updateStock(long id, Double cantidad) {
        Producto productoms = get(id);
        if(productoms == null){
            return null;
        }
        productoms.setStock( productoms.getStock() + cantidad );
        return productoRepository.save(productoms);
    }

    @Override
    public List<Producto> listAll() {
        return productoRepository.findAll();
    }
}
