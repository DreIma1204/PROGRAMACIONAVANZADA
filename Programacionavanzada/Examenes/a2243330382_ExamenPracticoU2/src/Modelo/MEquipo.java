package Modelo;

import com.google.gson.annotations.SerializedName;

public class MEquipo {

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("calificacion")
    private double calificacion;

    public MEquipo() {
        this.nombre = "";
        this.calificacion = 0.0;
    }

    public MEquipo(String nombre, double calificacion) {
        this.nombre = nombre != null ? nombre : "";
        this.calificacion = calificacion;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void establecerNombre(String nombre) {
        this.nombre = nombre != null ? nombre : "";
    }

    public double obtenerCalificacion() {
        return calificacion;
    }

    public void establecerCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}
