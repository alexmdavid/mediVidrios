package com.datos.medividrios.model;

import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Medicion {
    private long id;
    private String Cliente;
    private String descripcion;
    private Boolean hayMasDeUnPiso;
    private List<Artefacto> artefactos;
}
