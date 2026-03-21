import Controlador.CPrincipal;
import Controlador.InicializadorDatos;
import Modelo.ListaProductos;
import Vista.VistaPrincipal;

public class Main {
    public static void main(String[] args) {
        
        ListaProductos inventario = new ListaProductos();
        
        InicializadorDatos.cargarTodo(inventario); 
        
        VistaPrincipal vista = new VistaPrincipal();
        
        new CPrincipal(vista, inventario); 
        
        vista.setVisible(true);
    }
}