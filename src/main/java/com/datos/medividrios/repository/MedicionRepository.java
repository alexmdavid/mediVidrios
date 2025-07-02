package com.datos.medividrios.repository;

import com.datos.medividrios.model.Medicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicionRepository extends JpaRepository<Medicion,Long> {
}
