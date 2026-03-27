package Modelo;
public class LimpiezaDelHogar extends Producto {
    public LimpiezaDelHogar(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Limpieza del Hogar", imagen, "Químicos (Aislados)");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Mantener alejado de alimentos. Tóxico si se ingiere."; 
    	}
}