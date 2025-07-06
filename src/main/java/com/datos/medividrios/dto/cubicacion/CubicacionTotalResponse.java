package com.datos.medividrios.dto.cubicacion;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CubicacionTotalResponse {
    private Long referenciaId; // artefactoId o medicionId
    private double totalMetrosCuadrados;
}
