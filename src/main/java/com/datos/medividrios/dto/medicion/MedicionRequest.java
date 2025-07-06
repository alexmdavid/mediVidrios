package com.datos.medividrios.dto.medicion;


import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicionRequest {
    private String descripcion;
    private Boolean hayMasDeUnPiso;
    private LocalDate fechaRegistro;
    private LocalDate fechaEntrega;
    private Long clienteId;
}
