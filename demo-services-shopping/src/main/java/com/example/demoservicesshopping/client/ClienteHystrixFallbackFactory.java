package com.example.demoservicesshopping.client;

import com.example.demoservicesshopping.modelrest.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteHystrixFallbackFactory implements ClienteClient{
    @Override
    public ResponseEntity<Cliente> getCliente(Long idCliente) {
        Cliente cliente = Cliente.builder()
                .numberId("none")
                .nombre("none")
                .apellido("none")
                .correo("none")
                .status("none").build();
        return ResponseEntity.ok(cliente);
    }
}
