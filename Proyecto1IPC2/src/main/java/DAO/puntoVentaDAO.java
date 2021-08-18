/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modelos.PuntoVenta;
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
 * @author ordson
 */
public class PuntoVentaDAO  {
    
    
    Connection connection;
    
    private static final String SELECCIONAR_PUNTOS = "SELECT * FROM punto_venta";
    private static final String SELECCIONAR_PUNTOS_CODIGO = "SELECT * FROM punto_venta WHERE codigo = ?";
    private static final String INSERTAR_PUNTO = "INSERT INTO punto_venta (nombre, telefono, direccion) VALUES (?,?,?) ";
    private static final String UPDATE_PUNTO = "UPDATE punto_venta SET nombre = ?, telefono = ?, direccion = ? WHERE codigo = ?";
    private static final String ELIMINAR_PUNTO = "DELETE FROM punto_venta WHERE codigo = ?";
    
    
    public PuntoVentaDAO() {
        this.connection = Conexion.getConnection();
    }
    /*LISTAR
    crea una lista de objetos PuntoVenta que almacena la informacion
    de todos los puntos de venta en la base de datos, luego exporta esa lista para
    su utilizacion futura
    */
    public ArrayList<PuntoVenta> listar(){
        
        ArrayList<PuntoVenta> puntosVenta = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PUNTOS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {         
                
                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                
                puntosVenta.add(new PuntoVenta(codigo, nombre, direccion, telefono));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PuntoVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puntosVenta;
    }
    
    /*
    LISTAR CODIGO
    Usa el codigo de un punto de venta para encontrarlo y exportar un objeto punto de venta
    con sus datos
    */
    public PuntoVenta listarCodigo(int codigo){
       
        PuntoVenta puntoVenta = new PuntoVenta();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PUNTOS_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {         
                
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                
                puntoVenta = new PuntoVenta(nombre, direccion, telefono);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PuntoVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puntoVenta;
    }
    /*
    EDITAR
    recibe un punto de venta como atributo, en este caso sera un punto de venta cuyos datos
    sean diferentes a los encontrados en la base de datos, salvo por el codigo
    con dicho codigo y con los nuevos datos, se procede a editar el registro
    */
    public boolean editar(PuntoVenta puntoVenta){
       
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PUNTO);
            preparedStatement.setString(1, puntoVenta.getNombre());
            preparedStatement.setString(2, puntoVenta.getTelefono());
            preparedStatement.setString(3, puntoVenta.getDireccion());
            preparedStatement.setInt(4, puntoVenta.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            
            System.out.println(ex);
            Logger.getLogger(PuntoVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    /*
    AÑADIR
    Recibe un punto de venta como parametro y usa la informacion que almacena
    para crear un nuevo punto de venta en la base de datos
    */
    public int añadir(PuntoVenta puntoVenta){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_PUNTO);
            preparedStatement.setString(1, puntoVenta.getNombre());
            preparedStatement.setString(2, puntoVenta.getTelefono());
            preparedStatement.setString(3, puntoVenta.getDireccion());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PuntoVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        return 0;
    }
    /*
    ELIMINAR
    Toma el codigo de un punto de venta y lo usa para encontrar al objetivo
    y eliminarlo de la base de datos
    */
    public boolean eliminar(int codigo){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_PUNTO);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(PuntoVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(PuntoVentaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
}
