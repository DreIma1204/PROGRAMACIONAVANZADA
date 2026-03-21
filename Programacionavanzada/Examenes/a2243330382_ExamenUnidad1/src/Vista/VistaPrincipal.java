package Vista;
import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {
    public JDesktopPane desktop;
    public JMenuItem itemInventario, itemPuntoVenta, itemProductos;

    public VistaPrincipal() {
        setTitle("SISTEMA POS - CORE V1.0"); 
        setSize(1300, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        desktop.setBackground(new Color(24, 28, 31)); 
        setContentPane(desktop);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(45, 52, 54));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(108, 121, 123)));

        JMenu menuModulos = new JMenu("☰ PANEL DE CONTROL");
        menuModulos.setForeground(new Color(0, 206, 201)); 
        menuModulos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        itemInventario = new JMenuItem("📦 Gestión de Inventario");
        itemPuntoVenta = new JMenuItem("🛒 Terminal de Ventas");
        itemProductos = new JMenuItem("🏷️ Maestro de Productos");

        itemInventario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemPuntoVenta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemProductos.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        menuModulos.add(itemInventario);
        menuModulos.addSeparator();
        menuModulos.add(itemPuntoVenta);
        menuModulos.addSeparator();
        menuModulos.add(itemProductos);
        
        menuBar.add(menuModulos);
        setJMenuBar(menuBar);
    }
}