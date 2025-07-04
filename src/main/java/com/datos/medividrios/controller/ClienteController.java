package com.datos.medividrios.controller;

import com.datos.medividrios.dto.cliente.ClienteRequest;
import com.datos.medividrios.dto.cliente.ClienteResponse;
import com.datos.medividrios.dto.cliente.ObtenerCliente;
import com.datos.medividrios.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;

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

}
