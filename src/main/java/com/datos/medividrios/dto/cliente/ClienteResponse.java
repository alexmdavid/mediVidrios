package com.datos.medividrios.dto.cliente;

import com.datos.medividrios.enuum.TipoCliente;
import lombok.Data;

@Data
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private TipoCliente tipoCliente;
    private String direccion;
    private Double gasto;
}
