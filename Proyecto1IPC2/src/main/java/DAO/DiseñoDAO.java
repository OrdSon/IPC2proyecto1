/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modelos.Diseño;
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
 * @author OrdSon
 */
public class DiseñoDAO {
    Connection connection;
    
    private static final String SELECCIONAR_DISEÑOS = "SELECT * FROM diseño";
    private static final String SELECCIONAR_DISEÑO_CODIGO = "SELECT * FROM diseño WHERE codigo = ?";
    private static final String INSERTAR_DISEÑO = "INSERT INTO diseño (nit, nombre, telefono, direccion) VALUES (?,?,?,?) ";
    private static final String ELIMINAR_DISEÑO = "DELETE FROM diseño WHERE codigo = ?";
    
    
    public DiseñoDAO() {
        this.connection = Conexion.getConnection();
    }
    /*LISTAR
    crea una lista de objetos Diseño que almacena la informacion
    de todos los diseños en la base de datos, luego exporta esa lista para
    su utilizacion futura
    */
    public ArrayList<Diseño> listar(){
        
        ArrayList<Diseño> diseños = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DISEÑOS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {         
                
                int codigo = resultSet.getInt("codigo");
                String modelo = resultSet.getString("nit");
                int pieza = resultSet.getInt("nombre");
                
                
                diseños.add( new Diseño(codigo,modelo,pieza));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiseñoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diseños;
    }
    
    /*
    LISTAR CODIGO
    Usa el codigo de un diseño para encontrarlo y exportar un objeto Diseño
    con sus datos
    */
    public Diseño listarCodigo(int codigo){
        
        Diseño diseño = new Diseño();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DISEÑO_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                String modelo = resultSet.getString("nit");
                int pieza = resultSet.getInt("nombre");
                
                diseño = new Diseño(codigo,modelo,pieza);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiseñoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diseño;
    }
    /*

    /*
    AÑADIR
    Recibe un diseño como parametro y usa la informacion que almacena
    para crear un nuevo diseño en la base de datos
    */
    public boolean añadir(Diseño diseño){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_DISEÑO);
            preparedStatement.setInt(1, diseño.getCodigo());
            preparedStatement.setString(2, diseño.getModelo());
            preparedStatement.setInt(3, diseño.getPieza());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DiseñoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    /*
    ELIMINAR
    Toma el codigo de un diseño y lo usa para encontrar al objetivo
    y eliminarlo de la base de datos
    */
    public boolean eliminar(int codigo){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_DISEÑO);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(DiseñoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(DiseñoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
