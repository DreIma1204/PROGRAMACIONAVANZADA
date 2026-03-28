package Utilidades;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public final class UIconoSemaforoUtil {

    private UIconoSemaforoUtil() {
    }

    public static ImageIcon crearCirculo(int diametro, Color color) {
        BufferedImage imagen = new BufferedImage(diametro, diametro, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagen.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.fillOval(0, 0, diametro, diametro);
        g.dispose();
        return new ImageIcon(imagen);
    }
}
