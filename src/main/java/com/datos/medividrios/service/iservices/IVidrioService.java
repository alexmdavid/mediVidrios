package com.datos.medividrios.service.iservices;

import com.datos.medividrios.dto.vidrio.VidrioCubicado;
import com.datos.medividrios.dto.vidrio.VidrioRequest;
import com.datos.medividrios.dto.vidrio.VidrioResponse;

import java.util.List;

public interface IVidrioService {
    VidrioResponse crearVidrio(VidrioRequest dto);
    List<VidrioResponse> obtenerVidriosPorMedicion(Long medicionId);
    List<VidrioCubicado> obtenerVidriosCubicadosPorMedicion(Long medicionId);
}
