package com.datos.medividrios.enuum;

public enum EstadoVenta {
    EN_PROCESO,
    CANCELADO,
    COMPLETADO;

    @Override
    public String toString() {
        return switch (this) {
            case EN_PROCESO -> "En Proceso";
            case CANCELADO -> "Cancelado";
            case COMPLETADO -> "Completado";
        };
    }
}
