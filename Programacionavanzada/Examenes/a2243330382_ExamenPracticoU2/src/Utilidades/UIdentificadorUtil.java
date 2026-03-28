package Utilidades;

public final class UIdentificadorUtil {

    private UIdentificadorUtil() {
    }

    public static String generarId(String asignatura, String profesor, String grupo) {
        return normalizar(asignatura) + "_" + normalizar(profesor) + "_" + normalizar(grupo);
    }

    public static String normalizar(String texto) {
        if (texto == null || texto.isBlank()) {
            return "VACIO";
        }
        String t = texto.trim().replaceAll("\\s+", "_");
        t = t.replaceAll("[^\\p{L}\\p{N}_-]", "");
        if (t.isEmpty()) {
            return "VACIO";
        }
        return t;
    }
}
