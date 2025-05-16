package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static Connection connection = null;

    public static Connection connect(String user, String pwd, String host, String port, String dbName) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            setConnection(DriverManager.getConnection(url, user, pwd));
            System.out.println("✔️ Conexión exitosa con la BBDD: " + dbName);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver JDBC no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Error al conectarse a la BBDD");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error insesperado de conexión con la BBDD");
            e.printStackTrace();
        }
        return getConnection();
    }

    public static void close() {
        try {
            if (getConnection() != null && !getConnection().isClosed()) {
                getConnection().close();
                System.out.println("✔️ Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error inesperado");
        }
    }

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		ConnectionDB.connection = connection;
	}
}
