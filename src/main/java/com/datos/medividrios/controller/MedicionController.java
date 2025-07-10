package com.datos.medividrios.controller;

import com.datos.medividrios.dto.cubicacion.CubicacionTotalResponse;
import com.datos.medividrios.dto.medicion.MedicionRequest;
import com.datos.medividrios.dto.medicion.MedicionResponse;
import com.datos.medividrios.service.iservices.IMedicionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mediciones")
@RequiredArgsConstructor
public class MedicionController {

    private final IMedicionService medicionService;

    @PostMapping
    public ResponseEntity<MedicionResponse> crearMedicion(@Valid @RequestBody MedicionRequest request) {
        MedicionResponse response = medicionService.crearMedicion(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicionResponse>> obtenerTodasLasMediciones() {
        List<MedicionResponse> mediciones = medicionService.obtenerTodasLasMediciones();
        return ResponseEntity.ok(mediciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicionResponse> obtenerMedicionPorId(@PathVariable Long id) {
        MedicionResponse response = medicionService.obtenerMedicionPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicionResponse> actualizarMedicion(@PathVariable Long id, @Valid @RequestBody MedicionRequest request) {
        MedicionResponse response = medicionService.actualizarMedicion(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedicion(@PathVariable Long id) {
        medicionService.eliminarMedicion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<MedicionResponse>> obtenerMedicionesPorCliente(@PathVariable Long clienteId) {
        List<MedicionResponse> mediciones = medicionService.obtenerMedicionesPorCliente(clienteId);
        return ResponseEntity.ok(mediciones);
    }

    @GetMapping("/{id}/cubicacion-total")
    public ResponseEntity<CubicacionTotalResponse> obtenerCubicacionTotalPorMedicion(@PathVariable Long id) {
        return ResponseEntity.ok(medicionService.obtenerCubicacionTotalPorMedicion(id));
    }

    @GetMapping("/mediciones/{medicionId}/costo")
    public ResponseEntity<Double> calcularCostoMedicion(@PathVariable Long medicionId) {
        double costo = medicionService.calcularCostoMedicion(medicionId);
        return ResponseEntity.ok(costo);
    }


}
