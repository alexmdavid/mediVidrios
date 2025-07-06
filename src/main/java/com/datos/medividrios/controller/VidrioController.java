package com.datos.medividrios.controller;

import com.datos.medividrios.dto.vidrio.VidrioCubicado;
import com.datos.medividrios.dto.vidrio.VidrioRequest;
import com.datos.medividrios.dto.vidrio.VidrioResponse;
import com.datos.medividrios.service.VidrioService;
import com.datos.medividrios.service.iservices.IVidrioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vidrios")
@RequiredArgsConstructor
public class VidrioController {
    @Autowired
    private IVidrioService vidrioService;

    @PostMapping
    public ResponseEntity<VidrioResponse> crearVidrio(@RequestBody VidrioRequest dto) {
        VidrioResponse response = vidrioService.crearVidrio(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-medicion/{medicionId}")
    public ResponseEntity<List<VidrioResponse>> obtenerVidriosPorMedicion(@PathVariable Long medicionId) {
        List<VidrioResponse> vidrios = vidrioService.obtenerVidriosPorMedicion(medicionId);
        return ResponseEntity.ok(vidrios);
    }


    @GetMapping("/cubicados/por-medicion/{medicionId}")
    public ResponseEntity<List<VidrioCubicado>> obtenerVidriosCubicadosPorMedicion(@PathVariable Long medicionId) {
        List<VidrioCubicado> cubicados = vidrioService.obtenerVidriosCubicadosPorMedicion(medicionId);
        return ResponseEntity.ok(cubicados);
    }


    /**
     * Obtener todos los vidrios de un artefacto por ID de artefacto
     */
    @GetMapping("/por-artefacto/{artefactoId}")
    public ResponseEntity<List<VidrioResponse>> obtenerVidriosPorArtefacto(@PathVariable Long artefactoId) {
        List<VidrioResponse> vidrios = vidrioService.obtenerVidriosPorArtefacto(artefactoId);
        return ResponseEntity.ok(vidrios);
    }

    /**
     * Obtener todos los vidrios de un artefacto en formato cubicado
     */
    @GetMapping("/cubicados/por-artefacto/{artefactoId}")
    public ResponseEntity<List<VidrioCubicado>> obtenerVidriosCubicadosPorArtefacto(@PathVariable Long artefactoId) {
        List<VidrioCubicado> vidriosCubicados = vidrioService.obtenerVidriosCubicadosPorArtefacto(artefactoId);
        return ResponseEntity.ok(vidriosCubicados);
    }

    /**
     * Actualizar un vidrio existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<VidrioResponse> actualizarVidrio(@PathVariable Long id, @RequestBody VidrioRequest request) {
        VidrioResponse actualizado = vidrioService.actualizarVidrio(id, request);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Eliminar un vidrio por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVidrio(@PathVariable Long id) {
        vidrioService.eliminarVidrio(id);
        return ResponseEntity.noContent().build();
    }

}
