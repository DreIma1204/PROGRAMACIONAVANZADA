import Controlador.ExamenControlador;
import Vista.VentanaExamen;

public class Main {
    public static void main(String[] args) {
        VentanaExamen vista = new VentanaExamen();
        new ExamenControlador(vista);
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
}