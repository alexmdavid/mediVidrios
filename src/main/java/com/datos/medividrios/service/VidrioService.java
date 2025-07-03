package com.datos.medividrios.service;

import com.datos.medividrios.dto.vidrio.VidrioCubicado;
import com.datos.medividrios.dto.vidrio.VidrioRequest;
import com.datos.medividrios.dto.vidrio.VidrioResponse;
import com.datos.medividrios.model.Artefacto;
import com.datos.medividrios.model.Medicion;
import com.datos.medividrios.model.Vidrio;
import com.datos.medividrios.repository.ArtefactoRepository;
import com.datos.medividrios.repository.MedicionRepository;
import com.datos.medividrios.repository.VidrioRepository;
import com.datos.medividrios.service.iservices.IVidrioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VidrioService implements IVidrioService {

    @Autowired
    private VidrioRepository  vidrioRepository;
    @Autowired
    private ArtefactoRepository artefactoRepository;
    @Autowired
    private MedicionRepository medicionRepository;





    @Override
    public VidrioResponse crearVidrio(VidrioRequest dto) {
        Artefacto artefacto = artefactoRepository.findById(dto.getArtefactoId()).orElseThrow(() -> new EntityNotFoundException("Artefacto no encontrado con ID: " + dto.getArtefactoId()));
        Vidrio vidrio = Vidrio.builder()
                .ancho_cm(dto.getAncho_cm())
                .alto_cm(dto.getAlto_cm())
                .artefacto(artefacto)
                .build();
        Vidrio vidrioGuardado = vidrioRepository.save(vidrio);
        VidrioResponse response = new VidrioResponse();
        response.setId(vidrioGuardado.getId());
        response.setAncho_cm(vidrioGuardado.getAncho_cm());
        response.setAlto_cm(vidrioGuardado.getAlto_cm());
        response.setArtefactoId(artefacto.getId());

        return response;
    }










    //este es el hp metodo para traert todos los vidrios de una medicion
    @Override
    public List<VidrioResponse> obtenerVidriosPorMedicion(Long medicionId) {
        Medicion medicion = medicionRepository.findById(medicionId)
                .orElseThrow(() -> new EntityNotFoundException("Medición no encontrada con ID: " + medicionId));

        List<VidrioResponse> vidriosDTO = new ArrayList<>();

        for (Artefacto artefacto : medicion.getArtefactos()) {
            for (Vidrio vidrio : artefacto.getVidrios()) {
                VidrioResponse dto = new VidrioResponse();
                dto.setId(vidrio.getId());
                dto.setAncho_cm(vidrio.getAncho_cm());
                dto.setAlto_cm(vidrio.getAlto_cm());
                dto.setArtefactoId(artefacto.getId());
                vidriosDTO.add(dto);
            }
        }

        return vidriosDTO;
    }







    //mismo metodo, pero ahora esta caga los trae cubicados
    @Override
    public List<VidrioCubicado> obtenerVidriosCubicadosPorMedicion(Long medicionId) {
        Medicion medicion = medicionRepository.findById(medicionId)
                .orElseThrow(() -> new EntityNotFoundException("Medición no encontrada con ID: " + medicionId));

        List<VidrioCubicado> vidriosCubicados = new ArrayList<>();

        for (Artefacto artefacto : medicion.getArtefactos()) {
            for (Vidrio vidrio : artefacto.getVidrios()) {
                double[] medidasCubicadas = cubicar(vidrio.getAncho_cm(), vidrio.getAlto_cm());
                double anchoCubicado = medidasCubicadas[0];
                double altoCubicado = medidasCubicadas[1];
                double area = anchoCubicado * altoCubicado;

                VidrioCubicado dto = new VidrioCubicado();
                dto.setId(vidrio.getId());
                dto.setAncho(anchoCubicado);
                dto.setAlto(altoCubicado);
                dto.setArea(area);
                dto.setArtefactoId(artefacto.getId());

                vidriosCubicados.add(dto);
            }
        }

        return vidriosCubicados;
    }


//este puto lo uso intermente para cubicar cada vidrio jijijij
    private double[] cubicar(double anchoCm, double altoCm) {
        double anchoM = anchoCm / 100.0;
        double altoM = altoCm / 100.0;
        double anchoCubicado = Math.ceil(anchoM * 10) / 10.0;
        double altoCubicado = Math.ceil(altoM * 10) / 10.0;
        return new double[]{anchoCubicado, altoCubicado};
    }



}
