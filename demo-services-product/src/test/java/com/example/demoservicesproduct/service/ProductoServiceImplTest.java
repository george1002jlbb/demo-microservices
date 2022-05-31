package com.example.demoservicesproduct.service;

import com.example.demoservicesproduct.model.Categoria;
import com.example.demoservicesproduct.model.Producto;
import com.example.demoservicesproduct.repository.ProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    private ProductoService productoService;

    @BeforeEach
    public void Setup(){
        MockitoAnnotations.initMocks(this);
        productoService = new ProductoServiceImpl(productoRepository);
        Producto p1 = Producto.builder()
                .id(1L)
                .name("computer")
                .categoria(Categoria.builder().id(1).build())
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("2500000"))
                .build();
        Mockito.when(productoRepository.findById(1L))
                .thenReturn(Optional.of(p1));
        Mockito.when(productoRepository.save(p1)).thenReturn(p1);
    }

    @Test
    void get() {
        Producto p = productoService.get(1L);

        org.assertj.core.api.Assertions.assertThat(p.getName()).isEqualTo("computer");
    }

    @Test
    void updateStock(){
        Producto p = productoService.updateStock(1L, Double.parseDouble("8"));
        org.assertj.core.api.Assertions.assertThat(p.getStock()).isEqualTo(18);
    }
}