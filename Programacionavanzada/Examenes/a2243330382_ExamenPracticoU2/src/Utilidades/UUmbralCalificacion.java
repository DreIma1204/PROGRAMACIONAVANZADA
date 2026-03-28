package Utilidades;

public final class UUmbralCalificacion {

    public static final double MINIMO_APROBATORIO = 6.0;

    private UUmbralCalificacion() {
    }

    public static boolean esReprobatorio(double calificacion) {
        return calificacion < MINIMO_APROBATORIO;
    }
}
