package com.datos.medividrios.service;

import com.datos.medividrios.model.Medicion;
import com.datos.medividrios.model.Artefacto;
import com.datos.medividrios.model.Vidrio;
import com.datos.medividrios.repository.MedicionRepository;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CotizacionPdfService {

    private final MedicionRepository medicionRepository;

    public byte[] generarCotizacionPdf(Long medicionId) {
        Medicion medicion = medicionRepository.findById(medicionId)
                .orElseThrow(() -> new EntityNotFoundException("Medición no encontrada con ID: " + medicionId));

        String html = generarHtmlCotizacion(medicion);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF: " + e.getMessage(), e);
        }
    }

    private String generarHtmlCotizacion(Medicion medicion) {
        StringBuilder html = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#,###.##");

        html.append("<html><head>")
                .append("<style>")
                .append("body { font-family: sans-serif; font-size: 12px; }")
                .append("table { width: 100%; border-collapse: collapse; }")
                .append("th, td { border: 1px solid #333; padding: 5px; text-align: center; }")
                .append("</style>")
                .append("</head><body>");

        html.append("<h2 style='text-align:center;'>Cotización de Medición</h2>");
        html.append("<p>Cliente: ").append(medicion.getCliente() != null ? medicion.getCliente().getNombre() : "N/A").append("</p>");
        html.append("<p>Fecha: ").append(medicion.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("</p>");
        html.append("<p>Descripción: ").append(medicion.getDescripcion()).append("</p>");

        html.append("<table><thead><tr>")
                .append("<th>Artefacto</th><th>Vidrio</th><th>Ancho</th><th>Alto</th><th>Área (m²)</th><th>Precio m²</th><th>Valor Total</th>")
                .append("</tr></thead><tbody>");

        double totalArea = 0.0;
        double totalValor = 0.0;

        for (Artefacto artefacto : medicion.getArtefactos()) {
            for (Vidrio vidrio : artefacto.getVidrios()) {
                double[] medidas = com.datos.medividrios.util.Util.cubicar(vidrio.getAncho_cm(), vidrio.getAlto_cm());
                double ancho = medidas[0];
                double alto = medidas[1];
                double area = ancho * alto;
                double precioM2 = vidrio.getPrecioM2() != null ? vidrio.getPrecioM2() : 0.0;
                double valor = area * precioM2;

                totalArea += area;
                totalValor += valor;

                html.append("<tr>")
                        .append("<td>").append(artefacto.getNombre()).append("</td>")
                        .append("<td>").append(vidrio.getTipo() != null ? vidrio.getTipo() : "N/A").append("</td>")
                        .append("<td>").append(df.format(ancho)).append("</td>")
                        .append("<td>").append(df.format(alto)).append("</td>")
                        .append("<td>").append(df.format(area)).append("</td>")
                        .append("<td>").append(df.format(precioM2)).append("</td>")
                        .append("<td>").append(df.format(valor)).append("</td>")
                        .append("</tr>");
            }
        }

        html.append("</tbody></table>");
        html.append("<p><b>Total área:</b> ").append(df.format(totalArea)).append(" m²</p>");
        html.append("<p><b>Total valor:</b> $").append(df.format(totalValor)).append("</p>");
        html.append("<p>60% de anticipo al aceptar esta cotización, 40% contra entrega.</p>");
        html.append("</body></html>");

        return html.toString();
    }
}
