package Controlador;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PersistenciaDatos {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String RUTA_TICKETS = "tickets/";

    public PersistenciaDatos() {
        File folder = new File(RUTA_TICKETS);
        if (!folder.exists()) folder.mkdirs();
    }

    // --- GUARDAR VENTA Y GENERAR FOLIO ---
    public String procesarVenta(DefaultTableModel modelo, double total) {
        String folio = generarSiguienteFolio();
        
        JsonObject ticket = new JsonObject();
        ticket.addProperty("folio", folio);
        ticket.addProperty("fecha", "20/03/2026"); // Fecha del sistema
        ticket.addProperty("total_pagado", total);

        JsonArray productos = new JsonArray();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            JsonObject item = new JsonObject();
            item.addProperty("id", modelo.getValueAt(i, 0).toString());
            item.addProperty("nombre", modelo.getValueAt(i, 1).toString());
            item.addProperty("cant", modelo.getValueAt(i, 2).toString());
            item.addProperty("subtotal", modelo.getValueAt(i, 3).toString());
            productos.add(item);
        }
        ticket.add("detalle_compra", productos);

        try (FileWriter writer = new FileWriter(RUTA_TICKETS + folio + ".json")) {
            gson.toJson(ticket, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folio;
    }

    // --- CONTROL DE FOLIOS (Historial) ---
    private String generarSiguienteFolio() {
        File f = new File(RUTA_TICKETS);
        File[] lista = f.listFiles((dir, name) -> name.endsWith(".json"));
        int siguiente = (lista == null) ? 1 : lista.length + 1;
        return String.format("TICKET-%03d", siguiente);
    }

    // --- LEER TODOS LOS FOLIOS PARA EL HISTORIAL ---
    public ArrayList<String> listarTickets() {
        ArrayList<String> folios = new ArrayList<>();
        File f = new File(RUTA_TICKETS);
        File[] lista = f.listFiles((dir, name) -> name.endsWith(".json"));
        if (lista != null) {
            for (File archivo : lista) {
                folios.add(archivo.getName().replace(".json", ""));
            }
        }
        return folios;
    }
}