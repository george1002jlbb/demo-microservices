package com.example.demoservicesclient.repository;

import com.example.demoservicesclient.model.Cliente;
import com.example.demoservicesclient.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByNumberId(String numberId);
    public List<Cliente> findByNombre(String nombre);
    public List<Cliente> findByApellido(String apellido);
    public Cliente findByRegion(Region region);
}
