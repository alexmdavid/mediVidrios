package com.datos.medividrios.dto.vidrio;


import com.datos.medividrios.enuum.TipoVidrio;
import lombok.Data;

@Data
public class VidrioResponse {
    private Long id;
    private float ancho_cm;
    private float alto_cm;
    private Integer espesor;
    private Float precioM2;
    private String color;
    private TipoVidrio tipo;
    private Long artefactoId;

}
