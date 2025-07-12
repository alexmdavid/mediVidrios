package com.datos.medividrios.service;

import com.datos.medividrios.dto.artefacto.ArtefactoRequest;
import com.datos.medividrios.dto.vidrio.VidrioRequest;
import com.datos.medividrios.model.Artefacto;
import com.datos.medividrios.model.Medicion;
import com.datos.medividrios.repository.ArtefactoRepository;
import com.datos.medividrios.repository.MedicionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArtefactoServiceTest {

    @InjectMocks
    private ArtefactoService artefactoService;

    @Mock
    private ArtefactoRepository artefactoRepository;

    @Mock
    private MedicionRepository medicionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearArtefactoCorrectamente() {
        Medicion medicion = new Medicion();
        medicion.setId(1L);

        ArtefactoRequest request = new ArtefactoRequest();
        request.setNombre("Puerta");
        request.setMedicionId(1L);
        VidrioRequest vr = new VidrioRequest();
        vr.setAlto_cm(100);
        vr.setAncho_cm(50);
        request.setVidrios(List.of(vr));

        when(medicionRepository.findById(1L)).thenReturn(Optional.of(medicion));
        when(artefactoRepository.save(any(Artefacto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = artefactoService.crearArtefacto(request);

        assertNotNull(response);
        assertEquals("Puerta", response.getNombre());
        assertEquals(1L, response.getMedicionId());
        assertEquals(1, response.getVidrios().size());

        verify(artefactoRepository, times(1)).save(any(Artefacto.class));
    }

    @Test
    void testCrearArtefactoMedicionNoEncontrada() {
        ArtefactoRequest request = new ArtefactoRequest();
        request.setMedicionId(99L);

        when(medicionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> artefactoService.crearArtefacto(request));
    }
}
