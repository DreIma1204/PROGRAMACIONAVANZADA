package Modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

public class TicketVenta {
    private String folio;
    private Date fecha;
    private ArrayList<Producto> items;
    private double total;

    public TicketVenta(String folio, ArrayList<Producto> items, double total) {
        this.folio = folio;
        this.fecha = new Date();
        this.items = items;
        this.total = total;
    }

    public void guardarTicketJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // Nombre del archivo es el código de folio
        try (FileWriter writer = new FileWriter("datos/tickets/" + this.folio + ".json")) {
            gson.toJson(this, writer);
            System.out.println("Ticket JSON guardado con folio: " + folio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}