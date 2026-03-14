package Librerias;
import java.io.*;
import java.util.ArrayList;

public class Libreria {
    public static ArrayList<String[]> LeerCSV(String n) {
        ArrayList<String[]> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(n))) {
            String l;
            while ((l = br.readLine()) != null) { if (!l.trim().isEmpty()) lista.add(l.split(",")); }
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    public static void EscribirCSV(String n, String contenido, boolean anexar) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(n, anexar))) {
            bw.write(contenido); bw.newLine();
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
}