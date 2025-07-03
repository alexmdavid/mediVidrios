package com.datos.medividrios.dto.vidrio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class VidrioCubicado {
    private Long id;
    private double ancho;
    private double alto;
    private double area;
    private Long artefactoId;

}
