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
            String url = "jdbc:mysql://localhost:3306/muebles";
            String user = "mueblero";
            String password = "PassW123.";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error" + e);
        }
    }

    public static Connection getConnection() {
        return con;
    }

}
