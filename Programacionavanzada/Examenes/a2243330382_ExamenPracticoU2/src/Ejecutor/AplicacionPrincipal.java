package Ejecutor;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Controlador.CPrincipalControlador;

public class AplicacionPrincipal {

    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
            Font fuente = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
            UIManager.put("defaultFont", fuente);
            UIManager.put("Panel.background", new Color(0xE8E8E8));
            UIManager.put("TabbedPane.background", new Color(0xE8E8E8));
            UIManager.put("TabbedPane.foreground", new Color(0x1E3A5F));
            UIManager.put("Button.default.background", new Color(0x1E3A5F));
            UIManager.put("Button.default.foreground", Color.WHITE);
            UIManager.put("ComboBox.background", Color.WHITE);
            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("TextArea.background", Color.WHITE);
            UIManager.put("Table.background", Color.WHITE);
        } catch (Throwable t) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CPrincipalControlador.iniciar();
    }
}
