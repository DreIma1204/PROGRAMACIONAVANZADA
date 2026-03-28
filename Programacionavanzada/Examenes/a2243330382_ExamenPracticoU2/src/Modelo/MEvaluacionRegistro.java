package Modelo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MEvaluacionRegistro {
    @SerializedName("id") private String id;
    @SerializedName("asignatura") private String asignatura;
    @SerializedName("profesor") private String profesor;
    @SerializedName("grupo") private String grupo;
    @SerializedName("datos_instrumento") private MDatosInstrumento datosInstrumento;
    @SerializedName("equipos") private List<MEquipo> equipos;
    @SerializedName("filas_rubrica") private List<MFilaRubrica> filasRubrica;
    @SerializedName("items_lista_cotejo") private List<MItemCotejo> itemsListaCotejo;
    @SerializedName("texto_producto_integrador") private String textoProductoIntegrador;
    @SerializedName("excel_generado") private boolean excelGenerado;
    @SerializedName("ruta_excel") private String rutaExcel;

    public MEvaluacionRegistro() {
        this.datosInstrumento = new MDatosInstrumento();
        this.equipos = new ArrayList<>();
        this.filasRubrica = new ArrayList<>();
        this.itemsListaCotejo = new ArrayList<>();
        this.id = "";
        this.asignatura = "";
        this.profesor = "";
        this.grupo = "";
        this.textoProductoIntegrador = "";
    }

    public String obtenerId() { return id; }
    public void establecerId(String id) { this.id = id; }

    public String obtenerAsignatura() { return asignatura; }
    public void establecerAsignatura(String a) { this.asignatura = a; }

    public String obtenerProfesor() { return profesor; }
    public void establecerProfesor(String p) { this.profesor = p; }

    public String obtenerGrupo() { return grupo; }
    public void establecerGrupo(String g) { this.grupo = g; }

    public MDatosInstrumento obtenerDatosInstrumento() { return datosInstrumento; }
    
    public List<MEquipo> obtenerEquipos() { return equipos; }
    
    public List<MFilaRubrica> obtenerFilasRubrica() { return filasRubrica; }
    
    public List<MItemCotejo> obtenerItemsListaCotejo() { return itemsListaCotejo; }
    
    public void establecerItemsListaCotejo(List<MItemCotejo> items) { 
        this.itemsListaCotejo = items != null ? items : new ArrayList<>(); 
    }

    public String obtenerTextoProductoIntegrador() { return textoProductoIntegrador; }
    public void establecerTextoProductoIntegrador(String t) { this.textoProductoIntegrador = t; }

    public boolean esExcelGenerado() { return excelGenerado; }
    public void establecerExcelGenerado(boolean e) { this.excelGenerado = e; }

    public String obtenerRutaExcel() { return rutaExcel; }
    public void establecerRutaExcel(String r) { this.rutaExcel = r; }
}