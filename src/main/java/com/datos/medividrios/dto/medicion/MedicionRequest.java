package com.datos.medividrios.dto.medicion;


import com.datos.medividrios.enuum.EstadoVenta;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicionRequest {
    private String descripcion;
    private Boolean hayMasDeUnPiso;
    private LocalDate fechaRegistro;
    private LocalDate fechaEntrega;
    private EstadoVenta estadoVenta;
    private Integer cantidadPisos;
    private Long clienteId;
}
