package com.datos.medividrios.service;

import com.datos.medividrios.dto.cotizacion.ItemCotizacion;
import com.datos.medividrios.dto.cotizacion.Cotizacion;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DocumentoPdfService {

    public void generarPdfCotizacion(Cotizacion cot, String ruta) throws Exception {
        if (cot == null) {
            throw new IllegalArgumentException("La cotización es null");
        }

        if (ruta == null || ruta.isBlank()) {
            throw new IllegalArgumentException("La ruta del archivo PDF es inválida");
        }

        if (cot.getItems() == null || cot.getItems().isEmpty()) {
            throw new IllegalArgumentException("La cotización no contiene ítems.");
        }

        Document document = new Document(PageSize.A4, 40, 40, 40, 40);
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();

        Font fontTitulo = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
        Font fontNormal = FontFactory.getFont(FontFactory.TIMES, 11);
        Font fontBold = FontFactory.getFont(FontFactory.TIMES_BOLD, 11);

        // Encabezado
        Paragraph p = new Paragraph("RUBIEL ANTONIO RUIDIAZ COMAS\n", fontBold);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("RUT: 85165741 – 7\n", fontBold);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("Correo: rubanruic@gmail.com – Celular: 3103233594\n", fontBold);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("Calle 20 # 28 – 21 DUITAMA\n\n", fontBold);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);

        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        document.add(new Paragraph("Duitama, " + sdf.format(cot.getFecha()), fontNormal));
        document.add(new Paragraph("\n"));

        document.add(new Paragraph(cot.getTituloCliente(), fontNormal));
        document.add(new Paragraph(cot.getCliente() != null ? cot.getCliente() : "CLIENTE NO DEFINIDO", fontNormal));
        document.add(new Paragraph("Ciudad\n\nCordial saludo:\n", fontNormal));

        document.add(new Paragraph("COTIZACIÓN:\n", fontBold));

        // Tabla de ítems
        PdfPTable table = new PdfPTable(new float[]{1, 3, 1, 1, 1});
        table.setWidthPercentage(100);
        addCellHeader(table, "Ítems");
        addCellHeader(table, "Detalle");
        addCellHeader(table, "Área en M²");
        addCellHeader(table, "Valor M²");
        addCellHeader(table, "Valor Total");

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        NumberFormat decimalFormat = NumberFormat.getNumberInstance(new Locale("es", "CO"));
        decimalFormat.setMaximumFractionDigits(2);

        List<List<ItemCotizacion>> grupos = agruparItems(cot.getItems());
        int index = 1;
        for (List<ItemCotizacion> grupo : grupos) {
            boolean primera = true;
            for (ItemCotizacion item : grupo) {
                addCell(table, primera ? String.valueOf(index) : "");
                addCell(table, item.getDescripcion() != null ? item.getDescripcion() : "");
                addCell(table, decimalFormat.format(item.getArea()));
                addCell(table, currencyFormat.format(item.getValorM2()));
                addCell(table, currencyFormat.format(item.getTotal()));
                primera = false;
            }
            index++;
        }

        document.add(table);
        document.add(new Paragraph("\n"));

        Paragraph condiciones = new Paragraph();
        condiciones.add(new Chunk("CONDICIONES ECONÓMICAS: ", fontBold));
        condiciones.add(new Chunk("60% de anticipo al aceptar esta cotización y 40% contra entrega.\n\n", fontNormal));
        condiciones.add(new Chunk("NO INCLUYE: ", fontBold));
        condiciones.add(new Chunk("obras de albañilería.\n\n", fontNormal));
        condiciones.add(new Chunk("TIEMPO DE ENTREGA: ", fontBold));
        condiciones.add(new Chunk(cot.getTiempoEntrega() != null ? cot.getTiempoEntrega() : "No especificado\n\n", fontNormal));
        condiciones.add(new Chunk("VALIDEZ OFERTA: ", fontBold));
        condiciones.add(new Chunk(cot.getValidezOferta() != null ? cot.getValidezOferta().toUpperCase() : "SIN INFORMACIÓN\n\n", fontNormal));

        document.add(condiciones);

        document.add(new Paragraph("\n\nCordialmente,\n\n", fontNormal));
        document.add(new Paragraph("RUBIEL ANTONIO RUIDIAZ COMAS", fontBold));
        document.add(new Paragraph("C.C.85165741 de Guamal Magdalena", fontBold));

        document.close();
    }

    private void addCellHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.TIMES_BOLD, 11)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(220, 220, 220));
        cell.setPadding(5);
        table.addCell(cell);
    }

    private void addCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.TIMES, 11)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);
    }

    private List<List<ItemCotizacion>> agruparItems(List<ItemCotizacion> items) {
        List<List<ItemCotizacion>> grupos = new ArrayList<>();
        for (int i = 0; i < items.size(); i += 2) {
            grupos.add(items.subList(i, Math.min(i + 2, items.size())));
        }
        return grupos;
    }



}
