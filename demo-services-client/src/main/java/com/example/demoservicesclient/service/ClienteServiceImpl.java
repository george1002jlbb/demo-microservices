package com.example.demoservicesclient.service;

import com.example.demoservicesclient.model.Cliente;
import com.example.demoservicesclient.model.Region;
import com.example.demoservicesclient.repository.ClienteRepository;
import com.example.demoservicesclient.repository.RegionRepository;
import com.example.demoservicesclient.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Cliente save(Cliente cliente) {
        Cliente clientec = clienteRepository.findByNumberId(cliente.getNumberId());
        if(clientec != null){
            return clientec;
        }
        cliente.setStatus("A");
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        Cliente clientem = get(cliente.getId());
        if(clientem == null){
            return null;
        }
        clientem.setNombre(cliente.getNombre());
        clientem.setApellido(cliente.getApellido());
        clientem.setCorreo(cliente.getCorreo());

        return clienteRepository.save(clientem);
    }

    @Override
    public Cliente get(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente delete(Long id) {
        Cliente clientee = get(id);
        if(clientee == null){
            return null;
        }
        clientee.setStatus("E");
        return clienteRepository.save(clientee);
    }

    @Override
    public Cliente findByNumberId(String numberId) {
        return clienteRepository.findByNumberId(numberId);
    }

    @Override
    public List<Cliente> findByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public List<Cliente> findByApellido(String apellido) {
        return clienteRepository.findByApellido(apellido);
    }

    @Override
    public Cliente findByRegion(Region region) {
        return clienteRepository.findByRegion(region);
    }

    @Override
    public List<Cliente> listAll() {
        return clienteRepository.findAll();
    }
}
