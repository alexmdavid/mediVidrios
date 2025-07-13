package com.datos.medividrios.config;

import com.datos.medividrios.enuum.TipoCliente;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TipoClienteConverter implements Converter<String, TipoCliente> {
    @Override
    public TipoCliente convert(String source) {
        return TipoCliente.fromValue(source);
    }
}
