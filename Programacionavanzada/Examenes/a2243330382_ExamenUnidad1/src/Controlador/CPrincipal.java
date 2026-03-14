package Controlador;
import Vista.*;
import javax.swing.*;

public class CPrincipal {
    VistaPrincipal mdi;
    VInventario vInv;
    VProductos vProd;
    VPuntoVenta vPos;

    public CPrincipal(VistaPrincipal mdi) {
        this.mdi = mdi;
        this.vInv = new VInventario();
        this.vProd = new VProductos();
        this.vPos = new VPuntoVenta();

        this.mdi.itemInventario.addActionListener(e -> mostrarVentana(vInv));
        this.mdi.itemProductos.addActionListener(e -> mostrarVentana(vProd));
        this.mdi.itemPuntoVenta.addActionListener(e -> mostrarVentana(vPos));
    }

    private void mostrarVentana(JInternalFrame frame) {
        if (!frame.isVisible()) {
            mdi.desktop.add(frame);
            frame.setVisible(true);
        }
        try { frame.setSelected(true); } catch (Exception e) {}
    }
}