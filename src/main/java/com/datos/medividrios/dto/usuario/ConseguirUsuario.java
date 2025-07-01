package com.datos.medividrios.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public class ConseguirUsuario {
    private long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String hashClave;
}
