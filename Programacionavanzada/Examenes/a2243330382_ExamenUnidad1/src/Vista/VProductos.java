package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VProductos extends JInternalFrame {
    public JTextField txtId, txtNombre, txtPrecioC, txtPrecioV, txtStockI, txtStockM;
    public JTextArea txtDesc;
    public JComboBox<String> cbCat;
    public JRadioButton rbActivo, rbDesactivo;
    public JButton btnGuardar, btnLimpiar, btnBuscar, btnMostrar, btnExportar;
    public JTable tabla;
    public DefaultTableModel modelo;

    public VProductos() {
        super("Productos", true, true, true, true);
        setSize(1000, 600);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlAlta = new JPanel(new GridBagLayout());
        pnlAlta.setBorder(BorderFactory.createTitledBorder("Alta y Edición"));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(2, 5, 2, 5); g.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField("AUTOGEN", 10); txtId.setEnabled(false);
        txtNombre = new JTextField(10);
        txtDesc = new JTextArea(3, 10);
        cbCat = new JComboBox<>(new String[]{"Seleccionar..."});
        txtPrecioC = new JTextField(10); txtPrecioV = new JTextField(10);
        txtStockI = new JTextField(10); txtStockM = new JTextField(10);

        addComp(pnlAlta, new JLabel("ID [Auto]:"), 0, 0, g); addComp(pnlAlta, txtId, 1, 0, g);
        addComp(pnlAlta, new JLabel("Nombre Producto:"), 0, 1, g); addComp(pnlAlta, txtNombre, 1, 1, g);
        addComp(pnlAlta, new JLabel("Descripción:"), 0, 2, g); 
        g.gridx = 1; g.gridy = 2; pnlAlta.add(new JScrollPane(txtDesc), g);
        addComp(pnlAlta, new JLabel("Categoría:"), 0, 3, g); addComp(pnlAlta, cbCat, 1, 3, g);
        addComp(pnlAlta, new JLabel("Precio Compra:"), 0, 4, g); addComp(pnlAlta, txtPrecioC, 1, 4, g);
        addComp(pnlAlta, new JLabel("Precio Venta:"), 0, 5, g); addComp(pnlAlta, txtPrecioV, 1, 5, g);
        addComp(pnlAlta, new JLabel("Stock Inicial:"), 0, 6, g); addComp(pnlAlta, txtStockI, 1, 6, g);
        addComp(pnlAlta, new JLabel("Stock Mínimo:"), 0, 7, g); addComp(pnlAlta, txtStockM, 1, 7, g);

        JPanel pnlEst = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlEst.setBorder(BorderFactory.createTitledBorder("Estado Actual"));
        rbActivo = new JRadioButton("Activo", true); rbDesactivo = new JRadioButton("Desactivado");
        ButtonGroup bg = new ButtonGroup(); bg.add(rbActivo); bg.add(rbDesactivo);
        pnlEst.add(rbActivo); pnlEst.add(rbDesactivo);
        g.gridx=0; g.gridy=8; g.gridwidth=2; pnlAlta.add(pnlEst, g);

        btnGuardar = new JButton("Guardar Cambios");
        btnLimpiar = new JButton("Limpiar Formulario");
        JPanel pB = new JPanel(); pB.add(btnGuardar); pB.add(btnLimpiar);
        g.gridy=9; pnlAlta.add(pB, g);

        JPanel pnlDer = new JPanel(new BorderLayout());
        pnlDer.setBorder(BorderFactory.createTitledBorder("Catálogo de Productos"));
        modelo = new DefaultTableModel(new String[]{"ID", "Código", "Nombre", "Categoría", "Stock", "P.Venta", "Estado"}, 0);
        tabla = new JTable(modelo);
        pnlDer.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pnlBusq = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBusq.setBorder(BorderFactory.createTitledBorder("Acciones y Filtros"));
        btnBuscar = new JButton("Buscar"); btnMostrar = new JButton("Mostrar Todos"); btnExportar = new JButton("Exportar Lista");
        pnlBusq.add(new JLabel("Buscar por:")); pnlBusq.add(new JTextField(8)); pnlBusq.add(btnBuscar);
        pnlBusq.add(btnMostrar); pnlBusq.add(btnExportar);
        pnlDer.add(pnlBusq, BorderLayout.SOUTH);

        add(pnlAlta, BorderLayout.WEST);
        add(pnlDer, BorderLayout.CENTER);
    }
    private void addComp(JPanel p, Component c, int x, int y, GridBagConstraints g) {
        g.gridx = x; g.gridy = y; g.gridwidth = 1; p.add(c, g);
    }
}