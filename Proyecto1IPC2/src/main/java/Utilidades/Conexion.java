/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ordson
 */
public class Conexion {

    private static Connection con;

    public Conexion() {
        conectar();
    }

    private void conectar() {
        try {
            if (con != null) {
                System.out.println("conexion previa aun vigente");
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            String schema = "jdbc:mysql://localhost:3306/muebles";
            String user = "mueblero";
            String password = "1234";
            con = DriverManager.getConnection(schema, user, password);
            System.out.println("conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error" + e);
        }
    }

    public static Connection getConnection() {
        return con;
    }

}
