package Modelo;
public class SnacksyDulceria extends Producto {
    public SnacksyDulceria(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Snacks y Dulcería", imagen, "Compra por impulso");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Ubicación sugerida: Cerca de cajas de cobro."; 
    	}
}