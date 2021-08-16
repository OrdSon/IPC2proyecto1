/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ordson
 */
public class Conexion {
    static Connection con;
    public Conexion(){
        try {
            if (con != null) {
                System.out.println("conexion previa aun vigente");
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/muebles","root","Shinouyo0.");     
            System.out.println("conexion exitosa");
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
    }
    public static Connection getConnection(){
        return con;
    }
    
}
