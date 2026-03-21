package Modelo;
public class LacteosyHuevos extends Producto {
    public LacteosyHuevos(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Lácteos y Huevo", imagen, "Cadena de Frío");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Mantener refrigerado entre 2°C y 6°C."; 
    	}
}