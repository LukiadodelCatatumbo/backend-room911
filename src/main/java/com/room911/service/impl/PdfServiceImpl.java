package com.room911.service.impl;

import com.room911.repository.AccessAttemptRepository;
import com.room911.repository.EmpleadoRepository;
import com.room911.service.interfaces.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import com.room911.entity.AccessAttempt;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final AccessAttemptRepository accessAttemptRepository;
    private final EmpleadoRepository empleadoRepository;

    @Override
    public byte[] generarHistorialEmpleado(Long empleadoId) {
        List<AccessAttempt> intentos =
                accessAttemptRepository.findByEmpleadoId(empleadoId);

        try {

            Document document = new Document();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, outputStream);

            document.open();

            document.add(new Paragraph("Historial de accesos ROOM_911"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Empleado ID: " + empleadoId));
            document.add(new Paragraph(" "));

            for (AccessAttempt intento : intentos) {

                document.add(new Paragraph(
                        "Fecha: " + intento.getFechaAcceso()
                ));

                document.add(new Paragraph(
                        "Resultado: "
                                + (intento.getExito() ? "Permitido" : "Denegado")
                ));

                document.add(new Paragraph(
                        "Mensaje: " + intento.getMessage()
                ));

                document.add(new Paragraph("--------------------------------"));
            }

            document.close();

            return outputStream.toByteArray();

        } catch (DocumentException e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}