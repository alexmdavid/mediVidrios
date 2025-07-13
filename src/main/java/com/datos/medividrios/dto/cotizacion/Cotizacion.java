package com.datos.medividrios.dto.cotizacion;

import com.datos.medividrios.dto.cotizacion.ItemCotizacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cotizacion {

    private String cliente;
    private String tituloCliente; // Ej: "Señores", "Sr.", "Empresa X"
    private Date fecha;
    private String tiempoEntrega;
    private String validezOferta;
    private List<ItemCotizacion> items;

}
