package com.datos.medividrios.dto.cliente;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteGastoResponse {
    private Long clienteId;
    private String nombreCliente;
    private Double gastoTotalCliente;
    private Double gastoPromedioClientes;
}

