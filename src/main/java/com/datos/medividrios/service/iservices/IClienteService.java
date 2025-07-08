package com.datos.medividrios.service.iservices;

import com.datos.medividrios.dto.cliente.ClienteGastoResponse;
import com.datos.medividrios.dto.cliente.ClienteRequest;
import com.datos.medividrios.dto.cliente.ClienteResponse;
import com.datos.medividrios.dto.cliente.ObtenerCliente;
import com.datos.medividrios.enuum.TipoCliente;

import java.util.List;

public interface IClienteService {
    ClienteResponse createCliente(ClienteRequest dto);
    List<ObtenerCliente> clientes();
    ObtenerCliente obtenerPorId(Long id);
    void eliminar(Long id);
    ClienteResponse actualizar(Long id, ClienteRequest dto);
    List<ObtenerCliente> obtenerPorTipo(TipoCliente tipoCliente);
    ClienteGastoResponse obtenerGastoTotalYPromedio(Long clienteId);

}
