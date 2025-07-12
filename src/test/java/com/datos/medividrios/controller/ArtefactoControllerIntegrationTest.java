package com.datos.medividrios.controller;

import com.datos.medividrios.dto.artefacto.ArtefactoRequest;
import com.datos.medividrios.dto.vidrio.VidrioRequest;
import com.datos.medividrios.model.Medicion;
import com.datos.medividrios.repository.MedicionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtefactoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicionRepository medicionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long medicionId;

    @BeforeEach
    void setUp() {
        Medicion medicion = new Medicion();
        medicion.setDescripcion("Medición de prueba");
        medicion = medicionRepository.save(medicion);
        medicionId = medicion.getId();
    }

    @Test
    void testCrearArtefacto() throws Exception {
        ArtefactoRequest request = new ArtefactoRequest();
        request.setNombre("Ventana");
        request.setMedicionId(medicionId);

        VidrioRequest vidrioRequest = new VidrioRequest();
        vidrioRequest.setAlto_cm(120);
        vidrioRequest.setAncho_cm(80);
        request.setVidrios(List.of(vidrioRequest));

        mockMvc.perform(post("/api/artefacto/api/artefactos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Ventana"))
                .andExpect(jsonPath("$.medicionId").value(medicionId))
                .andExpect(jsonPath("$.vidrios[0].alto_cm").value(120.0))
                .andExpect(jsonPath("$.vidrios[0].ancho_cm").value(80.0));
    }

    @Test
    void testObtenerArtefactosPorMedicion() throws Exception {
        mockMvc.perform(get("/api/artefacto/medicion/" + medicionId))
                .andExpect(status().isOk());
    }
}

