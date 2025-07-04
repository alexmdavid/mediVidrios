package com.datos.medividrios.dto.cliente;

import com.datos.medividrios.model.Medicion;
import lombok.Data;


import java.util.List;

@Data
public class ObtenerCliente {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String tipoCliente;
    private String direccion;
    private List<Medicion> compras;
}
