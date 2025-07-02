package com.datos.medividrios.model;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Artefacto {
    private long id;
    private String nombre;
    private List<Vidrio> vidrios;


}
