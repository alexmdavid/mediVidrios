package com.datos.medividrios.service;

import com.datos.medividrios.dto.artefacto.ArtefactoRequest;
import com.datos.medividrios.dto.artefacto.ArtefactoResponse;
import com.datos.medividrios.dto.vidrio.VidrioResponse;
import com.datos.medividrios.model.Artefacto;
import com.datos.medividrios.model.Medicion;
import com.datos.medividrios.model.Vidrio;
import com.datos.medividrios.repository.ArtefactoRepository;
import com.datos.medividrios.repository.MedicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtefactoService {
    @Autowired
    private ArtefactoRepository artefactoRepository;
    @Autowired
    private MedicionRepository medicionRepository;



    //GUARDAR ARTEFACTO, UN MIERDERO LLEVO, PERO AJA SIRVE

    @Transactional
    public ArtefactoResponse crearArtefacto(ArtefactoRequest request) {
        // 1. obtener la medicion obligatoria
        Medicion medicion = medicionRepository.findById(request.getMedicionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicion no encontrada"));

        // 2. crear el hermoso artefacto
        Artefacto artefacto = new Artefacto();
        artefacto.setNombre(request.getNombre());
        artefacto.setMedicion(medicion);

        // 3. crear vidrios y asignarlos alputo artefacto
        List<Vidrio> vidrios = request.getVidrios().stream()
                .map(vr -> {
                    Vidrio vidrio = new Vidrio();
                    vidrio.setAncho_cm(vr.getAncho_cm());
                    vidrio.setAlto_cm(vr.getAlto_cm());
                    vidrio.setArtefacto(artefacto);
                    return vidrio;
                })
                .collect(Collectors.toList());

        artefacto.setVidrios(vidrios);

        // 4. guardar
        Artefacto artefactoGuardado = artefactoRepository.save(artefacto);

        // 5. mapear a artefactoResponse
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
                    return vr;
                })
                .collect(Collectors.toList());

        response.setVidrios(vidriosResponse);
        //y finishhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh jijijijijijij
        return response;
    }

}
