package Modelo;

import com.google.gson.annotations.SerializedName;

public class MItemCotejo {

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("marcado")
    private boolean marcado;

    public MItemCotejo() {
        this.descripcion = "";
        this.marcado = false;
    }

    public MItemCotejo(String descripcion, boolean marcado) {
        this.descripcion = descripcion != null ? descripcion : "";
        this.marcado = marcado;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public void establecerDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion : "";
    }

    public boolean estaMarcado() {
        return marcado;
    }

    public void establecerMarcado(boolean marcado) {
        this.marcado = marcado;
    }
}
