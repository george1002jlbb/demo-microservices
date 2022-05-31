package com.example.demoservicesclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "el numero de documento no puede ser vacio")
    @Size(min = 8, max = 8, message = "el tamaño del numero debe ser 8")
    @Column(name = "idnumber", unique = true, length = 8, nullable = false)
    private String numberId;
    @NotEmpty
    @Column(nullable = false)
    private String nombre;
    @NotEmpty
    @Column(nullable = false)
    private String apellido;
    @Email(message = "no es una direccion de correo valida")
    private String correo;
    private String status;
    @Valid
    @NotNull(message = "Region no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idregion")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Region region;

}
