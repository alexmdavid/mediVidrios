package com.datos.medividrios.enuum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoVidrio {
    LAMINADO("Laminado"),
    TEMPLADO("Templado"),
    FLOTADO("Flotado"),
    REFLECTIVO("Reflectivo"),
    MARTILLADO("Martillado");

    private final String value;

    TipoVidrio(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoVidrio fromValue(String value) {
        for (TipoVidrio tipo : values()) {
            if (tipo.value.equalsIgnoreCase(value) || tipo.name().equalsIgnoreCase(value.replace(" ", "_"))) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoVidrio inválido: " + value);
    }
}
