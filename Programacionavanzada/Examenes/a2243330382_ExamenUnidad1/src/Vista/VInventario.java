package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VInventario extends JInternalFrame {
    public JTextField txtId, txtNombre;
    public JComboBox<String> cbTipo;
    public JRadioButton rbTodos, rbDisponible, rbAgotado;
    public JButton btnBuscar, btnLimpiar, btnCrear, btnModificar, btnEliminar;
    public JTable tabla;
    public DefaultTableModel modelo;

    public VInventario() {
        super("Inventario", true, true, true, true);
        setSize(900, 500);
        setLayout(new BorderLayout(5, 5));

        JPanel pnlFiltros = new JPanel(new GridBagLayout());
        pnlFiltros.setBorder(BorderFactory.createTitledBorder("Filtros y Búsqueda"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); gbc.fill = GridBagConstraints.HORIZONTAL; gbc.gridx = 0;

        txtId = new JTextField(15);
        txtNombre = new JTextField(15);
        cbTipo = new JComboBox<>(new String[]{"Seleccionar..."});
        
        gbc.gridy = 0; pnlFiltros.add(new JLabel("ID:"), gbc);
        gbc.gridy = 1; pnlFiltros.add(txtId, gbc);
        gbc.gridy = 2; pnlFiltros.add(new JLabel("Nombre:"), gbc);
        gbc.gridy = 3; pnlFiltros.add(txtNombre, gbc);
        gbc.gridy = 4; pnlFiltros.add(new JLabel("Tipo:"), gbc);
        gbc.gridy = 5; pnlFiltros.add(cbTipo, gbc);

        JPanel pnlEstado = new JPanel(new GridLayout(3, 1));
        pnlEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
        rbTodos = new JRadioButton("Todos");
        rbDisponible = new JRadioButton("Disponible", true);
        rbAgotado = new JRadioButton("Agotado");
        ButtonGroup bg = new ButtonGroup(); bg.add(rbTodos); bg.add(rbDisponible); bg.add(rbAgotado);
        pnlEstado.add(rbTodos); pnlEstado.add(rbDisponible); pnlEstado.add(rbAgotado);
        gbc.gridy = 6; pnlFiltros.add(pnlEstado, gbc);

        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar Filtros");
        JPanel pnlBtnsF = new JPanel(); pnlBtnsF.add(btnBuscar); pnlBtnsF.add(btnLimpiar);
        gbc.gridy = 7; pnlFiltros.add(pnlBtnsF, gbc);

        JPanel pnlDerecha = new JPanel(new BorderLayout());
        pnlDerecha.setBorder(BorderFactory.createTitledBorder("Vista de Inventario"));
        
        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Tipo", "Cantidad", "Precio", "Estado"}, 0);
        tabla = new JTable(modelo);
        pnlDerecha.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pnlAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlAcciones.setBorder(BorderFactory.createTitledBorder("Acciones de Selección"));
        btnCrear = new JButton("Crear Nuevo");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        pnlAcciones.add(btnCrear); pnlAcciones.add(btnModificar); pnlAcciones.add(btnEliminar);
        pnlDerecha.add(pnlAcciones, BorderLayout.SOUTH);

        add(pnlFiltros, BorderLayout.WEST);
        add(pnlDerecha, BorderLayout.CENTER);
    }
}