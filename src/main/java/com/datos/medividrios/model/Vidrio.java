package com.datos.medividrios.model;

import com.datos.medividrios.enuum.TipoVidrio;
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
    private int espesor;
    @Column(name = "precio_m2")
    private float precioM2;
    private String color;
    private TipoVidrio tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artefacto_id")
    private Artefacto artefacto;
}
