package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VPuntoVenta extends JInternalFrame {
    public JComboBox<String> cbProductos;
    public JTextField txtCantidad, txtTotal;
    public JButton btnAgregar, btnPagar, btnTicket;
    public JTable tablaCarrito;
    public DefaultTableModel modeloCarrito;
    public JLabel lblFotoVenta;

    public VPuntoVenta() {
        super("🛒 CAJA REGISTRADORA", true, true, true, true);
        setSize(1000, 650);
        getContentPane().setBackground(new Color(245, 246, 250));
        setLayout(new BorderLayout(15, 15));

        JPanel pnlNorte = new JPanel(new BorderLayout());
        pnlNorte.setBackground(new Color(44, 62, 80));
        pnlNorte.setPreferredSize(new Dimension(0, 100));

        JPanel pnlInputs = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
        pnlInputs.setOpaque(false);
        pnlInputs.add(new JLabel("<html><font color='white'>ARTÍCULO:</font></html>"));
        cbProductos = new JComboBox<>(new String[]{"--- Seleccione ---"});
        cbProductos.setPreferredSize(new Dimension(300, 30));
        pnlInputs.add(cbProductos);
        
        pnlInputs.add(new JLabel("<html><font color='white'>CANT:</font></html>"));
        txtCantidad = new JTextField(5);
        pnlInputs.add(txtCantidad);
        
        btnAgregar = new JButton("➕ AÑADIR");
        btnAgregar.setBackground(new Color(9, 132, 227));
        btnAgregar.setForeground(Color.WHITE);
        pnlInputs.add(btnAgregar);

        JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        pnlTotal.setOpaque(false);
        txtTotal = new JTextField(8);
        txtTotal.setFont(new Font("Digital-7", Font.BOLD, 40));
        txtTotal.setBackground(Color.BLACK);
        txtTotal.setForeground(Color.GREEN);
        txtTotal.setHorizontalAlignment(JTextField.RIGHT);
        pnlTotal.add(new JLabel("<html><font color='white' size='6'>TOTAL $</font></html>"));
        pnlTotal.add(txtTotal);

        pnlNorte.add(pnlInputs, BorderLayout.WEST);
        pnlNorte.add(pnlTotal, BorderLayout.EAST);

        modeloCarrito = new DefaultTableModel(new String[]{"ID", "PRODUCTO", "CANTIDAD", "SUBTOTAL"}, 0);
        tablaCarrito = new JTable(modeloCarrito);
        tablaCarrito.setFillsViewportHeight(true);
        tablaCarrito.setBackground(Color.WHITE);

        JPanel pnlDerecho = new JPanel();
        pnlDerecho.setLayout(new BoxLayout(pnlDerecho, BoxLayout.Y_AXIS));
        pnlDerecho.setPreferredSize(new Dimension(200, 0));
        pnlDerecho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblFotoVenta = new JLabel("IMAGEN", SwingConstants.CENTER);
        lblFotoVenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFotoVenta.setPreferredSize(new Dimension(150, 150));
        lblFotoVenta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        btnPagar = new JButton("💵 FINALIZAR VENTA");
        btnTicket = new JButton("📄 IMPRIMIR TICKET");
        btnPagar.setMaximumSize(new Dimension(180, 50));
        btnTicket.setMaximumSize(new Dimension(180, 50));
        btnPagar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTicket.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlDerecho.add(lblFotoVenta);
        pnlDerecho.add(Box.createRigidArea(new Dimension(0, 50)));
        pnlDerecho.add(btnPagar);
        pnlDerecho.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlDerecho.add(btnTicket);

        add(pnlNorte, BorderLayout.NORTH);
        add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);
        add(pnlDerecho, BorderLayout.EAST);
    }
}