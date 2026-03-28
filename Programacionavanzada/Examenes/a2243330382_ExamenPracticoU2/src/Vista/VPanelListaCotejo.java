package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import Modelo.MEvaluacionRegistro;
import Modelo.MItemCotejo;

public class VPanelListaCotejo extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JPanel panelCasillas;
    private final JCheckBox casillaMarcarTodos;
    private final List<JCheckBox> casillasItems;
    private boolean suprimirEvento;

    public VPanelListaCotejo() {
        setLayout(new BorderLayout(8, 8));
        setBorder(new EmptyBorder(12, 12, 12, 12));
        
        casillasItems = new ArrayList<>();
        casillaMarcarTodos = new JCheckBox("Marcar todos");
        casillaMarcarTodos.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        
        casillaMarcarTodos.addItemListener(e -> {
            if (!suprimirEvento) {
                suprimirEvento = true;
                boolean m = (e.getStateChange() == ItemEvent.SELECTED);
                for (JCheckBox c : casillasItems) {
                    c.setSelected(m);
                }
                suprimirEvento = false;
            }
        });
        
        add(casillaMarcarTodos, BorderLayout.NORTH);

        panelCasillas = new JPanel();
        panelCasillas.setLayout(new BoxLayout(panelCasillas, BoxLayout.Y_AXIS));
        
        JScrollPane scroll = new JScrollPane(panelCasillas);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
        
        inicializarItems();
    }

    private void inicializarItems() {
        panelCasillas.removeAll();
        casillasItems.clear();
        
        String[] def = {"Entrega en tiempo", "Cumple estructura", "Ortografía", "Dominio tema", "Referencias"};
        for (String t : def) {
            JCheckBox cb = new JCheckBox(t);
            cb.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            cb.addItemListener(e -> {
                if (!suprimirEvento) {
                    sincronizar();
                }
            });
            casillasItems.add(cb);
            panelCasillas.add(cb);
            panelCasillas.add(Box.createVerticalStrut(4));
        }
        panelCasillas.revalidate();
        panelCasillas.repaint();
    }

    public void cargarDesdeRegistro(MEvaluacionRegistro registro) {
        if (registro == null) return;
        
        suprimirEvento = true;
        List<MItemCotejo> guardados = registro.obtenerItemsListaCotejo();
        
        if (guardados != null && !guardados.isEmpty()) {
            for (int i = 0; i < casillasItems.size(); i++) {
                if (i < guardados.size()) {
                    casillasItems.get(i).setSelected(guardados.get(i).estaMarcado());
                } else {
                    casillasItems.get(i).setSelected(false);
                }
            }
        } else {
            for (JCheckBox cb : casillasItems) {
                cb.setSelected(false);
            }
        }
        
        sincronizar();
        suprimirEvento = false;
    }

    public void volcarARegistro(MEvaluacionRegistro registro) {
        if (registro == null) return;
        List<MItemCotejo> items = new ArrayList<>();
        for (JCheckBox cb : casillasItems) {
            items.add(new MItemCotejo(cb.getText(), cb.isSelected()));
        }
        registro.establecerItemsListaCotejo(items);
    }

    private void sincronizar() {
        if (casillasItems.isEmpty()) return;
        boolean todos = true;
        for (JCheckBox c : casillasItems) {
            if (!c.isSelected()) {
                todos = false;
                break;
            }
        }
        suprimirEvento = true;
        casillaMarcarTodos.setSelected(todos);
        suprimirEvento = false;
    }
}