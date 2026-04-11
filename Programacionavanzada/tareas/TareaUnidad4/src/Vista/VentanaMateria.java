package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaMateria extends JFrame {

    public JTextField txtId;
    public JTextField txtNombre;
    public JButton btnActualizar;

    public VentanaMateria() {
        setTitle("Gestión de Materias");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("ID Materia:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel("Nuevo Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        btnActualizar = new JButton("Actualizar");
        add(btnActualizar);

        setVisible(true);
    }
}