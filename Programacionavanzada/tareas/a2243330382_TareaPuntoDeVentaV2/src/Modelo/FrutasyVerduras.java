package Modelo;
public class FrutasyVerduras extends Producto {
    public FrutasyVerduras(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Frutas y Verduras", imagen, "Perecedero (Peso variable)");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Producto fresco del día. Revisar madurez."; 
    	}
}