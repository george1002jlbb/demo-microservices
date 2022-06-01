package com.example.demoservicesshopping.model;

import com.example.demoservicesshopping.modelrest.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_factura")
@Data
@AllArgsConstructor
@Builder
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nroFactura;
    private Long idcliente;
    private String descripcion;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "iditem")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private List<FacturaItem> items;
    private String status;
    @Transient
    private Cliente cliente;

    public Factura() {
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){ // registrar createAt automaticmaente antes de registrar a la base de datos
        this.createAt = new Date();
    }
}
