package Modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MRepositorioEvaluaciones {

    private final Path rutaArchivo;
    private final Gson gson;

    public MRepositorioEvaluaciones(Path rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public Path obtenerRutaArchivo() {
        return rutaArchivo;
    }

    public MAlmacenEvaluaciones cargar() throws IOException {
        if (!Files.exists(rutaArchivo)) {
            return new MAlmacenEvaluaciones();
        }
        String contenido = Files.readString(rutaArchivo, StandardCharsets.UTF_8);
        if (contenido.isBlank()) {
            return new MAlmacenEvaluaciones();
        }
        MAlmacenEvaluaciones almacen = gson.fromJson(contenido, MAlmacenEvaluaciones.class);
        if (almacen == null) {
            return new MAlmacenEvaluaciones();
        }
        if (almacen.obtenerEvaluaciones() == null) {
            almacen.establecerEvaluaciones(new java.util.ArrayList<>());
        }
        return almacen;
    }

    public void guardar(MAlmacenEvaluaciones almacen) throws IOException {
        Path padre = rutaArchivo.getParent();
        if (padre != null) {
            Files.createDirectories(padre);
        }
        String json = gson.toJson(almacen);
        Files.writeString(rutaArchivo, json, StandardCharsets.UTF_8);
    }
}
