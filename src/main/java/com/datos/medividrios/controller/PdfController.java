package com.datos.medividrios.controller;

import com.datos.medividrios.dto.cotizacion.Cotizacion;
import com.datos.medividrios.service.CotizacionPdfService;
import com.datos.medividrios.service.DocumentoPdfService;
import com.datos.medividrios.service.iservices.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pdf")
@RequiredArgsConstructor
public class PdfController {
    @Autowired
    private DocumentoPdfService documentoPdfService;
    @Autowired
    private CotizacionPdfService cotizacionPdfService;

    @GetMapping("/{id}/cotizacion-pdf")
    public ResponseEntity<byte[]> generarCotizacionPdf(@PathVariable Long id) {
        byte[] pdfBytes = cotizacionPdfService.generarCotizacionPdf(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cotizacion_medicion_" + id + ".pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
