package com.example.demoservicesshopping.modelrest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cliente {
    private Long id;
    private String numberId;
    private String nombre;
    private String apellido;
    private String correo;
    private String status;
    private Region region;
}
