package com.datos.medividrios.dto.cotizacion;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCotizacion {

    private String descripcion;
    private double area;
    private BigDecimal valorM2;
    private BigDecimal total;

}
