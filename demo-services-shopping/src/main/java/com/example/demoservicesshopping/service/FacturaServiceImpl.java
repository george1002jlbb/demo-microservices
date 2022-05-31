package com.example.demoservicesshopping.service;

import com.example.demoservicesshopping.model.Factura;
import com.example.demoservicesshopping.repository.FacturaItemRepository;
import com.example.demoservicesshopping.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaItemRepository facturaItemRepository;

    @Override
    public List<Factura> list() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura create(Factura factura) {
        Factura facturac = facturaRepository.findByNroFactura( factura.getNroFactura() );
        if(facturac != null){
            return facturac;
        }
        factura.setStatus("A");
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(Factura factura) {
        Factura facturam = get(factura.getId());
        if(facturam == null){
            return null;
        }
        facturam.setIdcliente(factura.getIdcliente());
        facturam.setDescripcion(factura.getDescripcion());
        facturam.getItems().clear();
        facturam.setItems(factura.getItems());

        return facturaRepository.save(facturam);
    }

    @Override
    public Factura delete(Long id) {
        Factura facturae = get(id);
        if(facturae == null){
            return null;
        }
        facturae.setStatus("E");
        return facturaRepository.save(facturae);
    }

    @Override
    public Factura get(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }
}
