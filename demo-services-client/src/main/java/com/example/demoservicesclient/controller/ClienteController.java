package com.example.demoservicesclient.controller;

import com.example.demoservicesclient.exception.ErrorMessage;
import com.example.demoservicesclient.model.Cliente;
import com.example.demoservicesclient.repository.service.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> guardarClientes(@Valid @RequestBody Cliente cliente, BindingResult result){
        log.info("clientec jlbb ", cliente.getNumberId());
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Cliente clientec = clienteService.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientec);
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<List<Cliente>> listaClientes(){
        List<Cliente> productos = clienteService.listAll();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping(path = "/{idCliente}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("idCliente") Long idCliente){
        Cliente cliente = clienteService.get(idCliente);
        if(cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PutMapping(path = "/{idCliente}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("idCliente") Long idCliente, @RequestBody Cliente cliente){
        cliente.setId(idCliente);
        Cliente clienteu = clienteService.update(cliente);
        if(clienteu == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteu);
    }

    @DeleteMapping(path = "/{idCliente}")
    public ResponseEntity<Cliente> deleteProducto(@PathVariable("idCliente") long idCliente){
        Cliente cliented = clienteService.delete(idCliente);
        if(cliented == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliented);
    }

    private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try{
            // convetir el objeto errorMessage en un JSON String
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }
}
