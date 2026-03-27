package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VProductos extends JInternalFrame {
    public JTextField txtId, txtNombre, txtPrecioVenta, txtStock;
    public JTextArea txtDescripcion;
    public JComboBox<String> cbCategoria;
    public JButton btnGuardar, btnLimpiar;
    public JTable tabla;
    public DefaultTableModel modelo;
    public JLabel lblImagen; 
    public JTextArea txtDetallesEspeciales;

    public VProductos() {
        super(".: Maestro de Artículos :.", true, true, true, true);
        setSize(1150, 650);
        getContentPane().setBackground(new Color(236, 240, 241));
        setLayout(new BorderLayout(15, 15));

        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.setOpaque(false);
        pnlCentro.setBorder(BorderFactory.createTitledBorder("REGISTROS EXISTENTES"));
        
        modelo = new DefaultTableModel(new String[]{"CÓDIGO", "DESCRIPCIÓN", "CATEGORÍA", "EXISTENCIA", "PRECIO"}, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setBackground(new Color(52, 73, 94));
        tabla.getTableHeader().setForeground(Color.WHITE);
        pnlCentro.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pnlDerecho = new JPanel(new GridBagLayout());
        pnlDerecho.setPreferredSize(new Dimension(380, 0));
        pnlDerecho.setBackground(Color.WHITE);
        pnlDerecho.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(189, 195, 199)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(); txtId.setEditable(false); txtId.setBackground(new Color(236, 240, 241));
        txtNombre = new JTextField();
        cbCategoria = new JComboBox<>(new String[]{"Seleccionar...", "Abarrotes", "Bebidas", "Lácteos y Huevo", "Frutas y Verduras", "Carnes y Pescado", "Salchichonería", "Panadería", "Limpieza", "Cuidado Personal", "Snacks", "Mascotas"});
        txtPrecioVenta = new JTextField();
        txtStock = new JTextField();

        gbc.gridx = 0; gbc.gridy = 0; pnlDerecho.add(new JLabel("ID SISTEMA:"), gbc);
        gbc.gridx = 1; pnlDerecho.add(txtId, gbc);
        gbc.gridx = 0; gbc.gridy = 1; pnlDerecho.add(new JLabel("PRODUCTO:"), gbc);
        gbc.gridx = 1; pnlDerecho.add(txtNombre, gbc);
        gbc.gridx = 0; gbc.gridy = 2; pnlDerecho.add(new JLabel("GRUPO:"), gbc);
        gbc.gridx = 1; pnlDerecho.add(cbCategoria, gbc);
        gbc.gridx = 0; gbc.gridy = 3; pnlDerecho.add(new JLabel("PRECIO ($):"), gbc);
        gbc.gridx = 1; pnlDerecho.add(txtPrecioVenta, gbc);
        gbc.gridx = 0; gbc.gridy = 4; pnlDerecho.add(new JLabel("STOCK:"), gbc);
        gbc.gridx = 1; pnlDerecho.add(txtStock, gbc);

        btnGuardar = new JButton("CONFIRMAR REGISTRO");
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBackground(new Color(231, 76, 60));
        btnLimpiar.setForeground(Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        pnlDerecho.add(btnGuardar, gbc);
        gbc.gridy = 6; pnlDerecho.add(btnLimpiar, gbc);

        JPanel pnlInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        pnlInferior.setPreferredSize(new Dimension(0, 200));
        pnlInferior.setBackground(new Color(52, 73, 94));

        lblImagen = new JLabel("VISUALIZACIÓN DE IMAGEN", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(180, 180));
        lblImagen.setOpaque(true);
        lblImagen.setBackground(Color.DARK_GRAY);
        lblImagen.setForeground(Color.WHITE);
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.CYAN));

        txtDetallesEspeciales = new JTextArea(8, 40);
        txtDetallesEspeciales.setBackground(new Color(44, 62, 80));
        txtDetallesEspeciales.setForeground(new Color(241, 196, 15)); 
        txtDetallesEspeciales.setEditable(false);
        txtDetallesEspeciales.setBorder(BorderFactory.createTitledBorder(null, "DATOS TÉCNICOS", 0, 0, null, Color.WHITE));

        pnlInferior.add(lblImagen);
        pnlInferior.add(new JScrollPane(txtDetallesEspeciales));

        add(pnlCentro, BorderLayout.CENTER);
        add(pnlDerecho, BorderLayout.EAST);
        add(pnlInferior, BorderLayout.SOUTH);
    }
}