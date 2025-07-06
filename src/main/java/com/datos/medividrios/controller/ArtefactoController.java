package com.datos.medividrios.controller;

import com.datos.medividrios.dto.artefacto.ArtefactoRequest;
import com.datos.medividrios.dto.artefacto.ArtefactoResponse;
import com.datos.medividrios.dto.cubicacion.CubicacionTotalResponse;
import com.datos.medividrios.service.ArtefactoService;
import com.datos.medividrios.service.iservices.IArtefactoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/artefacto")
@RequiredArgsConstructor
public class ArtefactoController {
    private final IArtefactoService artefactoService;


    @PostMapping("/api/artefactos")
    public ResponseEntity<ArtefactoResponse> crearArtefacto(@RequestBody @Valid ArtefactoRequest request) {
        ArtefactoResponse response = artefactoService.crearArtefacto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtefactoResponse> actualizarArtefacto(
            @PathVariable Long id,
            @RequestBody ArtefactoRequest request) {
        ArtefactoResponse response = artefactoService.actualizarArtefacto(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Eliminar artefacto por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtefacto(@PathVariable Long id) {
        artefactoService.eliminarArtefacto(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtener artefacto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArtefactoResponse> obtenerArtefactoPorId(@PathVariable Long id) {
        ArtefactoResponse response = artefactoService.obtenerArtefactoPorId(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener todos los artefactos de una medición
     */
    @GetMapping("/medicion/{medicionId}")
    public ResponseEntity<List<ArtefactoResponse>> obtenerArtefactosPorMedicion(@PathVariable Long medicionId) {
        List<ArtefactoResponse> response = artefactoService.obtenerArtefactosPorMedicion(medicionId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}/cubicacion-total")
    public ResponseEntity<CubicacionTotalResponse> obtenerCubicacionTotalPorArtefacto(@PathVariable Long id) {
        return ResponseEntity.ok(artefactoService.obtenerCubicacionTotalPorArtefacto(id));
    }

}
