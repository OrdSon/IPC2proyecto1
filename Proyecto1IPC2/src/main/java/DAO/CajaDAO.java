/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Caja;
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
public class CajaDAO {

    Connection connection;

    private static final String SELECCIONAR_CAJA = "SELECT * FROM caja";
    private static final String SELECCIONAR_CAJA_CODIGO = "SELECT * FROM caja WHERE codigo = ?";
    private static final String INSERTAR_CAJA = "INSERT INTO caja (capital, punto_venta_codigo) VALUES (?,?)";
    private static final String UPDATE_CAJA = "UPDATE caja SET capital = ?, punto_venta_codigo = ? WHERE codigo = ?";
    private static final String ELIMINAR_CAJA = "DELETE FROM caja WHERE codigo = ?";

    public CajaDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    crea una lista de objetos Cliente que almacena la informacion
    de todos los clientes en la base de datos, luego exporta esa lista para
    su utilizacion futura
     */
    public ArrayList<Caja> listar() {

        ArrayList<Caja> cajas = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_CAJA_STRING);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                double capital = resultSet.getDouble("capital");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");

                cajas.add(new Caja(codigo, capital, puntoVentaCodigo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cajas;
    }

    /*
    LISTAR CODIGO
    Usa el codigo de un cliente para encontrarlo y exportar un objeto Cliente
    con sus datos
     */
    public Caja listarCodigo(int codigo) {

        Caja caja = new Caja();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_CAJA_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double capital = resultSet.getDouble("capital");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");

                caja = new Caja(capital, puntoVentaCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caja;
    }

    /*
    EDITAR
    recibe un cliente como atributo, en este caso sera un cliente cuyos datos
    sean diferentes a los encontrados en la base de datos, salvo por el codigo
    con dicho codigo y con los nuevos datos, se procede a editar el registro
     */
    public boolean editar(Caja caja) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAJA);

            preparedStatement.setDouble(1, caja.getCapital());
            preparedStatement.setInt(2, caja.getPuntoVentaCodigo());
            preparedStatement.setInt(3, caja.getCodigo());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    /*
    AÑADIR
    Recibe un cliente como parametro y usa la informacion que almacena
    para crear un nuevo cliente en la base de datos
     */
    public boolean añadir(Caja caja) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_CAJA);
            preparedStatement.setDouble(1, caja.getCapital());
            preparedStatement.setInt(2, caja.getPuntoVentaCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /*
    ELIMINAR
    Toma el codigo de un cliente y lo usa para encontrar al objetivo
    y eliminarlo de la base de datos
     */
    public boolean eliminar(int codigo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_CAJA);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
