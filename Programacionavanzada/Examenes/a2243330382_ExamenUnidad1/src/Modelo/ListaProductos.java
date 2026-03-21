package Modelo;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ListaProductos {
    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregar(Producto p) { productos.add(p); }
    public ArrayList<Producto> getTodos() { return productos; }

    // REPORTE GENERAL
    public void exportarExcelGeneral(String nombreArchivo) {
        // Le cambiamos la extensión a .csv para que Excel lo reconozca
        String nombreFinal = nombreArchivo.replace(".xlsx", ".csv").replace(".xls", ".csv");
        generarCSV(productos, nombreFinal);
    }

    // REPORTE POR CATEGORÍA
    public void exportarExcelPorCategoria(String categoria, String nombreArchivo) {
        ArrayList<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                filtrados.add(p);
            }
        }
        String nombreFinal = nombreArchivo.replace(".xlsx", ".csv").replace(".xls", ".csv");
        generarCSV(filtrados, nombreFinal);
    }

    private void generarCSV(ArrayList<Producto> lista, String nombreArchivo) {
        // Usamos BufferedWriter y OutputStreamWriter para manejar acentos (UTF-8)
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombreArchivo), "UTF-8"))) {
            
            // Escribir la marca de orden de bytes (BOM) para que Excel reconozca el UTF-8
            bw.write('\ufeff');

            // Encabezados
            bw.write("ID,NOMBRE,CATEGORIA,STOCK,PRECIO,DETALLES");
            bw.newLine();

            // Datos
            for (Producto p : lista) {
                String linea = String.format("%s,%s,%s,%d,%.2f,%s",
                        p.getId(),
                        p.getNombre(),
                        p.getCategoria(),
                        p.getStock(),
                        p.getPrecio(),
                        p.getConservacion().replace(",", ";") // Evitar que las comas rompan las columnas
                );
                bw.write(linea);
                bw.newLine();
            }
            
            JOptionPane.showMessageDialog(null, "Reporte generado: " + nombreArchivo);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar reporte: " + e.getMessage());
        }
    }
}