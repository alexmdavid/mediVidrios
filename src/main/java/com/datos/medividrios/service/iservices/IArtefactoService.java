package com.datos.medividrios.service.iservices;

import com.datos.medividrios.dto.artefacto.ArtefactoRequest;
import com.datos.medividrios.dto.artefacto.ArtefactoResponse;
import com.datos.medividrios.dto.cubicacion.CubicacionTotalResponse;

import java.util.List;

public interface IArtefactoService {
    ArtefactoResponse crearArtefacto(ArtefactoRequest request);
    ArtefactoResponse actualizarArtefacto(Long id, ArtefactoRequest request);
    void eliminarArtefacto(Long id);
    ArtefactoResponse obtenerArtefactoPorId(Long id);
    List<ArtefactoResponse> obtenerArtefactosPorMedicion(Long medicionId);
    CubicacionTotalResponse obtenerCubicacionTotalPorArtefacto(Long artefactoId);


}
