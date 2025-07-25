package com.datos.medividrios.dto.artefacto;

import com.datos.medividrios.dto.vidrio.VidrioRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ArtefactoRequest {
    @NotBlank
    private String nombre;
    @NotNull
    private Long medicionId;
    @NotEmpty
    private List<VidrioRequest> vidrios;
}








