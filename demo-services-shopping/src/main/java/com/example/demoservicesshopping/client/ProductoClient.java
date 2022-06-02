package com.example.demoservicesshopping.client;

import com.example.demoservicesshopping.modelrest.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "producto-service", path = "/productos", fallback = ClienteHystrixFallbackFactory.class)
public interface ProductoClient {

    @GetMapping(path = "/{idProducto}")
    public ResponseEntity<Producto> getProducto(@PathVariable("idProducto") Long idProducto);

    @GetMapping(path = "/{idProducto}/stock")
    public ResponseEntity<Producto> updateStockProducto(@PathVariable("idProducto") long id, @RequestParam(value = "cantidad", required = true) Double cantidad);
}
