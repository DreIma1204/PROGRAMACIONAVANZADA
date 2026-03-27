package Modelo;
public class CarnesyPescado extends Producto {
    public CarnesyPescado(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Carnes y Pescados", imagen, "Congelados / Frescos");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Requiere congelación inmediata para conservación."; 
    	}
}