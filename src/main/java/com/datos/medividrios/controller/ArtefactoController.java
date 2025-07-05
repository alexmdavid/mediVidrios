package com.datos.medividrios.controller;

import com.datos.medividrios.dto.artefacto.ArtefactoRequest;
import com.datos.medividrios.dto.artefacto.ArtefactoResponse;
import com.datos.medividrios.service.ArtefactoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/artefacto")
@RequiredArgsConstructor
public class ArtefactoController {
    private final ArtefactoService artefactoService;


    @PostMapping("/api/artefactos")
    public ResponseEntity<ArtefactoResponse> crearArtefacto(@RequestBody @Valid ArtefactoRequest request) {
        ArtefactoResponse response = artefactoService.crearArtefacto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
