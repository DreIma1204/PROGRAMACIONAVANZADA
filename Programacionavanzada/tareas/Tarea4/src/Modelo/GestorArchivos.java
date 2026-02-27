package Modelo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
    public static List<Pregunta> cargarDesdeCSV(File archivo) throws Exception {
        List<Pregunta> lista = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 6) {
                String[] opciones = {partes[2], partes[3], partes[4], partes[5]};
                lista.add(new Pregunta(partes[0], partes[1], opciones));
            }
        }
        br.close();

        if (lista.size() < 5) {
            throw new Exception("El archivo debe tener al menos 5 preguntas.");
        }
        return lista;
    }
}