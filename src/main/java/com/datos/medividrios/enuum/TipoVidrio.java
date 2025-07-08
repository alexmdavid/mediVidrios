package com.datos.medividrios.enuum;

public enum TipoVidrio {
    LAMINADO,
    TEMPLADO,
    FLOTADO,
    REFLECTIVO,
    MARTILLADO;

    @Override
    public String toString() {
        return switch (this) {
            case LAMINADO -> "Laminado";
            case TEMPLADO -> "Templado";
            case FLOTADO -> "Flotado";
            case REFLECTIVO -> "Reflectivo";
            case MARTILLADO -> "Martillado";
        };
    }
}
