package Modelo;
public class CuidadoPersonal extends Producto {
    public CuidadoPersonal(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Cuidado Personal", imagen, "Higiene");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Producto de uso personal. Empaque sellado."; 
    	}
}