package com.datos.medividrios.enuum;

public enum TipoCliente {
    CONSTRUCTORA,
    EMPRESA,
    CLIENTE_PARTICULAR;

    @Override
    public String toString() {
        return switch (this) {
            case CONSTRUCTORA -> "Constructora";
            case EMPRESA -> "Empresa";
            case CLIENTE_PARTICULAR -> "Cliente Particular";
        };
    }
}
