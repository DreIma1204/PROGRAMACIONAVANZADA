package Utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Modelo.MFilaCatalogo;

public final class UExcelCatalogoLector {

    private UExcelCatalogoLector() {}

    public static List<MFilaCatalogo> leerCatalogo(Path archivoExcel) throws IOException {
        List<MFilaCatalogo> filas = new ArrayList<>();
        if (!Files.exists(archivoExcel)) return filas;

        try (FileInputStream fis = new FileInputStream(archivoExcel.toFile());
             Workbook libro = new XSSFWorkbook(fis)) {
            Sheet hoja = libro.getSheetAt(0);
            if (hoja == null) return filas;

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= hoja.getLastRowNum(); i++) {
                Row fila = hoja.getRow(i);
                if (fila == null) continue;

                // Ajustado según el orden real de tus columnas en el Excel
                String grupo      = obtenerTexto(fila.getCell(0), formatter); // Col A
                String profesor   = obtenerTexto(fila.getCell(1), formatter); // Col B
                String asignatura = obtenerTexto(fila.getCell(2), formatter); // Col C
                String alumno     = obtenerTexto(fila.getCell(4), formatter); // Col E

                if (!profesor.isEmpty() && !asignatura.isEmpty()) {
                    filas.add(new MFilaCatalogo(profesor, asignatura, grupo, alumno));
                }
            }
        }
        return filas;
    }

    private static String obtenerTexto(Cell celda, DataFormatter formatter) {
        if (celda == null) return "";
        return formatter.formatCellValue(celda).trim();
    }
}