package com.datos.medividrios.controller;

import com.datos.medividrios.dto.cliente.ClienteRequest;
import com.datos.medividrios.dto.cliente.ClienteResponse;
import com.datos.medividrios.dto.cliente.ObtenerCliente;
import com.datos.medividrios.enuum.TipoCliente;
import com.datos.medividrios.service.iservices.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    @Autowired
    private final IClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> createCliente(@RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.createCliente(clienteRequest);
        return ResponseEntity.ok(clienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<ObtenerCliente>> clientes() {
        List<ObtenerCliente> clientes = clienteService.clientes();
        return ResponseEntity.ok(clientes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ObtenerCliente> obtenerClientePorId(@PathVariable Long id) {
        ObtenerCliente cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizarCliente(
            @PathVariable Long id,
            @RequestBody ClienteRequest dto) {
        ClienteResponse response = clienteService.actualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipo/{tipoCliente}")
    public ResponseEntity<List<ObtenerCliente>> obtenerClientesPorTipo(@PathVariable TipoCliente tipoCliente) {
        List<ObtenerCliente> clientes = clienteService.obtenerPorTipo(tipoCliente);
        return ResponseEntity.ok(clientes);
    }

}
