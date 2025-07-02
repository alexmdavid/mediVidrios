package com.datos.medividrios.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Medicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Cliente;
    private String descripcion;
    private Boolean hayMasDeUnPiso;
    @OneToMany(mappedBy = "medicion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artefacto> artefactos;
}
