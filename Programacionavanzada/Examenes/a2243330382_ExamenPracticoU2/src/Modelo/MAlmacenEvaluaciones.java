package Modelo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MAlmacenEvaluaciones {

    @SerializedName("evaluaciones")
    private List<MEvaluacionRegistro> evaluaciones;

    @SerializedName("ruta_carpeta_reportes")
    private String rutaCarpetaReportes;

    public MAlmacenEvaluaciones() {
        this.evaluaciones = new ArrayList<>();
        this.rutaCarpetaReportes = "";
    }

    public List<MEvaluacionRegistro> obtenerEvaluaciones() { return evaluaciones; }
    public void establecerEvaluaciones(List<MEvaluacionRegistro> evaluaciones) { 
        this.evaluaciones = evaluaciones != null ? evaluaciones : new ArrayList<>(); 
    }

    public String obtenerRutaCarpetaReportes() { return rutaCarpetaReportes != null ? rutaCarpetaReportes : ""; }
    public void establecerRutaCarpetaReportes(String ruta) { this.rutaCarpetaReportes = ruta != null ? ruta : ""; }
}