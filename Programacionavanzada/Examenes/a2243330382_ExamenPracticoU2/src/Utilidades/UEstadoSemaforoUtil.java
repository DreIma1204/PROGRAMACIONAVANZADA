package Utilidades;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Paths;
import Modelo.MEvaluacionRegistro;

public final class UEstadoSemaforoUtil {

    public static final int ROJO = 0;
    public static final int AMARILLO = 1;
    public static final int VERDE = 2;

    private UEstadoSemaforoUtil() {
    }

    public static int calcularEstado(MEvaluacionRegistro r) {
        if (r == null || estaVacioInicial(r)) {
            return ROJO;
        }

        boolean tieneRubrica = r.obtenerFilasRubrica() != null && !r.obtenerFilasRubrica().isEmpty();
        boolean tieneListaCotejo = r.obtenerItemsListaCotejo() != null && !r.obtenerItemsListaCotejo().isEmpty();
        
        boolean archivoExiste = false;
        if (r.esExcelGenerado() && r.obtenerRutaExcel() != null && !r.obtenerRutaExcel().isBlank()) {
            archivoExiste = Files.exists(Paths.get(r.obtenerRutaExcel()));
        }

        if (tieneRubrica && tieneListaCotejo && archivoExiste) {
            return VERDE;
        }

        return AMARILLO;
    }

    public static Color colorPorEstado(int estado) {
        return switch (estado) {
            case VERDE -> new Color(0, 160, 60);
            case AMARILLO -> new Color(220, 180, 0);
            default -> new Color(200, 40, 40);
        };
    }

    private static boolean estaVacioInicial(MEvaluacionRegistro r) {
        return r.obtenerAsignatura() == null || r.obtenerAsignatura().isBlank() ||
               r.obtenerProfesor() == null || r.obtenerProfesor().isBlank() ||
               r.obtenerGrupo() == null || r.obtenerGrupo().isBlank();
    }
}