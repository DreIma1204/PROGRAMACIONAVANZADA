package Vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Utilidades.UUmbralCalificacion;

public class VRenderizadorTablaRubrica extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    private final int indiceColumnaPromedio;

    public VRenderizadorTablaRubrica(int indiceColumnaPromedio) {
        this.indiceColumnaPromedio = indiceColumnaPromedio;
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccionado, boolean foco,
            int fila, int columna) {
        super.getTableCellRendererComponent(tabla, valor, seleccionado, foco, fila, columna);
        if (!seleccionado) {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
        if (columna == indiceColumnaPromedio && valor != null) {
            try {
                double p = Double.parseDouble(valor.toString().replace(',', '.'));
                if (UUmbralCalificacion.esReprobatorio(p)) {
                    setBackground(new Color(255, 200, 200));
                    setForeground(new Color(120, 0, 0));
                } else if (!seleccionado) {
                    setBackground(new Color(220, 245, 220));
                    setForeground(Color.BLACK);
                }
            } catch (NumberFormatException ex) {
                setBackground(Color.WHITE);
            }
        }
        return this;
    }
}
