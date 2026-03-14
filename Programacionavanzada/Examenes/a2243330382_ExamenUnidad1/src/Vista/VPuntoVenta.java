package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VPuntoVenta extends JInternalFrame {
    public VPuntoVenta() {
        super("Punto de Venta", true, true, true, true);
        setSize(950, 550);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlTop = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5); g.anchor = GridBagConstraints.WEST;

        g.gridx = 0; g.gridy = 0; pnlTop.add(new JLabel("ID Cliente/Nombre: Cajero:"), g);
        g.gridy = 1; g.gridwidth = 4; pnlTop.add(new JSeparator(), g);
        
        g.gridwidth = 1; g.gridy = 2; pnlTop.add(new JLabel("SELECCIÓN DE PRODUCTO"), g);
        g.gridx = 0; g.gridy = 3; pnlTop.add(new JComboBox<>(new String[]{"--- Seleccionar ---"}), g);
        g.gridx = 1; pnlTop.add(new JLabel("Cantidad:"), g);
        g.gridx = 2; pnlTop.add(new JTextField(5), g);
        
        JPanel pnlBtns = new JPanel();
        pnlBtns.add(new JButton("Añadir a Carrito"));
        pnlBtns.add(new JButton("Modificar"));
        pnlBtns.add(new JButton("Eliminar"));
        g.gridx = 0; g.gridy = 4; g.gridwidth = 3; pnlTop.add(pnlBtns, g);

        JPanel pnlTabla = new JPanel(new BorderLayout());
        pnlTabla.setBorder(BorderFactory.createTitledBorder("Detalles Transacción Actual"));
        String[] col = {"Cód", "Descrip", "Cant", "P.Unit", "Total"};
        pnlTabla.add(new JScrollPane(new JTable(new DefaultTableModel(col, 0))), BorderLayout.CENTER);

        JPanel pnlInferior = new JPanel(new BorderLayout());
        JPanel pnlTotales = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlTotales.add(new JLabel("Subtotal:")); pnlTotales.add(new JTextField(8));
        pnlTotales.add(new JLabel("IVA:")); pnlTotales.add(new JTextField(8));
        pnlTotales.add(new JLabel("Total a Pagar:")); pnlTotales.add(new JTextField(8));

        JPanel pnlFinal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlFinal.add(new JLabel("Total a Pagar: "));
        pnlFinal.add(new JButton("Limpiar Carrito"));
        pnlFinal.add(new JButton("Procesar Pago"));
        pnlFinal.add(new JButton("Exportar Ticket"));

        pnlInferior.add(pnlTotales, BorderLayout.WEST);
        pnlInferior.add(pnlFinal, BorderLayout.SOUTH);

        add(pnlTop, BorderLayout.NORTH);
        add(pnlTabla, BorderLayout.CENTER);
        add(pnlInferior, BorderLayout.SOUTH);
    }
}