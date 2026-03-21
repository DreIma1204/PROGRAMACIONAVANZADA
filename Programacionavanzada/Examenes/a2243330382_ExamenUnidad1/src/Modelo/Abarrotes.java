package Modelo;
public class Abarrotes extends Producto {
    public Abarrotes(String id, String nombre, double precio, int stock, String imagen) {
        super(id, nombre, precio, stock, "Abarrotes", imagen, "No perecedero (Anaquel)");
    }
    @Override
    public String getDetallesEspeciales() 
    { return "Producto seco. Almacenar en lugar libre de humedad."; 
    }
}