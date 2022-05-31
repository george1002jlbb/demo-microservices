package com.example.demoservicesshopping.controller;

import com.example.demoservicesshopping.exception.ErrorMessage;
import com.example.demoservicesshopping.model.Factura;
import com.example.demoservicesshopping.service.FacturaService;
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
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<Factura> guardarFactura(@Valid @RequestBody Factura factura, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Factura facturac = facturaService.create(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturac);
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<List<Factura>> listafacturas(){
        List<Factura> facturas = facturaService.list();
        if(facturas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }

    @GetMapping(path = "/{idFactura}")
    public ResponseEntity<Factura> getfactura(@PathVariable("idFactura") Long idFactura){
        log.info("idFactura ", idFactura);
        Factura factura = facturaService.get(idFactura);
        if(factura == null){
            log.error("idFactura error: ", idFactura);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @PutMapping(path = "/{idFactura}")
    public ResponseEntity<Factura> updateFactura(@PathVariable("idFactura") Long idFactura, @RequestBody Factura factura){
        factura.setId(idFactura);
        Factura facturau = facturaService.update(factura);
        if(facturau == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturau);
    }

    @DeleteMapping(path = "/{idFactura}")
    public ResponseEntity<Factura> deleteFactura(@PathVariable("idFactura") Long idFactura){
        Factura facturad = facturaService.delete(idFactura);
        if(facturad == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturad);
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
