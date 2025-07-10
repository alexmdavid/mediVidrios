package com.datos.medividrios.service.iservices;

import com.datos.medividrios.dto.vidrio.VidrioCubicado;
import com.datos.medividrios.dto.vidrio.VidrioRequest;
import com.datos.medividrios.dto.vidrio.VidrioResponse;

import java.util.List;

public interface IVidrioService {
    VidrioResponse crearVidrio(VidrioRequest dto);
    List<VidrioResponse> obtenerVidriosPorMedicion(Long medicionId);
    List<VidrioCubicado> obtenerVidriosCubicadosPorMedicion(Long medicionId);
    List<VidrioResponse> obtenerVidriosPorArtefacto(Long artefactoId);
    VidrioResponse actualizarVidrio(Long id, VidrioRequest dto);
    void eliminarVidrio(Long id);
    List<VidrioCubicado> obtenerVidriosCubicadosPorArtefacto(Long artefactoId);



}
