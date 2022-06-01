package com.example.demoservicesshopping.client;

import com.example.demoservicesshopping.modelrest.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("cliente-service")
@RequestMapping("/clientes")
public interface ClienteClient {

    @GetMapping(path = "/{idCliente}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("idCliente") Long idCliente);
}
