package com.datos.medividrios.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private String descripcion;
    private Boolean hayMasDeUnPiso;
    private LocalDate fechaRegistro;
    private LocalDate fechaEntrega;
    @OneToMany(mappedBy = "medicion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artefacto> artefactos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
