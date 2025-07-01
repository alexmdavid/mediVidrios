package com.datos.medividrios.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GuardarUsuario {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;
    @NotBlank(message = "El correo no puede estar vacio")
    private String correo;
    @NotBlank(message = "La clave no pude estar vacia")
    private String hashClave;
}
