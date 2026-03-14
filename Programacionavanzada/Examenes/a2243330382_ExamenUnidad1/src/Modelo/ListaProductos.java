package Modelo;

import java.util.ArrayList;

public class ListaProductos {
    private ArrayList<MProductos> lista;

    public ListaProductos() {
        this.lista = new ArrayList<>();
    }

    public void agregar(MProductos p) {
        lista.add(p);
    }

    public ArrayList<MProductos> getTodos() {
        return lista;
    }

    public MProductos buscarPorId(String id) {
        for (MProductos p : lista) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    public boolean eliminar(String id) {
        return lista.removeIf(p -> p.getId().equals(id));
    }

    // Convierte los datos crudos del CSV a objetos MProductos
    public void cargarDesdeResumen(ArrayList<String[]> datosCvs) {
        lista.clear();
        for (String[] fila : datosCvs) {
            if (fila.length >= 8) {
                agregar(new MProductos(
                    fila[0], fila[1], fila[2], fila[3], 
                    fila[4], Integer.parseInt(fila[5]), 
                    Double.parseDouble(fila[6]), Boolean.parseBoolean(fila[7])
                ));
            }
        }
    }
}