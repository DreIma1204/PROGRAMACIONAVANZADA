package Utilidades;

import java.util.List;
import java.util.Locale;

import Modelo.MEvaluacionRegistro;

public final class UValidadorDuplicados {

    private UValidadorDuplicados() {
    }

    public static boolean existeDuplicado(List<MEvaluacionRegistro> lista, MEvaluacionRegistro candidato, String idExcluir) {
        String a = normalizarLlave(candidato.obtenerAsignatura());
        String p = normalizarLlave(candidato.obtenerProfesor());
        String g = normalizarLlave(candidato.obtenerGrupo());
        for (MEvaluacionRegistro r : lista) {
            if (idExcluir != null && idExcluir.equals(r.obtenerId())) {
                continue;
            }
            if (a.equals(normalizarLlave(r.obtenerAsignatura()))
                    && p.equals(normalizarLlave(r.obtenerProfesor()))
                    && g.equals(normalizarLlave(r.obtenerGrupo()))) {
                return true;
            }
        }
        return false;
    }

    private static String normalizarLlave(String s) {
        if (s == null) {
            return "";
        }
        return s.trim().toLowerCase(Locale.ROOT);
    }
}
