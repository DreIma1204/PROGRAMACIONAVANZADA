package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Modelo.MEquipo;
import Modelo.MEvaluacionRegistro;

public class VPanelEquipos extends JPanel {
    private static final long serialVersionUID = 1L;
    private final DefaultTableModel modelo;
    private final JTable tabla;

    public VPanelEquipos() {
        setLayout(new BorderLayout(4, 4));
        setBorder(new EmptyBorder(8, 0, 0, 0));
        JLabel titulo = new JLabel("Equipos / Calificaciones");
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new Object[]{"Equipo/Alumno", "Calificación"}, 0);
        tabla = new JTable(modelo);
        
        modelo.addTableModelListener(e -> {
            if (e.getColumn() == 1) {
                int fila = e.getFirstRow();
                Object valor = modelo.getValueAt(fila, 1);
                try {
                    Double.parseDouble(valor.toString().replace(',', '.'));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido en la calificación.");
                    modelo.setValueAt("0.0", fila, 1);
                }
            }
        });

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void cargarDesdeRegistro(MEvaluacionRegistro registro) {
        modelo.setRowCount(0);
        for (MEquipo eq : registro.obtenerEquipos()) {
            modelo.addRow(new Object[]{eq.obtenerNombre(), eq.obtenerCalificacion()});
        }
        if (modelo.getRowCount() == 0) {
            for(int i=0; i<4; i++) modelo.addRow(new Object[]{"", 0.0});
        }
    }

    public void volcarARegistro(MEvaluacionRegistro registro) {
        registro.obtenerEquipos().clear();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object n = modelo.getValueAt(i, 0);
            Object c = modelo.getValueAt(i, 1);
            if (n == null || n.toString().isBlank()) continue;
            double cal = 0;
            try { cal = Double.parseDouble(c.toString().replace(',', '.')); } catch (Exception ignored) {}
            registro.obtenerEquipos().add(new MEquipo(n.toString(), cal));
        }
    }
}