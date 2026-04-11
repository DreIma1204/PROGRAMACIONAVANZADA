package Modelo;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class BaseDatos {

    private Connection conexion;

    public BaseDatos(Connection conexion) {
        this.conexion = conexion;
    }

    private void asignarParametros(PreparedStatement ps, Object[] parametros) throws SQLException {
        if (parametros != null) {
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }
        }
    }

    public ArrayList<String[]> consultar(String tabla, String campos, String condicion, Object[] parametros) {
        ArrayList<String[]> resultados = new ArrayList<>();
        String columnas = (campos == null || campos.isEmpty()) ? "*" : campos;
        String sql = "SELECT " + columnas + " FROM " + tabla;
        
        if (condicion != null && !condicion.isEmpty()) {
            sql += " WHERE " + condicion;
        }

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            asignarParametros(ps, parametros);
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumnas = rsmd.getColumnCount();

                while (rs.next()) {
                    String[] fila = new String[numColumnas];
                    for (int i = 1; i <= numColumnas; i++) {
                        fila[i - 1] = rs.getString(i);
                    }
                    resultados.add(fila);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en consulta: " + e.getMessage());
        }
        return resultados;
    }

    public <T> ArrayList<T> consultarAObjeto(String sql, Class<T> clase, Object[] parametros) {
        ArrayList<T> resultados = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            asignarParametros(ps, parametros);
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                
                while (rs.next()) {
                    T objeto = clase.getDeclaredConstructor().newInstance();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String nombreColumna = rsmd.getColumnLabel(i);
                        Object valorColumna = rs.getObject(i);
                        
                        try {
                            Field campo = clase.getDeclaredField(nombreColumna);
                            campo.setAccessible(true);
                            campo.set(objeto, valorColumna);
                        } catch (NoSuchFieldException e) {
                            // Si el campo no existe en el POJO, se ignora
                        }
                    }
                    resultados.add(objeto);
                }
            }
        } catch (Exception e) {
            System.err.println("Error en mapeo de objetos: " + e.getMessage());
        }
        return resultados;
    }

    public void insertar(String tabla, Map<String, Object> datos) {
        StringJoiner columnas = new StringJoiner(", ");
        StringJoiner placeholders = new StringJoiner(", ");
        Object[] valores = new Object[datos.size()];
        
        int i = 0;
        for (Map.Entry<String, Object> entrada : datos.entrySet()) {
            columnas.add(entrada.getKey());
            placeholders.add("?");
            valores[i++] = entrada.getValue();
        }

        String sql = "INSERT INTO " + tabla + " (" + columnas + ") VALUES (" + placeholders + ")";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            asignarParametros(ps, valores);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
    }

    public void modificar(String tabla, Map<String, Object> valores, String condicion, Object[] parametrosCondicion) {
        StringJoiner setClause = new StringJoiner(", ");
        Object[] todosLosParametros = new Object[valores.size() + (parametrosCondicion != null ? parametrosCondicion.length : 0)];
        
        int i = 0;
        for (Map.Entry<String, Object> entrada : valores.entrySet()) {
            setClause.add(entrada.getKey() + " = ?");
            todosLosParametros[i++] = entrada.getValue();
        }

        String sql = "UPDATE " + tabla + " SET " + setClause;
        if (condicion != null && !condicion.isEmpty()) {
            sql += " WHERE " + condicion;
            if (parametrosCondicion != null) {
                for (Object param : parametrosCondicion) {
                    todosLosParametros[i++] = param;
                }
            }
        }

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            asignarParametros(ps, todosLosParametros);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
    }

    public void eliminar(String tabla, String condicion, Object[] parametros) {
        String sql = "DELETE FROM " + tabla + " WHERE " + condicion;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            asignarParametros(ps, parametros);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }
}