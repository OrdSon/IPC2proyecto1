/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Mueble;
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
public class MuebleDAO {

    Connection connection;

    private static final String SELECCIONAR_MUEBLE = "SELECT * FROM mueble";
    private static final String SELECCIONAR_MUEBLE_CODIGO = "SELECT * FROM mueble WHERE modelo = ?";
    private static final String INSERTAR_MUEBLE = "INSERT INTO mueble (modelo, nombre, precio, costo) VALUES (?,?,?,?)";
    private static final String UPDATE_MUEBLE = "UPDATE mueble SET  nombre = ?, precio = ?, costo = ? WHERE modelo = ?";
    private static final String ELIMINAR_MUEBLE = "DELETE FROM mueble WHERE modelo = ?";
    private static final String SELECCIONAR_NOMBRE = "SELECT * FROM mueble WHERE nombre LIKE ? ESCAPE '!'";

    public MuebleDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de Muebles y la exporta para su uso
     */
    public ArrayList<Mueble> listar() {

        ArrayList<Mueble> muebles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MUEBLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                String modelo = resultSet.getString("modelo");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                double costo = resultSet.getDouble("costo");

                muebles.add(new Mueble(modelo, nombre, precio, costo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MuebleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return muebles;
    }

    /*
    LISTAR CODIGO
    Usa el codigo de Mueble para obtener un registro
     */
    public Mueble listarCodigo(String modelo) {

        Mueble mueble = new Mueble();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MUEBLE_CODIGO);
            preparedStatement.setString(1, modelo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                double costo = resultSet.getDouble("costo");

                mueble = new Mueble(modelo, nombre, precio, costo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MuebleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mueble;
    }
    
    public Mueble listarNombre(String nombre) { 

        Mueble mueble;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_NOMBRE);
            nombre = nombre.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
            preparedStatement.setString(1, nombre+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String modelo = resultSet.getString("modelo");
//                double precio = resultSet.getDouble("precio");
//                double costo = resultSet.getDouble("costo");
                mueble = new Mueble();
                mueble.setModelo(modelo);
                mueble.setNombre(nombre);
//                mueble.setPrecio(precio);
//                mueble.setCosto(costo);
                return mueble;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MuebleDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch(NullPointerException ex){
            
        }
        return null;
    }

    /*
    EDITAR
    Recibe una Mueble y la usa para editar un registro ya existente
     */
    public boolean editar(Mueble mueble) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MUEBLE);

            preparedStatement.setString(1, mueble.getNombre());
            preparedStatement.setDouble(2, mueble.getPrecio());
            preparedStatement.setDouble(3, mueble.getCosto());
            preparedStatement.setString(4, mueble.getModelo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(MuebleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    /*
    AÑADIR
    Usa un objeto Mueble para añadir un registro a la base de datos
     */
    public boolean añadir(Mueble mueble) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_MUEBLE);

            preparedStatement.setString(1, mueble.getModelo());
            preparedStatement.setString(2, mueble.getNombre());
            preparedStatement.setDouble(3, mueble.getPrecio());
            preparedStatement.setDouble(4, mueble.getCosto());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MuebleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /*
    ELIMINAR
    Toma el codigo de un Mueble y lo usa para encontrar al objetivo
    y eliminarlo de la base de datos
     */
    public boolean eliminar(String modelo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_MUEBLE);
            preparedStatement.setString(1, modelo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(MuebleDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(MuebleDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
