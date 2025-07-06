package com.datos.medividrios.repository;

import com.datos.medividrios.enuum.TipoCliente;
import com.datos.medividrios.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByTipoCliente(TipoCliente tipoCliente);
}
