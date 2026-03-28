package Modelo;

import com.google.gson.annotations.SerializedName;

public class MDatosInstrumento {

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("criterios")
    private String criterios;

    @SerializedName("observaciones")
    private String observaciones;

    public MDatosInstrumento() {
        this.fecha = "";
        this.criterios = "";
        this.observaciones = "";
    }

    public String obtenerFecha() {
        return fecha;
    }

    public void establecerFecha(String fecha) {
        this.fecha = fecha != null ? fecha : "";
    }

    public String obtenerCriterios() {
        return criterios;
    }

    public void establecerCriterios(String criterios) {
        this.criterios = criterios != null ? criterios : "";
    }

    public String obtenerObservaciones() {
        return observaciones;
    }

    public void establecerObservaciones(String observaciones) {
        this.observaciones = observaciones != null ? observaciones : "";
    }
}
