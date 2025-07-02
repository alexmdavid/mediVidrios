package com.datos.medividrios.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Vidrio {
    private long id;
    private float ancho_cm;
    private float alto_cm;
}
