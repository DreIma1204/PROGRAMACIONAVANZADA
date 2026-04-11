package Librerias;

import java.sql.*;

public class ConexionBD {
    
    private static final String urlMySQL = "jdbc:mysql://";
    private static final String urlPostgreSQL = "jdbc:postgresql://";
    private static final String urlMariaDB = "jdbc:mariadb://";
    private static final String urlOracle = "jdbc:oracle:thin:@";
    private static final String urlAccess = "jdbc:ucanaccess://";
    private static final String urlSQLServer = "jdbc:sqlserver://";

    public static Connection conectarSQLServer(String host, String bd, String user, String pass) throws SQLException {
        String cadenaconexion = urlSQLServer + host + "\\SQLEXPRESS;databaseName=" + bd + ";";
        return DriverManager.getConnection(cadenaconexion, user, pass);
    }

    public static Connection conectarMySQL(String host, String bd, String user, String pass) throws SQLException {
        String cadenaconexion = urlMySQL + host + "/" + bd;
        return DriverManager.getConnection(cadenaconexion, user, pass);
    }

    public static void cerrar(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar: " + e.getMessage());
        }
    }
}