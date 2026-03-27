package Modelo;
public class Salchichoneria extends Producto {
    public Salchichoneria(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Salchichonería", imagen, "Refrigerados");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Venta por peso o pieza. Mantener en vitrina fría."; 
    	}
}