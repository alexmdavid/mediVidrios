package com.datos.medividrios.controller;

import com.datos.medividrios.dto.vidrio.VidrioCubicado;
import com.datos.medividrios.dto.vidrio.VidrioRequest;
import com.datos.medividrios.dto.vidrio.VidrioResponse;
import com.datos.medividrios.service.VidrioService;
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
    private VidrioService vidrioService;

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

}
