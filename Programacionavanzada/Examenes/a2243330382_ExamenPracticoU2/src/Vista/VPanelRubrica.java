package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import Modelo.MEvaluacionRegistro;
import Modelo.MFilaRubrica;

public class VPanelRubrica extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JTable tabla;
    private final DefaultTableModel modelo;
    private boolean isRecalculating = false;

    public VPanelRubrica() {
        setLayout(new BorderLayout(8, 8));
        setBorder(new EmptyBorder(12, 12, 12, 12));
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != getColumnCount() - 1;
            }
        };
        
        tabla = new JTable(modelo);
        tabla.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        tabla.setRowHeight(25);
        
        modelo.addTableModelListener(e -> {
            if (isRecalculating || e.getType() != TableModelEvent.UPDATE) return;
            
            int fila = e.getFirstRow();
            int col = e.getColumn();
            int totalCols = modelo.getColumnCount();
            
            if (col > 0 && col < totalCols - 1 && fila != -1) {
                recalcularPromedioFila(fila);
            }
        });
        
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void recalcularPromedioFila(int fila) {
        isRecalculating = true;
        try {
            double suma = 0;
            int cont = 0;
            int totalCols = modelo.getColumnCount();
            
            for (int j = 1; j < totalCols - 1; j++) {
                Object valor = modelo.getValueAt(fila, j);
                if (valor != null) {
                    try {
                        double val = Double.parseDouble(valor.toString().replace(',', '.'));
                        suma += val;
                        cont++;
                    } catch (NumberFormatException ignored) {}
                }
            }
            
            double promedio = (cont > 0) ? suma / cont : 0.0;
            modelo.setValueAt(promedio, fila, totalCols - 1);
        } finally {
            isRecalculating = false;
        }
    }

    public void actualizarColumnasPorCriterios(String textoCriterios) {
        String[] criterios = textoCriterios.split(",");
        Object[][] datosActuales = extraerDatos();
        
        isRecalculating = true;
        modelo.setColumnCount(0);
        modelo.addColumn("Alumno");
        for (String c : criterios) {
            if (!c.trim().isEmpty()) modelo.addColumn(c.trim());
        }
        modelo.addColumn("Promedio");
        
        for (Object[] d : datosActuales) {
            Object[] nuevaFila = new Object[modelo.getColumnCount()];
            nuevaFila[0] = d[0];
            for (int i = 1; i < nuevaFila.length; i++) {
                if (i < d.length) nuevaFila[i] = d[i];
                else nuevaFila[i] = 0.0;
            }
            modelo.addRow(nuevaFila);
        }
        
        if (modelo.getRowCount() == 0) {
            for (int i = 0; i < 4; i++) {
                Object[] filaBlanca = new Object[modelo.getColumnCount()];
                filaBlanca[0] = "";
                for (int j = 1; j < filaBlanca.length; j++) filaBlanca[j] = 0.0;
                modelo.addRow(filaBlanca);
            }
        }
        
        tabla.getColumnModel().getColumn(modelo.getColumnCount() - 1).setCellRenderer(new VRenderizadorTablaRubrica(modelo.getColumnCount() - 1));
        isRecalculating = false;
    }

    private Object[][] extraerDatos() {
        int rows = modelo.getRowCount();
        int cols = modelo.getColumnCount();
        if (cols == 0) return new Object[0][0];
        Object[][] datos = new Object[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                datos[i][j] = modelo.getValueAt(i, j);
            }
        }
        return datos;
    }

    public void cargarDesdeRegistro(MEvaluacionRegistro registro) {
        isRecalculating = true;
        modelo.setRowCount(0);
        String criteriosStr = registro.obtenerDatosInstrumento().obtenerCriterios();
        if (criteriosStr.isBlank()) criteriosStr = "Criterio 1, Criterio 2";
        
        actualizarColumnasPorCriterios(criteriosStr);
        modelo.setRowCount(0);
        
        for (MFilaRubrica fr : registro.obtenerFilasRubrica()) {
            Object[] fila = new Object[modelo.getColumnCount()];
            fila[0] = fr.obtenerNombreAlumno();
            List<Double> cals = fr.obtenerCalificacionesCriterios();
            for (int i = 0; i < cals.size() && (i + 1) < fila.length - 1; i++) {
                fila[i + 1] = cals.get(i);
            }
            fila[fila.length - 1] = fr.obtenerPromedio();
            modelo.addRow(fila);
        }
        
        if (modelo.getRowCount() == 0) {
             for (int i = 0; i < 4; i++) {
                Object[] filaBlanca = new Object[modelo.getColumnCount()];
                filaBlanca[0] = "";
                for (int j = 1; j < filaBlanca.length; j++) filaBlanca[j] = 0.0;
                modelo.addRow(filaBlanca);
            }
        }
        isRecalculating = false;
    }

    public void volcarARegistro(MEvaluacionRegistro registro) {
        registro.obtenerFilasRubrica().clear();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String alumno = (String) modelo.getValueAt(i, 0);
            if (alumno == null || alumno.isBlank()) continue;
            
            MFilaRubrica fr = new MFilaRubrica();
            fr.establecerNombreAlumno(alumno);
            List<Double> cals = new ArrayList<>();
            for (int j = 1; j < modelo.getColumnCount() - 1; j++) {
                double val = 0;
                try {
                    val = Double.parseDouble(modelo.getValueAt(i, j).toString().replace(',', '.'));
                } catch (Exception ignored) {}
                cals.add(val);
            }
            fr.establecerCalificacionesCriterios(cals);
            try {
                fr.establecerPromedio(Double.parseDouble(modelo.getValueAt(i, modelo.getColumnCount() - 1).toString()));
            } catch (Exception e) {
                fr.establecerPromedio(0.0);
            }
            registro.obtenerFilasRubrica().add(fr);
        }
    }
}