package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Modelo.MDatosInstrumento;
import Modelo.MEvaluacionRegistro;

public class VPanelProductoIntegrador extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JTextField campoFecha;
    private final JTextField campoCriterios;
    private final JTextArea areaObservaciones;
    private final JTextArea areaProducto;
    private final VPanelEquipos panelEquipos;

    public VPanelProductoIntegrador() {
        setLayout(new BorderLayout(8, 8));
        setBorder(new EmptyBorder(12, 12, 12, 12));
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0; form.add(new JLabel("Fecha:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; campoFecha = new JTextField(); form.add(campoFecha, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; form.add(new JLabel("Criterios (sep. por coma):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; campoCriterios = new JTextField(); form.add(campoCriterios, gbc);

        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Observaciones:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.5;
        areaObservaciones = new JTextArea(); form.add(new JScrollPane(areaObservaciones), gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weighty = 0; form.add(new JLabel("Producto Integrador:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
        areaProducto = new JTextArea(); form.add(new JScrollPane(areaProducto), gbc);

        add(form, BorderLayout.CENTER);
        panelEquipos = new VPanelEquipos();
        add(panelEquipos, BorderLayout.SOUTH);
    }

    public JTextField obtenerCampoCriterios() {
        return campoCriterios;
    }

    public void cargarDesdeRegistro(MEvaluacionRegistro registro) {
        MDatosInstrumento d = registro.obtenerDatosInstrumento();
        campoFecha.setText(d.obtenerFecha());
        campoCriterios.setText(d.obtenerCriterios());
        areaObservaciones.setText(d.obtenerObservaciones());
        areaProducto.setText(registro.obtenerTextoProductoIntegrador());
        panelEquipos.cargarDesdeRegistro(registro);
    }

    public void volcarARegistro(MEvaluacionRegistro registro) {
        MDatosInstrumento d = registro.obtenerDatosInstrumento();
        d.establecerFecha(campoFecha.getText());
        d.establecerCriterios(campoCriterios.getText());
        d.establecerObservaciones(areaObservaciones.getText());
        registro.establecerTextoProductoIntegrador(areaProducto.getText());
        panelEquipos.volcarARegistro(registro);
    }
}