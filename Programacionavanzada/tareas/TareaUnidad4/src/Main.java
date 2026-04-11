import java.sql.Connection;
import java.util.Map;
import Librerias.ConexionBD;
import Modelo.BaseDatos;
import Modelo.Configurador;
import Vista.VentanaMateria;
import Controlador.MateriaControlador;

public class Main {
    public static void main(String[] args) {
        Map<String, String> conf = Configurador.cargarConfiguracion();
        
        try {
            Connection con = ConexionBD.conectarMySQL(
                conf.get("host"), 
                conf.get("db"), 
                conf.get("user"), 
                conf.get("pass")
            );

            BaseDatos modelo = new BaseDatos(con);
            VentanaMateria vista = new VentanaMateria();
            new MateriaControlador(modelo, vista);

        } catch (Exception e) {
            System.err.println("Error al iniciar aplicación: " + e.getMessage());
        }
    }
}