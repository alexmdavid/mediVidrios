package com.datos.medividrios.dto.cliente;

import lombok.Data;

@Data
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String tipoCliente;
    private String direccion;
}
