package com.example.demoservicesproduct.repository;

import com.example.demoservicesproduct.model.Categoria;
import com.example.demoservicesproduct.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    public List<Producto> findByCategoria(Categoria categoria);
}
