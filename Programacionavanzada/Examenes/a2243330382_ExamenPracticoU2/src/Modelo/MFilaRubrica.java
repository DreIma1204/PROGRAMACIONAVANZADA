package Modelo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MFilaRubrica {

    @SerializedName("nombre_alumno")
    private String nombreAlumno;

    @SerializedName("calificaciones_criterios")
    private List<Double> calificacionesCriterios;

    @SerializedName("promedio")
    private double promedio;

    public MFilaRubrica() {
        this.nombreAlumno = "";
        this.calificacionesCriterios = new ArrayList<>();
        this.promedio = 0.0;
    }

    public String obtenerNombreAlumno() {
        return nombreAlumno;
    }

    public void establecerNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno != null ? nombreAlumno : "";
    }

    public List<Double> obtenerCalificacionesCriterios() {
        return calificacionesCriterios;
    }

    public void establecerCalificacionesCriterios(List<Double> calificacionesCriterios) {
        this.calificacionesCriterios = calificacionesCriterios != null ? calificacionesCriterios : new ArrayList<>();
    }

    public double obtenerPromedio() {
        return promedio;
    }

    public void establecerPromedio(double promedio) {
        this.promedio = promedio;
    }
}
