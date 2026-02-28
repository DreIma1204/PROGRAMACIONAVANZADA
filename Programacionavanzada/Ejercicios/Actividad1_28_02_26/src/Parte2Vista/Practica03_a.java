package Parte2Vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Parte2Controlador.ListaCategorias;
import Parte2Controlador.ListaInsumos;
import Parte2Modelo.Categoria;
import Parte2Modelo.Insumo;

public class Practica03_a extends JFrame implements ActionListener {

    ListaInsumos listainsumo;
    ListaCategorias listacategorias;

    private JComboBox ComboCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea areaProductos;
    private JPanel panelFormulario;

    public Practica03_a() {
        super("Administración de Productos");
        
        this.listacategorias = new ListaCategorias();
        this.listainsumo = new ListaInsumos();
        inicializarcategorias();

        setBounds(0, 0, 390, 370);
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        this.Tid = new JTextField(10);
        this.Tid.setEditable(false);
        this.Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);

        JLabel labelInsumo = new JLabel("Insumo:");
        labelInsumo.setBounds(10, 34, 71, 20);
        this.Tinsumo = new JTextField(20);
        this.Tinsumo.setEditable(false);
        this.Tinsumo.setBounds(91, 35, 147, 20);
        panelFormulario.add(labelInsumo);
        panelFormulario.add(Tinsumo);

        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(10, 66, 71, 20);
        ComboCategoria = new JComboBox(this.listacategorias.CategoriasArreglo());
        ComboCategoria.setEditable(false);
        ComboCategoria.setBounds(91, 66, 160, 20);
        ComboCategoria.addActionListener(this);
        panelFormulario.add(labelCategoria);
        panelFormulario.add(ComboCategoria);

        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(20, 104, 111, 20);
        Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(153, 104, 111, 20);
        Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(274, 104, 79, 20);
        Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 132, 357, 179);
        this.areaProductos = new JTextArea(10, 40);
        this.areaProductos.setEditable(false);
        scrollPane.setViewportView(areaProductos);
        panelFormulario.add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void inicializarcategorias() {
        this.listacategorias.agregarCategoria(new Categoria("01", "Materiales"));
        this.listacategorias.agregarCategoria(new Categoria("02", "Mano de Obra"));
        this.listacategorias.agregarCategoria(new Categoria("03", "Maquinaria y Equipo"));
        this.listacategorias.agregarCategoria(new Categoria("04", "Servicios"));
    }

    public void Volveralinicio() {
        this.Bagregar.setText("Agregar");
        this.Bsalir.setText("Salir");
        this.Beliminar.setEnabled(true);
        this.Tid.setEditable(false);
        this.Tinsumo.setEditable(false);
        this.ComboCategoria.setEnabled(false);
        this.Tid.setText("");
        this.Tinsumo.setText("");
        this.ComboCategoria.setSelectedIndex(0);
    }

    public void Altas() {
        if (this.Bagregar.getText().compareTo("Agregar") == 0) {
           
            this.Bagregar.setText("Salvar");
            this.Bsalir.setText("Cancelar");
            this.Beliminar.setEnabled(false);
            this.Tid.setEditable(true);
            this.Tinsumo.setEditable(true);
            this.ComboCategoria.setEnabled(true);
            this.ComboCategoria.setFocusable(true);
        } else {
            String id, insumo, idcategoria;
            id = this.Tid.getText().trim();
            insumo = this.Tinsumo.getText().trim();
            idcategoria = ((Categoria)this.ComboCategoria.getSelectedItem()).getIdcategoria().trim();
            
            Insumo nodo = new Insumo(id, insumo, idcategoria);
            
            if (!this.listainsumo.agregarInsumo(nodo)) {
                JOptionPane.showMessageDialog(this, "Lo siento, el id " + id + " ya existe asignado a " + this.listainsumo.buscarInsumo(id));
            } else {
                this.areaProductos.setText(this.listainsumo.toString());
            }
            Volveralinicio();
        }
    }

    public void Eliminar() {
        Object[] opciones = this.listainsumo.idinsumos();
        if (opciones.length > 0) {
            String id = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Eliminación de Insumos", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            
            if (id != null && !id.isEmpty()) {
                if (!this.listainsumo.eliminarInsumoPorId(id)) {
                    JOptionPane.showMessageDialog(this, "No existe este id");
                } else {
                    this.areaProductos.setText(this.listainsumo.toString());
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Bagregar) {
            this.Altas();
        } else if (e.getSource() == this.Beliminar) {
            this.Eliminar();
        } else if (e.getSource() == Bsalir) {
            if (this.Bsalir.getText().compareTo("Cancelar") == 0) {
                this.Volveralinicio();
            } else {
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        new Practica03_a();
    }
}