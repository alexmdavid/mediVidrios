package com.datos.medividrios.dto.medicion;


import com.datos.medividrios.enuum.EstadoVenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicionResponse {
    private Long id;
    private String descripcion;
    private Boolean hayMasDeUnPiso;
    private LocalDate fechaRegistro;
    private LocalDate fechaEntrega;
    private EstadoVenta estadoVenta;
    private Integer cantidadPisos;
    private Double costoTotal;
    private Long clienteId;
}

