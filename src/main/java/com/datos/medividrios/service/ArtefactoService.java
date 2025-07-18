package com.datos.medividrios.service;

import com.datos.medividrios.dto.artefacto.ArtefactoRequest;
import com.datos.medividrios.dto.artefacto.ArtefactoResponse;
import com.datos.medividrios.dto.cubicacion.CubicacionTotalResponse;
import com.datos.medividrios.dto.vidrio.VidrioResponse;
import com.datos.medividrios.model.Artefacto;
import com.datos.medividrios.model.Medicion;
import com.datos.medividrios.model.Vidrio;
import com.datos.medividrios.repository.ArtefactoRepository;
import com.datos.medividrios.repository.MedicionRepository;
import com.datos.medividrios.service.iservices.IArtefactoService;
import com.datos.medividrios.util.Util;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtefactoService implements IArtefactoService {
    @Autowired
    private ArtefactoRepository artefactoRepository;
    @Autowired
    private MedicionRepository medicionRepository;

    @Transactional
    public ArtefactoResponse crearArtefacto(ArtefactoRequest request) {
        Medicion medicion = medicionRepository.findById(request.getMedicionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicion no encontrada"));
        Artefacto artefacto = new Artefacto();
        artefacto.setNombre(request.getNombre());
        artefacto.setMedicion(medicion);
        List<Vidrio> vidrios = request.getVidrios().stream()
                .map(vr -> {
                    Vidrio vidrio = new Vidrio();
                    vidrio.setAncho_cm(vr.getAncho_cm());
                    vidrio.setAlto_cm(vr.getAlto_cm());
                    vidrio.setEspesor(vr.getEspesor());
                    vidrio.setPrecioM2(vr.getPrecioM2());
                    vidrio.setTipo(vr.getTipo());
                    vidrio.setColor(vr.getColor());
                    vidrio.setArtefacto(artefacto);
                    return vidrio;
                })
                .collect(Collectors.toList());
        artefacto.setVidrios(vidrios);
        Artefacto artefactoGuardado = artefactoRepository.save(artefacto);
        ArtefactoResponse response = new ArtefactoResponse();
        response.setId(artefactoGuardado.getId());
        response.setNombre(artefactoGuardado.getNombre());
        response.setMedicionId(medicion.getId());
        List<VidrioResponse> vidriosResponse = artefactoGuardado.getVidrios().stream()
                .map(v -> {
                    VidrioResponse vr = new VidrioResponse();
                    vr.setId(v.getId());
                    vr.setAncho_cm(v.getAncho_cm());
                    vr.setAlto_cm(v.getAlto_cm());
                    vr.setEspesor(v.getEspesor());
                    vr.setPrecioM2(v.getPrecioM2());
                    vr.setTipo(v.getTipo());
                    vr.setColor(v.getColor());
                    return vr;
                })
                .collect(Collectors.toList());
        response.setVidrios(vidriosResponse);
        return response;
    }

    @Transactional
    public ArtefactoResponse actualizarArtefacto(Long id, ArtefactoRequest request) {
        Artefacto artefacto = artefactoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artefacto no encontrado"));
        artefacto.setNombre(request.getNombre());
        Medicion nuevaMedicion = medicionRepository.findById(request.getMedicionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medición no encontrada"));
        artefacto.setMedicion(nuevaMedicion);
        artefacto.getVidrios().clear();
        List<Vidrio> nuevosVidrios = request.getVidrios().stream()
                .map(vr -> {
                    Vidrio vidrio = new Vidrio();
                    vidrio.setAncho_cm(vr.getAncho_cm());
                    vidrio.setAlto_cm(vr.getAlto_cm());
                    vidrio.setArtefacto(artefacto);
                    return vidrio;
                })
                .collect(Collectors.toList());
        artefacto.getVidrios().addAll(nuevosVidrios);
        Artefacto artefactoActualizado = artefactoRepository.save(artefacto);
        ArtefactoResponse response = new ArtefactoResponse();
        response.setId(artefactoActualizado.getId());
        response.setNombre(artefactoActualizado.getNombre());
        response.setMedicionId(nuevaMedicion.getId());
        List<VidrioResponse> vidriosResponse = artefactoActualizado.getVidrios().stream()
                .map(v -> {
                    VidrioResponse vr = new VidrioResponse();
                    vr.setId(v.getId());
                    vr.setAncho_cm(v.getAncho_cm());
                    vr.setAlto_cm(v.getAlto_cm());
                    return vr;
                })
                .collect(Collectors.toList());
        response.setVidrios(vidriosResponse);
        return response;
    }

    @Transactional
    public void eliminarArtefacto(Long id) {
        Artefacto artefacto = artefactoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artefacto no encontrado"));
        artefactoRepository.delete(artefacto);
    }

    @Transactional(readOnly = true)
    public List<ArtefactoResponse> obtenerArtefactosPorMedicion(Long medicionId) {
        Medicion medicion = medicionRepository.findById(medicionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medición no encontrada"));

        return medicion.getArtefactos().stream()
                .map(artefacto -> {
                    ArtefactoResponse response = new ArtefactoResponse();
                    response.setId(artefacto.getId());
                    response.setNombre(artefacto.getNombre());
                    response.setMedicionId(medicion.getId());

                    List<VidrioResponse> vidriosResponse = artefacto.getVidrios().stream()
                            .map(v -> {
                                VidrioResponse vr = new VidrioResponse();
                                vr.setId(v.getId());
                                vr.setAncho_cm(v.getAncho_cm());
                                vr.setAlto_cm(v.getAlto_cm());
                                vr.setColor(v.getColor());
                                vr.setTipo(v.getTipo());
                                vr.setEspesor(v.getEspesor());
                                vr.setPrecioM2(v.getPrecioM2());
                                return vr;
                            })
                            .collect(Collectors.toList());
                    response.setVidrios(vidriosResponse);
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArtefactoResponse obtenerArtefactoPorId(Long id) {
        Artefacto artefacto = artefactoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artefacto no encontrado"));
        ArtefactoResponse response = new ArtefactoResponse();
        response.setId(artefacto.getId());
        response.setNombre(artefacto.getNombre());
        response.setMedicionId(artefacto.getMedicion().getId());
        List<VidrioResponse> vidriosResponse = artefacto.getVidrios().stream()
                .map(v -> {
                    VidrioResponse vr = new VidrioResponse();
                    vr.setId(v.getId());
                    vr.setAncho_cm(v.getAncho_cm());
                    vr.setAlto_cm(v.getAlto_cm());
                    vr.setColor(v.getColor());
                    vr.setTipo(v.getTipo());
                    vr.setEspesor(v.getEspesor());
                    vr.setPrecioM2(v.getPrecioM2());
                    return vr;
                })
                .collect(Collectors.toList());
        response.setVidrios(vidriosResponse);
        return response;
    }

    @Override
    public CubicacionTotalResponse obtenerCubicacionTotalPorArtefacto(Long artefactoId) {
        Artefacto artefacto = artefactoRepository.findById(artefactoId)
                .orElseThrow(() -> new EntityNotFoundException("Artefacto no encontrado con ID: " + artefactoId));
        double total = artefacto.getVidrios().stream()
                .mapToDouble(v -> {
                    double[] medidas = Util.cubicar(v.getAncho_cm(), v.getAlto_cm());
                    return medidas[0] * medidas[1];
                })
                .sum();
        return new CubicacionTotalResponse(artefactoId, total);
    }
}
