package Parte2Vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Parte2Controlador.ListaCategorias;
import Parte2Modelo.Categoria;

public class Practica03_b extends JFrame implements ActionListener {

    // Lógica de datos
    ListaCategorias listacategorias;

    // Componentes visuales
    private JTextField Tid, Tcategoria;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea Tareacategoria;
    private JPanel panelFormulario;

    public Practica03_b() {
        super("Administración de Categorías");
        this.listacategorias = new ListaCategorias();

        // Configuración de la ventana
        setBounds(0, 0, 400, 350);
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        // ID
        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 10, 80, 20);
        Tid = new JTextField();
        Tid.setBounds(100, 10, 150, 20);
        Tid.setEditable(false); // Característica solicitada
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);

        // Categoría
        JLabel labelCat = new JLabel("Categoría:");
        labelCat.setBounds(10, 40, 80, 20);
        Tcategoria = new JTextField();
        Tcategoria.setBounds(100, 40, 150, 20);
        Tcategoria.setEditable(false); // Característica solicitada
        panelFormulario.add(labelCat);
        panelFormulario.add(Tcategoria);

        // Botones
        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(10, 80, 100, 25);
        Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(120, 80, 100, 25);
        Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(230, 80, 100, 25);
        Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);

        // Área de Texto
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 120, 360, 150);
        Tareacategoria = new JTextArea();
        Tareacategoria.setEditable(false); // Característica solicitada
        scrollPane.setViewportView(Tareacategoria);
        panelFormulario.add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void Volveralinicio() {
        Bagregar.setText("Agregar");
        Bsalir.setText("Salir");
        Beliminar.setEnabled(true);
        Tid.setEditable(false);
        Tcategoria.setEditable(false);
        Tid.setText("");
        Tcategoria.setText("");
    }

    public void Altas() {
        if (Bagregar.getText().equals("Agregar")) {
            Bagregar.setText("Salvar");
            Bsalir.setText("Cancelar");
            Beliminar.setEnabled(false);
            Tid.setEditable(true);
            Tcategoria.setEditable(true);
        } else {
            String id = Tid.getText().trim();
            String cat = Tcategoria.getText().trim();
            
            if (!id.isEmpty() && !cat.isEmpty()) {
                Categoria nueva = new Categoria(id, cat);
                listacategorias.agregarCategoria(nueva);
                Tareacategoria.setText(listacategorias.toLinea());
                Volveralinicio();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos.");
            }
        }
    }

    public void Eliminar() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la categoría a eliminar:");
        if (id != null) {
            listacategorias.eliminarCategoriaPorId(id);
            Tareacategoria.setText(listacategorias.toLinea());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Bagregar) {
            Altas();
        } else if (e.getSource() == Beliminar) {
            Eliminar();
        } else if (e.getSource() == Bsalir) {
            if (Bsalir.getText().equals("Cancelar")) {
                Volveralinicio();
            } else {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new Practica03_b();
    }
}