package com.datos.medividrios.repository;

import com.datos.medividrios.model.Medicion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicionRepository extends JpaRepository<Medicion,Long> {
    List<Medicion> findByClienteId(Long clienteId);
}
