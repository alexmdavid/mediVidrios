package com.datos.medividrios.dto.artefacto;

import com.datos.medividrios.dto.vidrio.VidrioResponse;
import lombok.Data;

import java.util.List;

@Data
public class ArtefactoResponse {
    private Long id;
    private String nombre;
    private Long medicionId;
    private List<VidrioResponse> vidrios;
}
