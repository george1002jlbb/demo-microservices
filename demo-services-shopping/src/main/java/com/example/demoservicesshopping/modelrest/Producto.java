package com.example.demoservicesshopping.modelrest;

import lombok.Data;

import java.util.Date;

@Data
public class Producto {
    private long id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private String status;
    private Date createAt;
    private Categoria categoria;
}
