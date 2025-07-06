package com.datos.medividrios.dto.vidrio;


import com.datos.medividrios.enuum.TipoVidrio;
import lombok.Data;

@Data
public class VidrioRequest {
    private float ancho_cm;
    private float alto_cm;
    private Long artefactoId;
    private int espesor;
    private float precioM2;
    private String color;
    private TipoVidrio tipo;
}

