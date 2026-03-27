package Modelo;
public class PanaderiayTortilleria extends Producto {
    public PanaderiayTortilleria(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Panadería y Tortillería", imagen, "Alta rotación diaria");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Consumo preferente: 24 horas. Producto artesanal."; 
    	}
}