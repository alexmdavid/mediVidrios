package com.datos.medividrios.enuum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoVenta {
    EN_PROCESO("En Proceso"),
    CANCELADO("Cancelado"),
    COMPLETADO("Completado");

    private final String value;

    EstadoVenta(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EstadoVenta fromValue(String value) {
        for (EstadoVenta estado : values()) {
            if (estado.value.equalsIgnoreCase(value) || estado.name().equalsIgnoreCase(value.replace(" ", "_"))) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoVenta inválido: " + value);
    }
}
