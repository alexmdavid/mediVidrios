package com.datos.medividrios.dto.cliente;

import com.datos.medividrios.enuum.TipoCliente;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClienteRequest {
    @NotBlank(message = "debe ingresar un nombre o identificador")
    private String nombre;
    @NotBlank(message = "debe ingresar un apellido")
    private String apellido;
    private String email;
    private String telefono;
    private TipoCliente tipoCliente;
    private String direccion;
    private  Double gasto;

}
