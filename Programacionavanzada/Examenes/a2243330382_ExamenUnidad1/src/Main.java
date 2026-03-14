import Vista.VistaPrincipal;
import Controlador.CPrincipal;

public class Main {
    public static void main(String[] args) {
        VistaPrincipal vista = new VistaPrincipal();
        new CPrincipal(vista); 
        vista.setVisible(true);
    }
}