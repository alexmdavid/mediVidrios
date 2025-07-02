package com.datos.medividrios.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Vidrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float ancho_cm;
    private float alto_cm;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artefacto_id")
    private Artefacto artefacto;
}
