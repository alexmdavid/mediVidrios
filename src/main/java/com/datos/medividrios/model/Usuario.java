package com.datos.medividrios.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;
    @Column(unique = true)
    @NotBlank(message = "El correo no puede estar vacio")
    private String correo;
    private String rol = "ROLE_USER";
    @NotBlank(message = "La clave no pude estar vacia")
    private String hashClave;


}
