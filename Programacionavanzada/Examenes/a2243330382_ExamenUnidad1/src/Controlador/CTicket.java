package Controlador;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Date;

public class CTicket {
    public static void generar(DefaultTableModel m, double sub, double tot) {
        String f = "Ticket_" + System.currentTimeMillis() + ".txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            pw.println("======= TICKET DE VENTA =======");
            pw.println("Fecha: " + new Date());
            pw.println("-------------------------------");
            for (int i = 0; i < m.getRowCount(); i++) {
                pw.printf("%-15s x%s   $%s\n", m.getValueAt(i,1), m.getValueAt(i,2), m.getValueAt(i,4));
            }
            pw.println("-------------------------------");
            pw.println("Subtotal: $" + sub);
            pw.println("TOTAL:    $" + tot);
            pw.println("===============================");
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
}