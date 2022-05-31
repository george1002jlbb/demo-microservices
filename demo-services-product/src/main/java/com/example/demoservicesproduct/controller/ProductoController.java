package com.example.demoservicesproduct.controller;

import com.example.demoservicesproduct.exception.ErrorMessage;
import com.example.demoservicesproduct.model.Categoria;
import com.example.demoservicesproduct.model.Producto;
import com.example.demoservicesproduct.service.ProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> guardarProductos(@Valid @RequestBody Producto producto, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Producto productoc = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoc);
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<List<Producto>> listaProductos(){
        List<Producto> productos = productoService.listAll();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping(path = "/categorias")
    public ResponseEntity<List<Producto>> listaProductosCategoria_request(@RequestParam(name = "idCategoria", required = false) Long idCategoria){
        List<Producto> productos = new ArrayList<>();
        if(idCategoria == null){
            productos = productoService.listAll();
            if(productos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else {
            productos = productoService.findByCategoria(Categoria.builder().id(idCategoria).build());
            if(productos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping(path = "/categorias/{idCategoria}")
    public ResponseEntity<List<Producto>> listaProductosCategoria_path(@PathVariable("idCategoria") Long idCategoria){
        List<Producto> productos = new ArrayList<>();
        if(idCategoria == null){
            productos = productoService.listAll();
            if(productos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else {
            productos = productoService.findByCategoria(Categoria.builder().id(idCategoria).build());
            if(productos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping(path = "/{idProducto}")
    public ResponseEntity<Producto> getProducto(@PathVariable("idProducto") Long idProducto){
        Producto producto = productoService.get(idProducto);
        if(producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PutMapping(path = "/{idProducto}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("idProducto") Long idProducto, @RequestBody Producto producto){
        producto.setId(idProducto);
        Producto productou = productoService.update(producto);
        if(productou == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productou);
    }

    @DeleteMapping(path = "/{idProducto}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable("idProducto") long id){
        Producto productod = productoService.delete(id);
        if(productod == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productod);
    }

    @GetMapping(path = "/{idProducto}/stock")
    public ResponseEntity<Producto> updateStockProducto(@PathVariable("idProducto") long id, @RequestParam(value = "cantidad", required = true) Double cantidad){
        Producto productou = productoService.updateStock(id, cantidad);
        if(productou == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productou);
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
