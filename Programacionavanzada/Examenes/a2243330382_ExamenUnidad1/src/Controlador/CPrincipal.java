package Controlador;

import Vista.*;
import Modelo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CPrincipal {
    VistaPrincipal mdi;
    ListaProductos inventario;
    VInventario vInv = new VInventario();
    VProductos vProd = new VProductos();
    VPuntoVenta vPos = new VPuntoVenta();
    PersistenciaDatos persistencia = new PersistenciaDatos();

    public CPrincipal(VistaPrincipal mdi, ListaProductos inventario) {
        this.mdi = mdi;
        this.inventario = inventario;

        // --- 1. EVENTOS DE MENÚ (ABRIR VENTANAS) ---
        this.mdi.itemInventario.addActionListener(e -> { actualizarTablaInv(); abrir(vInv); });
        this.mdi.itemProductos.addActionListener(e -> { actualizarTablaProd(); abrir(vProd); });
        this.mdi.itemPuntoVenta.addActionListener(e -> { 
            llenarComboArticulos(); 
            abrir(vPos); 
        });

        // --- 2. REPORTES (EXCEL/CSV) ---
        vInv.btnReporteCategoria.addActionListener(e -> {
            String cat = vInv.cbCategoriaFiltro.getSelectedItem().toString();
            if(!cat.equals("Todas")) {
                inventario.exportarExcelPorCategoria(cat, "Reporte_" + cat + ".csv");
                JOptionPane.showMessageDialog(vInv, "Reporte de " + cat + " generado.");
            } else {
                JOptionPane.showMessageDialog(vInv, "Selecciona una categoría específica.");
            }
        });

        vInv.btnExportarExcel.addActionListener(e -> {
            inventario.exportarExcelGeneral("Reporte_General.csv");
            JOptionPane.showMessageDialog(vInv, "Reporte General generado.");
        });

        // --- 3. LÓGICA DE LA CAJA (PUNTO DE VENTA) ---

        // BOTÓN AÑADIR (+)
        vPos.btnAgregar.addActionListener(e -> {
            if (vPos.cbProductos.getSelectedItem() != null) {
                String nombreSel = vPos.cbProductos.getSelectedItem().toString();
                if (!nombreSel.equals("--- Seleccione ---")) {
                    agregarAlCarrito(nombreSel);
                }
            }
        });

        // BOTÓN FINALIZAR VENTA (CREAR JSON)
        vPos.btnPagar.addActionListener(e -> {
            try {
                String textoTotal = vPos.txtTotal.getText().replace("$", "").trim();
                double total = textoTotal.isEmpty() ? 0 : Double.parseDouble(textoTotal);
                
                if(vPos.modeloCarrito.getRowCount() > 0) {
                    String folio = persistencia.procesarVenta(vPos.modeloCarrito, total);
                    JOptionPane.showMessageDialog(vPos, "¡Venta Exitosa!\nFolio: " + folio);
                    
                    // Limpiar después de vender
                    vPos.modeloCarrito.setRowCount(0);
                    vPos.txtTotal.setText("0.00");
                } else {
                    JOptionPane.showMessageDialog(vPos, "El carrito está vacío.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vPos, "Error: " + ex.getMessage());
            }
        });

        // BOTÓN IMPRIMIR TICKET (HISTORIAL)
        vPos.btnTicket.addActionListener(e -> {
            ArrayList<String> listaTickets = persistencia.listarTickets();
            if (listaTickets.isEmpty()) {
                JOptionPane.showMessageDialog(vPos, "No hay historial de ventas.");
                return;
            }

            String seleccion = (String) JOptionPane.showInputDialog(
                vPos, "Seleccione un folio para consultar:", "HISTORIAL",
                JOptionPane.PLAIN_MESSAGE, null, listaTickets.toArray(), 
                listaTickets.get(listaTickets.size() - 1)
            );

            if (seleccion != null) {
                try {
                    File file = new File("tickets/" + seleccion + ".json");
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(vPos, "No se pudo abrir el archivo.");
                }
            }
        });
        
        configurarClicsTablas();
    }

    // --- MÉTODOS DE APOYO ---

    private void llenarComboArticulos() {
        vPos.cbProductos.removeAllItems();
        vPos.cbProductos.addItem("--- Seleccione ---");
        for (Producto p : inventario.getTodos()) {
            vPos.cbProductos.addItem(p.getNombre());
        }
    }

    private void agregarAlCarrito(String nombre) {
        for (Producto p : inventario.getTodos()) {
            if (p.getNombre().equals(nombre)) {
                // Agregar fila a la tabla del carrito
                vPos.modeloCarrito.addRow(new Object[]{p.getId(), p.getNombre(), 1, p.getPrecio()});
                
                // Sumar al total acumulado
                String actualTxt = vPos.txtTotal.getText().trim();
                double actual = actualTxt.isEmpty() ? 0 : Double.parseDouble(actualTxt);
                vPos.txtTotal.setText(String.format("%.2f", actual + p.getPrecio()));
                break;
            }
        }
    }

    private void abrir(JInternalFrame f) {
        if (f.getParent() == null) mdi.desktop.add(f);
        f.setVisible(true);
    }

    private void actualizarTablaInv() {
        vInv.modelo.setRowCount(0);
        for(Producto p : inventario.getTodos()) {
            vInv.modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getCategoria(), p.getStock(), p.getPrecio(), p.getConservacion()});
        }
    }

    private void actualizarTablaProd() {
        vProd.modelo.setRowCount(0);
        for(Producto p : inventario.getTodos()) {
            vProd.modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getCategoria(), p.getStock(), p.getPrecio()});
        }
    }

    private void configurarClicsTablas() {
        vInv.tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int f = vInv.tabla.getSelectedRow();
                if(f != -1) cargarDetalle(vInv.tabla.getValueAt(f,0).toString(), vInv.lblImagenInventario, vInv.txtInfoDetallada);
            }
        });
    }

    private void cargarDetalle(String id, JLabel lbl, JTextArea txt) {
        for(Producto p : inventario.getTodos()){
            if(p.getId().equals(id)){
                ImageIcon i = new ImageIcon(p.getImagenRuta());
                Image img = i.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
                lbl.setIcon(new ImageIcon(img));
                lbl.setText("");
                txt.setText(p.getDetallesEspeciales());
                break;
            }
        }
    }
}