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
    
    private static final String SELECCIONAR_CLIENTES = "SELECT * FROM cliente";
    private static final String SELECCIONAR_CLIENTE_CODIGO = "SELECT * FROM cliente WHERE codigo = ?";
    private static final String SELECCIONAR_CLIENTE_NIT = "SELECT * FROM cliente WHERE nit = ?";
    private static final String INSERTAR_CLIENTE = "INSERT INTO cliente (nit, nombre, telefono, direccion) VALUES (?,?,?,?) ";
    
    
    public ClienteDAO() {
        this.connection = Conexion.getConnection();
    }
    
    public ArrayList<Cliente> listar(){
        connection = Conexion.getConnection();
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_CLIENTES);
            ResultSet resultSet = preparedStatement.executeQuery();
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
    
    public boolean añadir(Cliente cliente){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_CLIENTE);
            preparedStatement.setString(1, cliente.getNit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getTelefono());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
}
