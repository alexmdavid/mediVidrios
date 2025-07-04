package com.datos.medividrios.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GuardarCliente {
    @NotBlank(message = "debe ingresar un nombre o identificador")
    private String nombre;
    @NotBlank(message = "debe ingresar un apellido")
    private String apellido;
    private String email;
    private String telefono;
    private String tipoCliente;
    private String direccion;

}
