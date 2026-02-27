package Vista;
import javax.swing.*;
import java.awt.*;

public class VentanaExamen extends JFrame {
    public JLabel lblEnunciado = new JLabel("Cargue el archivo CSV para iniciar");
    public JRadioButton[] rbOpciones = new JRadioButton[4];
    public ButtonGroup grupo = new ButtonGroup();
    public JButton btnSiguiente = new JButton("Siguiente");
    public JMenuItem itemAbrir = new JMenuItem("Abrir (.csv)");
    public JMenuItem itemSalir = new JMenuItem("Salir");
    public JMenu menuOpciones = new JMenu("Opciones");
    public JTextArea txtConsola = new JTextArea(5, 20);

    public VentanaExamen() {
        setTitle("Examen Multiple Choice");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar mb = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.add(itemAbrir);
        menuArchivo.add(itemSalir);
        mb.add(menuArchivo);
        mb.add(menuOpciones);
        setJMenuBar(mb);

        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));
        panel.add(lblEnunciado);
        for (int i = 0; i < 4; i++) {
            rbOpciones[i] = new JRadioButton();
            grupo.add(rbOpciones[i]);
            panel.add(rbOpciones[i]);
        }
        panel.add(btnSiguiente);
        
        add(panel, BorderLayout.CENTER);
        add(new JScrollPane(txtConsola), BorderLayout.SOUTH);
        
        btnSiguiente.setEnabled(false);
    }
}