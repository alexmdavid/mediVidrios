package com.datos.medividrios.service.iservices;

import com.datos.medividrios.dto.cliente.ClienteRequest;
import com.datos.medividrios.dto.cliente.ClienteResponse;
import com.datos.medividrios.dto.cliente.ObtenerCliente;

import java.util.List;

public interface ICienteService {
    ClienteResponse createCliente(ClienteRequest dto);
    List<ObtenerCliente> clientes();

}
