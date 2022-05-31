package com.example.demoservicesclient.service;

import com.example.demoservicesclient.model.Cliente;
import com.example.demoservicesclient.model.Region;

import java.util.List;

public interface ClienteService {

    public Cliente save(Cliente cliente);
    public Cliente update(Cliente cliente);
    public Cliente get(Long id);
    public Cliente delete(Long id);
    public Cliente findByNumberId(String id);
    public List<Cliente> findByNombre(String nombre);
    public List<Cliente> findByApellido(String apellido);
    public Cliente findByRegion(Region region);
    public List<Cliente> listAll();
}
