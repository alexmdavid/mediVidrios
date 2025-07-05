package com.datos.medividrios.service;

import com.datos.medividrios.dto.cliente.ClienteRequest;
import com.datos.medividrios.dto.cliente.ClienteResponse;
import com.datos.medividrios.dto.cliente.ObtenerCliente;
import com.datos.medividrios.model.Cliente;
import com.datos.medividrios.repository.ClienteRepository;
import com.datos.medividrios.service.iservices.ICienteService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService implements ICienteService {
    @Autowired
    ClienteRepository  clienteRepository;
    @Override
    public ClienteResponse createCliente(ClienteRequest dto) {
        Cliente cliente = Cliente.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .tipoCliente(dto.getTipoCliente())
                .gasto(dto.getGasto())
                .build();
        Cliente clienteGuardado = clienteRepository.save(cliente);
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setApellido(cliente.getApellido());
        clienteResponse.setEmail(cliente.getEmail());
        clienteResponse.setTelefono(cliente.getTelefono());
        clienteResponse.setDireccion(cliente.getDireccion());
        clienteResponse.setTipoCliente(cliente.getTipoCliente());
        clienteResponse.setGasto(cliente.getGasto());
        return clienteResponse;
    }

    @Override
    public List<ObtenerCliente> clientes() {
        List<ObtenerCliente> clientes = new ArrayList<>();
        for (Cliente cliente : clienteRepository.findAll()) {
            ObtenerCliente obtenerCliente = new ObtenerCliente();
            obtenerCliente.setId( cliente.getId() );
            obtenerCliente.setNombre(cliente.getNombre());
            obtenerCliente.setApellido(cliente.getApellido());
            obtenerCliente.setEmail(cliente.getEmail());
            obtenerCliente.setTelefono(cliente.getTelefono());
            obtenerCliente.setDireccion(cliente.getDireccion());
            obtenerCliente.setTipoCliente(cliente.getTipoCliente());
            obtenerCliente.setGasto(cliente.getGasto());
            clientes.add(obtenerCliente);
        }
        return clientes;
    }

}
