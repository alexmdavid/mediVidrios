package com.datos.medividrios.service;

import com.datos.medividrios.dto.cliente.ClienteGastoResponse;
import com.datos.medividrios.dto.cubicacion.CubicacionTotalResponse;
import com.datos.medividrios.model.Artefacto;
import com.datos.medividrios.model.Cliente;
import com.datos.medividrios.model.Medicion;
import com.datos.medividrios.model.Vidrio;
import com.datos.medividrios.repository.ClienteRepository;
import com.datos.medividrios.repository.MedicionRepository;
import com.datos.medividrios.service.iservices.IMedicionService;
import com.datos.medividrios.util.Util;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.datos.medividrios.dto.medicion.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicionService implements IMedicionService {

    @Autowired
    private final MedicionRepository medicionRepository;
    @Autowired
    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public MedicionResponse crearMedicion(MedicionRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + request.getClienteId()));

        Medicion medicion = Medicion.builder()
                .descripcion(request.getDescripcion())
                .fechaRegistro(request.getFechaRegistro())
                .fechaEntrega(request.getFechaEntrega())
                .hayMasDeUnPiso(request.getHayMasDeUnPiso())
                .estadoVenta(request.getEstadoVenta())
                .cliente(cliente)
                .build();

        Medicion guardado = medicionRepository.save(medicion);

        return MedicionResponse.builder()
                .id(guardado.getId())
                .descripcion(guardado.getDescripcion())
                .fechaRegistro(guardado.getFechaRegistro())
                .fechaEntrega(guardado.getFechaEntrega())
                .hayMasDeUnPiso(guardado.getHayMasDeUnPiso())
                .estadoVenta(guardado.getEstadoVenta())
                .clienteId(cliente.getId())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public MedicionResponse obtenerMedicionPorId(Long id) {
        Medicion medicion = medicionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medición no encontrada con ID: " + id));

        return MedicionResponse.builder()
                .id(medicion.getId())
                .descripcion(medicion.getDescripcion())
                .fechaRegistro(medicion.getFechaRegistro())
                .fechaEntrega(medicion.getFechaEntrega())
                .hayMasDeUnPiso(medicion.getHayMasDeUnPiso())
                .estadoVenta(medicion.getEstadoVenta())
                .clienteId(medicion.getCliente().getId())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicionResponse> obtenerTodasLasMediciones() {
        return medicionRepository.findAll().stream()
                .map(medicion -> MedicionResponse.builder()
                        .id(medicion.getId())
                        .descripcion(medicion.getDescripcion())
                        .fechaRegistro(medicion.getFechaRegistro())
                        .fechaEntrega(medicion.getFechaEntrega())
                        .hayMasDeUnPiso(medicion.getHayMasDeUnPiso())
                        .estadoVenta(medicion.getEstadoVenta())
                        .clienteId(medicion.getCliente().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MedicionResponse actualizarMedicion(Long id, MedicionRequest request) {
        Medicion medicion = medicionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medición no encontrada con ID: " + id));

        medicion.setDescripcion(request.getDescripcion());
        medicion.setFechaRegistro(request.getFechaRegistro());
        medicion.setFechaEntrega(request.getFechaEntrega());
        medicion.setHayMasDeUnPiso(request.getHayMasDeUnPiso());
        medicion.setEstadoVenta(request.getEstadoVenta());
        if (!medicion.getCliente().getId().equals(request.getClienteId())) {
            Cliente cliente = clienteRepository.findById(request.getClienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + request.getClienteId()));
            medicion.setCliente(cliente);
        }

        Medicion actualizado = medicionRepository.save(medicion);

        return MedicionResponse.builder()
                .id(actualizado.getId())
                .descripcion(actualizado.getDescripcion())
                .fechaRegistro(actualizado.getFechaRegistro())
                .fechaEntrega(actualizado.getFechaEntrega())
                .hayMasDeUnPiso(actualizado.getHayMasDeUnPiso())
                .estadoVenta(actualizado.getEstadoVenta())
                .clienteId(actualizado.getCliente().getId())
                .build();
    }

    @Override
    @Transactional
    public void eliminarMedicion(Long id) {
        Medicion medicion = medicionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medición no encontrada con ID: " + id));
        medicionRepository.delete(medicion);
    }





    @Override
    public List<MedicionResponse> obtenerMedicionesPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + clienteId);
        }

        List<Medicion> mediciones = medicionRepository.findByClienteId(clienteId);
        return mediciones.stream().map(medicion -> {
            MedicionResponse response = new MedicionResponse();
            response.setId(medicion.getId());
            response.setDescripcion(medicion.getDescripcion());
            response.setHayMasDeUnPiso(medicion.getHayMasDeUnPiso());
            response.setFechaRegistro(medicion.getFechaRegistro());
            response.setFechaEntrega(medicion.getFechaEntrega());
            response.setEstadoVenta(medicion.getEstadoVenta());
            response.setClienteId(medicion.getCliente().getId());
            return response;
        }).collect(Collectors.toList());
    }


    //cubicacion total, general y globalmente masiza por una bendita medicion o venta como a llaman el del frontend
    @Override
    public CubicacionTotalResponse obtenerCubicacionTotalPorMedicion(Long medicionId) {
        Medicion medicion = medicionRepository.findById(medicionId)
                .orElseThrow(() -> new EntityNotFoundException("Medición no encontrada con ID: " + medicionId));

        double total = medicion.getArtefactos().stream()
                .flatMap(artefacto -> artefacto.getVidrios().stream())
                .mapToDouble(v -> {
                    double[] medidas = Util.cubicar(v.getAncho_cm(), v.getAlto_cm());
                    return medidas[0] * medidas[1];
                })
                .sum();

        return new CubicacionTotalResponse(medicionId, total);
    }






    //clacular costo de cada vidrio, ya que estos varian segun el tipo
    @Override
    public double calcularCostoMedicion(Long medicionId) {
        Medicion medicion = medicionRepository.findById(medicionId)
                .orElseThrow(() -> new EntityNotFoundException("Medición no encontrada con ID: " + medicionId));

        double costoTotal = 0.0;

        for (Artefacto artefacto : medicion.getArtefactos()) {
            for (Vidrio vidrio : artefacto.getVidrios()) {
                double[] medidasCubicadas = Util.cubicar(vidrio.getAncho_cm(), vidrio.getAlto_cm());
                double anchoCubicado = medidasCubicadas[0];
                double altoCubicado = medidasCubicadas[1];
                double area = anchoCubicado * altoCubicado;
                double precioM2 = vidrio.getPrecioM2() != null ? vidrio.getPrecioM2() : 0.0;
                double costoVidrio = area * precioM2;
                costoTotal += costoVidrio;
            }
        }
        return costoTotal;
    }

}
