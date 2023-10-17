package com.neoris.cliente.infraestructure.driveradapter;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "CLIENTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class ClienteEntity extends PersonaEntity {

    
    @Column(name = "CONTRASENA")
    private String contrasena;
    
    private String estado;
    

    
}
