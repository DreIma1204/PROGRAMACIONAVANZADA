package Modelo;

public class MProductos {
    private String id, codigo, nombre, descripcion, categoria;
    private int stock;
    private double precioVenta;
    private boolean estado;

    public MProductos(String id, String codigo, String nombre, String descripcion, String categoria, int stock, double precioVenta, boolean estado) {
        this.id = id; this.codigo = codigo; this.nombre = nombre; this.descripcion = descripcion;
        this.categoria = categoria; this.stock = stock; this.precioVenta = precioVenta; this.estado = estado;
    }

    public String getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public int getStock() { return stock; }
    public double getPrecioVenta() { return precioVenta; }
    public boolean isEstado() { return estado; }
}