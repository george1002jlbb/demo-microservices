package com.example.demoservicesshopping.modelrest;

import lombok.Data;

@Data
public class Cliente {
    private Long id;
    private String numberId;
    private String nombre;
    private String apellido;
    private String correo;
    private String status;
    private Region region;
}
