package com.datos.medividrios.enuum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCliente {
    CONSTRUCTORA("Constructora"),
    EMPRESA("Empresa"),
    CLIENTE_PARTICULAR("Cliente Particular");

    private final String value;

    TipoCliente(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoCliente fromValue(String value) {
        for (TipoCliente tipo : values()) {
            if (tipo.value.equalsIgnoreCase(value) || tipo.name().equalsIgnoreCase(value.replace(" ", "_"))) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoCliente inválido: " + value);
    }
}
