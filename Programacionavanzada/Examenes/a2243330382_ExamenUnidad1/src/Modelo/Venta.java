package Modelo;
import java.util.*;
import java.io.*;
public class Venta {
	private String idVenta;
	private ArrayList<MProductos> productosVendidos;
	
	public Venta(String idVenta) {
		this.idVenta = idVenta;
		this.productosVendidos = new ArrayList<>();
	}
 
	public String getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(String idVenta) {
		this.idVenta = idVenta;
	}

	public ArrayList<MProductos> getProductosVendidos() {
		return productosVendidos;
	}

	public void setProductosVendidos(ArrayList<MProductos> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}
	
	public void agregarProducto(MProductos producto) {
		productosVendidos.add(producto);
	}
	
	public void mostrarVenta() {
		System.out.println("ID Venta: " + idVenta);
		System.out.println("Productos Vendidos:");
		for (MProductos p : productosVendidos) {
			System.out.println("  - " + p.getNombre() + " | Precio: " + p.getPrecioVenta() + " | Cantidad: " + p.getStock());
		}
	}
}
