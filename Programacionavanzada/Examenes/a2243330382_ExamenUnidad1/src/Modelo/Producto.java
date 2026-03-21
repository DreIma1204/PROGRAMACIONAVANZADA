package Modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.File;

public abstract class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    private String imagenRuta;
    private String conservacion;

    public Producto(String id, String nombre, double precio, int stock, String categoria, String imagenRuta, String conservacion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.imagenRuta = imagenRuta;
        this.conservacion = conservacion;
    }

    public void generarArchivoJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File folder = new File("datos/productos");
        if (!folder.exists()) folder.mkdirs();

        try (FileWriter writer = new FileWriter("datos/productos/" + id + ".json")) {
            gson.toJson(this, writer);
        } catch (Exception e) {
            System.err.println("Error en JSON de " + nombre + ": " + e.getMessage());
        }
    }

    public abstract String getDetallesEspeciales();

    public String getId() { 
    	return id; 
    	}
    public String getNombre() { 
    	return nombre; 
    	}
    public double getPrecio() { 
    	return precio; 
    	}
    public int getStock() { 
    	return stock; 
    	}
    public String getCategoria() { 
    	return categoria; 
    	}
    public String getImagenRuta() { 
    	return imagenRuta; 
    	}
    public String getConservacion() { 
    	return conservacion; 
    	}
}