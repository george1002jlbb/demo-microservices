package com.example.demoservicesshopping.repository;

import com.example.demoservicesshopping.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    public List<Factura> findByIdcliente(Long idcliente );
    public Factura findByNroFactura(String nroFactura);
}
