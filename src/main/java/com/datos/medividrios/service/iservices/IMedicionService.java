package com.datos.medividrios.service.iservices;

import com.datos.medividrios.dto.cubicacion.CubicacionTotalResponse;
import com.datos.medividrios.dto.medicion.MedicionCosto;
import com.datos.medividrios.dto.medicion.MedicionRequest;
import com.datos.medividrios.dto.medicion.MedicionResponse;

import java.util.List;

public interface IMedicionService {
    MedicionResponse crearMedicion(MedicionRequest request);
    MedicionResponse obtenerMedicionPorId(Long id);
    List<MedicionResponse> obtenerTodasLasMediciones();
    MedicionResponse actualizarMedicion(Long id, MedicionRequest request);
    void eliminarMedicion(Long id);
    List<MedicionResponse> obtenerMedicionesPorCliente(Long clienteId);
    CubicacionTotalResponse obtenerCubicacionTotalPorMedicion(Long medicionId);
    MedicionCosto calcularCostoMedicion(Long medicionId);
    List<MedicionResponse> obtenerMejoresVentasPorPeriodo(String period, String fechaInicio, String fechaFin);



}
