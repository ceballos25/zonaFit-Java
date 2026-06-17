package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion() {
        try {
            String url = "jdbc:mysql://localhost:3306/zona_fit_bd";
            String usuario = "cristian";
            String password = "root";

            return DriverManager.getConnection(url, usuario, password);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if (conexion != null){
            System.out.println("Conexion exitosa.");
        }else {
            System.out.println("Algo salió al conectar");
        }
    }
}
