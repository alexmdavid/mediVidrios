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
    private Float ancho_cm;
    private Float alto_cm;
    @Column(name = "espesor")
    private Integer espesor;
    @Column(name = "precio_m2")
    private Float precioM2;
    private String color;
    private TipoVidrio tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artefacto_id")
    private Artefacto artefacto;
}
