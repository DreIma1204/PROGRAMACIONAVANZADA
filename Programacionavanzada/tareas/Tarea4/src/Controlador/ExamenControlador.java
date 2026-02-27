package Controlador;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import Modelo.Examen;
import Modelo.GestorArchivos;
import Modelo.Pregunta;
import Vista.VentanaExamen;

public class ExamenControlador {
    private VentanaExamen vista;
    private Examen modelo;

    public ExamenControlador(VentanaExamen vista) {
        this.vista = vista;
        
        // Listeners
        this.vista.itemAbrir.addActionListener(e -> abrirArchivo());
        this.vista.btnSiguiente.addActionListener(e -> responder());
        this.vista.itemSalir.addActionListener(e -> System.exit(0));
    }

    private void abrirArchivo() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
            try {
                List<Pregunta> p = GestorArchivos.cargarDesdeCSV(fc.getSelectedFile());
                modelo = new Examen(p);
                modelo.barajar();
                
                // Bloquear menús al iniciar (según instrucción)
                vista.itemAbrir.setEnabled(false);
                vista.menuOpciones.setEnabled(false);
                vista.btnSiguiente.setEnabled(true);
                
                actualizarInterfaz();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
            }
        }
    }

    private void actualizarInterfaz() {
        Pregunta p = modelo.obtenerActual();
        vista.lblEnunciado.setText("Pregunta " + modelo.getNumeroPregunta() + ": " + p.getEnunciado());
        String[] ops = p.getOpciones();
        for (int i = 0; i < 4; i++) {
            vista.rbOpciones[i].setText(ops[i]);
        }
        vista.grupo.clearSelection();
    }

    private void responder() {
        String seleccion = null;
        for (JRadioButton rb : vista.rbOpciones) {
            if (rb.isSelected()) seleccion = rb.getText();
        }

        if (seleccion == null) {
            JOptionPane.showMessageDialog(vista, "Selecciona una opción.");
            return;
        }

        if (modelo.verificarRespuesta(seleccion)) {
            vista.txtConsola.append("Pregunta " + modelo.getNumeroPregunta() + ": Correcto\n");
        } else {
            vista.txtConsola.append("Pregunta " + modelo.getNumeroPregunta() + ": Incorrecto\n");
        }

        if (modelo.siguiente()) {
            actualizarInterfaz();
        } else {
            JOptionPane.showMessageDialog(vista, "Fin. Aciertos: " + modelo.getAciertos() + "/" + modelo.getTotal());
            System.exit(0);
        }
    }
}