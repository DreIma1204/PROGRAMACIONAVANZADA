package Utilidades;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Modelo.*;

public final class UExcelServicio {
    private UExcelServicio() {}

    public static void generarReporte(MEvaluacionRegistro registro, Path ruta) throws IOException {
        try (Workbook libro = new XSSFWorkbook()) {
            Sheet hoja = libro.createSheet("Evaluación de Atributos");
            
            // Estilos
            CellStyle estiloHeader = libro.createCellStyle();
            Font fuenteH = libro.createFont();
            fuenteH.setBold(true);
            estiloHeader.setFont(fuenteH);

            int f = 0;
            // Encabezados
            crearFilaInfo(hoja, f++, "ID REGISTRO:", registro.obtenerId());
            crearFilaInfo(hoja, f++, "ASIGNATURA:", registro.obtenerAsignatura());
            crearFilaInfo(hoja, f++, "PROFESOR:", registro.obtenerProfesor());
            crearFilaInfo(hoja, f++, "GRUPO:", registro.obtenerGrupo());
            f++;

            // Sección Rúbrica
            Row rowR = hoja.createRow(f++);
            rowR.createCell(0).setCellValue("ALUMNO");
            rowR.createCell(1).setCellValue("PROMEDIO");
            
            for (MFilaRubrica fr : registro.obtenerFilasRubrica()) {
                Row fila = hoja.createRow(f++);
                fila.createCell(0).setCellValue(fr.obtenerNombreAlumno());
                fila.createCell(1).setCellValue(fr.obtenerPromedio());
            }

            // Autoajuste
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            try (FileOutputStream fos = new FileOutputStream(ruta.toFile())) {
                libro.write(fos);
            }
        }
    }

    private static void crearFilaInfo(Sheet hoja, int numFila, String etiqueta, String valor) {
        Row fila = hoja.createRow(numFila);
        fila.createCell(0).setCellValue(etiqueta);
        fila.createCell(1).setCellValue(valor);
    }
}