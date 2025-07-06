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
import com.datos.medividrios.util.Util;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<VidrioResponse> obtenerVidriosPorArtefacto(Long artefactoId) {
        Artefacto artefacto = artefactoRepository.findById(artefactoId)
                .orElseThrow(() -> new EntityNotFoundException("Artefacto no encontrado con ID: " + artefactoId));

        return artefacto.getVidrios().stream().map(v -> {
            VidrioResponse dto = new VidrioResponse();
            dto.setId(v.getId());
            dto.setAncho_cm(v.getAncho_cm());
            dto.setAlto_cm(v.getAlto_cm());
            dto.setArtefactoId(artefactoId);
            return dto;
        }).collect(Collectors.toList());
    }

    //obteenrlos por artefacto, pero cubicados

    @Override
    public List<VidrioCubicado> obtenerVidriosCubicadosPorArtefacto(Long artefactoId) {
        Artefacto artefacto = artefactoRepository.findById(artefactoId)
                .orElseThrow(() -> new EntityNotFoundException("Artefacto no encontrado con ID: " + artefactoId));

        return artefacto.getVidrios().stream().map(vidrio -> {
            double[] medidasCubicadas = Util.cubicar(vidrio.getAncho_cm(), vidrio.getAlto_cm());
            double anchoCubicado = medidasCubicadas[0];
            double altoCubicado = medidasCubicadas[1];
            double area = anchoCubicado * altoCubicado;

            VidrioCubicado dto = new VidrioCubicado();
            dto.setId(vidrio.getId());
            dto.setAncho(anchoCubicado);
            dto.setAlto(altoCubicado);
            dto.setArea(area);
            dto.setArtefactoId(artefactoId);
            return dto;
        }).collect(Collectors.toList());
    }







    //actulizar los cortantes vidrios ijijij

    @Override
    public VidrioResponse actualizarVidrio(Long id, VidrioRequest dto) {
        Vidrio vidrio = vidrioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vidrio no encontrado con ID: " + id));

        if (!vidrio.getArtefacto().getId().equals(dto.getArtefactoId())) {
            Artefacto artefacto = artefactoRepository.findById(dto.getArtefactoId())
                    .orElseThrow(() -> new EntityNotFoundException("Artefacto no encontrado con ID: " + dto.getArtefactoId()));
            vidrio.setArtefacto(artefacto);
        }

        vidrio.setAncho_cm(dto.getAncho_cm());
        vidrio.setAlto_cm(dto.getAlto_cm());

        Vidrio vidrioActualizado = vidrioRepository.save(vidrio);

        VidrioResponse response = new VidrioResponse();
        response.setId(vidrioActualizado.getId());
        response.setAncho_cm(vidrioActualizado.getAncho_cm());
        response.setAlto_cm(vidrioActualizado.getAlto_cm());
        response.setArtefactoId(vidrioActualizado.getArtefacto().getId());

        return response;
    }




    //borrarlo como mi ex

    @Override
    public void eliminarVidrio(Long id) {
        Vidrio vidrio = vidrioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vidrio no encontrado con ID: " + id));

        vidrioRepository.delete(vidrio);
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
                double[] medidasCubicadas = Util.cubicar(vidrio.getAncho_cm(), vidrio.getAlto_cm());
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






}
