package com.datos.medividrios.dto.vidrio;


import lombok.Data;

@Data
public class VidrioRequest {
    private float ancho_cm;
    private float alto_cm;
    private Long artefactoId;
}

