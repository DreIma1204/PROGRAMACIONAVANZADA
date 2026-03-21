package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VInventario extends JInternalFrame {
    public JTextField txtBusqueda;
    public JComboBox<String> cbCategoriaFiltro;
    public JButton btnFiltrar, btnLimpiar, btnReporteCategoria, btnExportarExcel;
    public JRadioButton rbTodos, rbBajoStock, rbAgotado;
    public JTable tabla;
    public DefaultTableModel modelo;
    public JLabel lblImagenInventario;
    public JTextArea txtInfoDetallada;

    public VInventario() {
        super("📊 MÓDULO DE INTELIGENCIA DE INVENTARIO", true, true, true, true);
        setSize(1150, 700);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new BorderLayout(15, 15));

        // --- PANEL NORTE: FILTROS ---
        JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        pnlNorte.setBackground(new Color(45, 45, 48));
        pnlNorte.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 122, 204)));

        pnlNorte.add(new JLabel("<html><font color='white'>🔎 BUSCAR:</font></html>"));
        txtBusqueda = new JTextField(15);
        txtBusqueda.setBackground(new Color(63, 63, 70));
        txtBusqueda.setForeground(Color.WHITE);
        pnlNorte.add(txtBusqueda);

        pnlNorte.add(new JLabel("<html><font color='white'>CATEGORÍA:</font></html>"));
        cbCategoriaFiltro = new JComboBox<>(new String[]{
            "Todas", "Abarrotes", "Bebidas", "Lácteos y Huevo", "Frutas y Verduras", 
            "Carnes y Pescado", "Salchichonería", "Panadería", "Limpieza", 
            "Cuidado Personal", "Snacks", "Mascotas"
        });
        pnlNorte.add(cbCategoriaFiltro);

        btnFiltrar = new JButton("FILTRAR");
        btnFiltrar.setBackground(new Color(0, 122, 204));
        btnFiltrar.setForeground(Color.WHITE);
        
        btnLimpiar = new JButton("RESETEAR");
        btnLimpiar.setBackground(new Color(80, 80, 80));
        btnLimpiar.setForeground(Color.WHITE);
        
        pnlNorte.add(btnFiltrar);
        pnlNorte.add(btnLimpiar);

        // --- PANEL OESTE: REPORTES Y ESTADO ---
        JPanel pnlOeste = new JPanel();
        pnlOeste.setLayout(new BoxLayout(pnlOeste, BoxLayout.Y_AXIS));
        pnlOeste.setBackground(new Color(37, 37, 38));
        pnlOeste.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        pnlOeste.setPreferredSize(new Dimension(230, 0));

        JLabel lblTit = new JLabel("REPORTES Y STOCK");
        lblTit.setForeground(Color.LIGHT_GRAY);
        lblTit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pnlOeste.add(lblTit);
        pnlOeste.add(Box.createRigidArea(new Dimension(0, 15)));

        rbTodos = new JRadioButton("<html><font color='white'>Ver Todo</font></html>", true);
        rbBajoStock = new JRadioButton("<html><font color='yellow'>⚠️ Stock Bajo</font></html>");
        rbAgotado = new JRadioButton("<html><font color='red'>❌ Agotado</font></html>");
        rbTodos.setOpaque(false); rbBajoStock.setOpaque(false); rbAgotado.setOpaque(false);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbTodos); bg.add(rbBajoStock); bg.add(rbAgotado);
        pnlOeste.add(rbTodos); pnlOeste.add(rbBajoStock); pnlOeste.add(rbAgotado);

        pnlOeste.add(Box.createVerticalGlue());

        // BOTÓN 1: REPORTE POR CATEGORÍA
        btnReporteCategoria = new JButton("📂 REPORTE CATEGORÍA");
        btnReporteCategoria.setMaximumSize(new Dimension(210, 45));
        btnReporteCategoria.setBackground(new Color(52, 152, 219));
        btnReporteCategoria.setForeground(Color.WHITE);
        pnlOeste.add(btnReporteCategoria);

        pnlOeste.add(Box.createRigidArea(new Dimension(0, 10)));

        // BOTÓN 2: REPORTE GENERAL
        btnExportarExcel = new JButton("📤 REPORTE GENERAL");
        btnExportarExcel.setMaximumSize(new Dimension(210, 45));
        btnExportarExcel.setBackground(new Color(33, 115, 70));
        btnExportarExcel.setForeground(Color.WHITE);
        pnlOeste.add(btnExportarExcel);

        // --- TABLA Y VISOR (IDEM ANTERIOR PERO OSCURO) ---
        modelo = new DefaultTableModel(new String[]{"ID", "NOMBRE", "CATEGORÍA", "STOCK", "PRECIO", "ESTADO"}, 0);
        tabla = new JTable(modelo);
        tabla.setBackground(new Color(30, 30, 30));
        tabla.setForeground(Color.WHITE);
        tabla.setRowHeight(25);
        
        JPanel pnlVisor = new JPanel(new BorderLayout(5, 10));
        pnlVisor.setBackground(new Color(37, 37, 38));
        pnlVisor.setPreferredSize(new Dimension(280, 0));

        lblImagenInventario = new JLabel("FOTO", SwingConstants.CENTER);
        lblImagenInventario.setOpaque(true);
        lblImagenInventario.setBackground(Color.BLACK);
        lblImagenInventario.setPreferredSize(new Dimension(220, 220));
        lblImagenInventario.setBorder(BorderFactory.createLineBorder(new Color(0, 122, 204)));
        pnlVisor.add(lblImagenInventario, BorderLayout.NORTH);

        txtInfoDetallada = new JTextArea();
        txtInfoDetallada.setBackground(new Color(25, 25, 25));
        txtInfoDetallada.setForeground(new Color(0, 255, 100));
        txtInfoDetallada.setFont(new Font("Monospaced", Font.PLAIN, 12));
        pnlVisor.add(new JScrollPane(txtInfoDetallada), BorderLayout.CENTER);

        add(pnlNorte, BorderLayout.NORTH);
        add(pnlOeste, BorderLayout.WEST);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(pnlVisor, BorderLayout.EAST);
    }
}