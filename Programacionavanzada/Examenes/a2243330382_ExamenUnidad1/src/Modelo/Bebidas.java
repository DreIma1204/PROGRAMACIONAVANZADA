package Modelo;
public class Bebidas extends Producto {
    public Bebidas(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Bebidas", imagen, "Líquidos / Pesado");
    }
    @Override
    public String getDetallesEspeciales() { 
    	return "Envase frágil o de alto peso. Manejar con cuidado."; 
    	}
}