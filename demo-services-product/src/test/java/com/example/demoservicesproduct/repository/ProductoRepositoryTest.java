package com.example.demoservicesproduct.repository;

import com.example.demoservicesproduct.model.Categoria;
import com.example.demoservicesproduct.model.Producto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void findByCategoria() {
        Producto p1 = Producto.builder()
                .name("computer")
                .categoria(Categoria.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("2500000"))
                .status("A")
                .createAt(new Date())
                .build();
        productoRepository.save(p1);

        List<Producto> lsProductos = productoRepository.findByCategoria(p1.getCategoria());
        Assertions.assertThat(lsProductos.size()).isEqualTo(1);
    }

    @Test
    void findAll(){
        Producto p1 = Producto.builder()
                .name("computer")
                .categoria(Categoria.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("2500000"))
                .status("A")
                .createAt(new Date())
                .build();
        productoRepository.save(p1);

        Producto p2 = Producto.builder()
                .name("Shoes")
                .categoria(Categoria.builder().id(2L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("300000"))
                .status("A")
                .createAt(new Date())
                .build();
        productoRepository.save(p2);

        Producto p3 = Producto.builder()
                .name("Mac Pro")
                .categoria(Categoria.builder().id(3L).build())
                .description("")
                .stock(Double.parseDouble("1"))
                .price(Double.parseDouble("2500000"))
                .status("A")
                .createAt(new Date())
                .build();
        productoRepository.save(p3);
        List<Producto> lsProductos = productoRepository.findAll();
        Assertions.assertThat(lsProductos.size()).isEqualTo(3);
    }
}