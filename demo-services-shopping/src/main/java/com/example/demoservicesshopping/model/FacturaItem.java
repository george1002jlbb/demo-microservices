package com.example.demoservicesshopping.model;

import com.example.demoservicesshopping.modelrest.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tbl_factura_item")
@Data
@AllArgsConstructor
@Builder
public class FacturaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Double cantidad;
    private Double precio;
    private Long idproducto;
    @Transient
    private Double subtotal; // no va hacer registrado en nuestra base de datos
    @Transient
    private Producto producto;

    public Double getSubTotal(){
        if (this.precio >0  && this.cantidad >0 ){
            return this.cantidad * this.precio;
        }else {
            return (double) 0;
        }
    }
    public FacturaItem(){
        this.cantidad=(double) 0;
        this.precio=(double) 0;

    }

}
