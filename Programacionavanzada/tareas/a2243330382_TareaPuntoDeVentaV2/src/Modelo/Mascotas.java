package Modelo;
public class Mascotas extends Producto {
    public Mascotas(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Mascotas", imagen, "Volumen / Peso");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Alimento y accesorios para animales domésticos."; 
    	}
}