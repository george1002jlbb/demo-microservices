package com.example.demoservicesshopping.service;

import com.example.demoservicesshopping.model.Factura;

import java.util.List;

public interface FacturaService {
    public List<Factura> list();
    public Factura create(Factura factura);
    public Factura update(Factura factura);
    public Factura delete(Long id);
    public Factura get(Long id);
}
