package com.datos.medividrios.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Artefacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicion_id")
    private Medicion medicion;
    @OneToMany(mappedBy = "artefacto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vidrio> vidrios;


}
