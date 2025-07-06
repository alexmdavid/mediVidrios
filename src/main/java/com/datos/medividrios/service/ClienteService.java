package com.datos.medividrios.service;

import com.datos.medividrios.dto.cliente.ClienteRequest;
import com.datos.medividrios.dto.cliente.ClienteResponse;
import com.datos.medividrios.dto.cliente.ObtenerCliente;
import com.datos.medividrios.dto.medicion.MedicionResponse;
import com.datos.medividrios.enuum.TipoCliente;
import com.datos.medividrios.model.Cliente;
import com.datos.medividrios.repository.ClienteRepository;
import com.datos.medividrios.service.iservices.IClienteService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService implements IClienteService {
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


//mala mia este metodo es obtener todos los clientes, clientes no especifica mucho
    @Override
    public List<ObtenerCliente> clientes() {
        List<ObtenerCliente> clientes = new ArrayList<>();
        for (Cliente cliente : clienteRepository.findAll()) {
            ObtenerCliente obtenerCliente = new ObtenerCliente();
            obtenerCliente.setId(cliente.getId());
            obtenerCliente.setNombre(cliente.getNombre());
            obtenerCliente.setApellido(cliente.getApellido());
            obtenerCliente.setEmail(cliente.getEmail());
            obtenerCliente.setTelefono(cliente.getTelefono());
            obtenerCliente.setDireccion(cliente.getDireccion());
            obtenerCliente.setTipoCliente(cliente.getTipoCliente());
            obtenerCliente.setGasto(cliente.getGasto());

            List<MedicionResponse> compras = cliente.getCompras().stream()
                    .map(medicion -> {
                        MedicionResponse response = new MedicionResponse();
                        response.setId(medicion.getId());
                        response.setDescripcion(medicion.getDescripcion());
                        response.setHayMasDeUnPiso(medicion.getHayMasDeUnPiso());
                        response.setFechaRegistro(medicion.getFechaRegistro());
                        response.setFechaEntrega(medicion.getFechaEntrega());
                        response.setClienteId(cliente.getId());
                        return response;
                    })
                    .toList();

            obtenerCliente.setCompras(compras);
            clientes.add(obtenerCliente);
        }
        return clientes;
    }




    //nada que especificar
    @Override
    public ObtenerCliente obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));

        ObtenerCliente obtenerCliente = new ObtenerCliente();
        obtenerCliente.setId(cliente.getId());
        obtenerCliente.setNombre(cliente.getNombre());
        obtenerCliente.setApellido(cliente.getApellido());
        obtenerCliente.setEmail(cliente.getEmail());
        obtenerCliente.setTelefono(cliente.getTelefono());
        obtenerCliente.setDireccion(cliente.getDireccion());
        obtenerCliente.setTipoCliente(cliente.getTipoCliente());
        obtenerCliente.setGasto(cliente.getGasto());

        List<MedicionResponse> compras = cliente.getCompras().stream()
                .map(medicion -> {
                    MedicionResponse response = new MedicionResponse();
                    response.setId(medicion.getId());
                    response.setDescripcion(medicion.getDescripcion());
                    response.setHayMasDeUnPiso(medicion.getHayMasDeUnPiso());
                    response.setFechaRegistro(medicion.getFechaRegistro());
                    response.setFechaEntrega(medicion.getFechaEntrega());
                    response.setClienteId(cliente.getId());
                    return response;
                })
                .toList();

        obtenerCliente.setCompras(compras);
        return obtenerCliente;
    }


    //menos es mas
    @Override
    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }


    //lo nuevo no siempre es mejor
    @Override
    public ClienteResponse actualizar(Long id, ClienteRequest dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTipoCliente(dto.getTipoCliente());
        cliente.setGasto(dto.getGasto());

        Cliente clienteActualizado = clienteRepository.save(cliente);

        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(clienteActualizado.getId());
        clienteResponse.setNombre(clienteActualizado.getNombre());
        clienteResponse.setApellido(clienteActualizado.getApellido());
        clienteResponse.setEmail(clienteActualizado.getEmail());
        clienteResponse.setTelefono(clienteActualizado.getTelefono());
        clienteResponse.setDireccion(clienteActualizado.getDireccion());
        clienteResponse.setTipoCliente(clienteActualizado.getTipoCliente());
        clienteResponse.setGasto(clienteActualizado.getGasto());

        return clienteResponse;
    }


    //en fin, los del frontend piden mucho y hacen poco
    @Transactional(readOnly = true)
    @Override
    public List<ObtenerCliente> obtenerPorTipo(TipoCliente tipoCliente) {
        List<ObtenerCliente> clientes = new ArrayList<>();
        for (Cliente cliente : clienteRepository.findByTipoCliente(tipoCliente)) {
            ObtenerCliente obtenerCliente = new ObtenerCliente();
            obtenerCliente.setId(cliente.getId());
            obtenerCliente.setNombre(cliente.getNombre());
            obtenerCliente.setApellido(cliente.getApellido());
            obtenerCliente.setEmail(cliente.getEmail());
            obtenerCliente.setTelefono(cliente.getTelefono());
            obtenerCliente.setDireccion(cliente.getDireccion());
            obtenerCliente.setTipoCliente(cliente.getTipoCliente());
            obtenerCliente.setGasto(cliente.getGasto());

            List<MedicionResponse> compras = cliente.getCompras().stream()
                    .map(medicion -> {
                        MedicionResponse response = new MedicionResponse();
                        response.setId(medicion.getId());
                        response.setDescripcion(medicion.getDescripcion());
                        response.setHayMasDeUnPiso(medicion.getHayMasDeUnPiso());
                        response.setFechaRegistro(medicion.getFechaRegistro());
                        response.setFechaEntrega(medicion.getFechaEntrega());
                        response.setClienteId(cliente.getId());
                        return response;
                    })
                    .toList();

            obtenerCliente.setCompras(compras);
            clientes.add(obtenerCliente);
        }
        return clientes;
    }

}
