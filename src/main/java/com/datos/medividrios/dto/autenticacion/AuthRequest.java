package com.datos.medividrios.dto.autenticacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String correo;
    private String clave;

}