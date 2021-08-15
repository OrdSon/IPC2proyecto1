/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Cliente;
import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ordso
 */
public class ClienteDAO  {

    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet resultSet;

    public ArrayList<Cliente> listar(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente";
        connection=conexion.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                String nit = resultSet.getString("nit");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                
                clientes.add( new Cliente(nit, nombre, telefono, direccion));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
}
