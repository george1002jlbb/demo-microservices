package com.example.demoservicesshopping.service;

import com.example.demoservicesshopping.client.ClienteClient;
import com.example.demoservicesshopping.client.ProductoClient;
import com.example.demoservicesshopping.model.Factura;
import com.example.demoservicesshopping.model.FacturaItem;
import com.example.demoservicesshopping.modelrest.Cliente;
import com.example.demoservicesshopping.modelrest.Producto;
import com.example.demoservicesshopping.service.repository.FacturaItemRepository;
import com.example.demoservicesshopping.service.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaItemRepository facturaItemRepository;
    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private ProductoClient productoClient;

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
        facturac = facturaRepository.save(factura);
        facturac.getItems().forEach( facturaItem ->{
            productoClient.updateStockProducto( facturaItem.getIdproducto(), facturaItem.getCantidad() * -1 );
        });
        return facturac;
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
        Factura factura = facturaRepository.findById(id).orElse(null);
        if(factura != null){
            Cliente cliente = clienteClient.getCliente( factura.getIdcliente() ).getBody();
            factura.setCliente(cliente);
            List<FacturaItem> listItems = factura.getItems().stream().map( facturaItem -> {
                Producto producto = productoClient.getProducto(facturaItem.getIdproducto()).getBody();
                facturaItem.setProducto(producto);
                return facturaItem;
            }).collect(Collectors.toList());
            factura.setItems(listItems);
        }
        return factura;
    }
}
