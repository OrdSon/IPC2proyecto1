/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Movimiento;
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
public class MovimientoDAO {
        Connection connection;

    private static final String SELECCIONAR_MOVIMIENTO = "SELECT * FROM movimiento";
    private static final String SELECCIONAR_MOVIMIENTO_CODIGO = "SELECT * FROM movimiento WHERE codigo = ?";
    private static final String INSERTAR_MOVIMIENTO = "INSERT INTO movimiento (monto, resultado, venta_codigo, caja_codigo) VALUES (?,?,?,?)";
    private static final String ELIMINAR_MOVIMIENTO = "DELETE FROM venta WHERE codigo = ?";

    public MovimientoDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de objetos Movimiento y la exporta para su futuro uso
     */
    public ArrayList<Movimiento> listar() {

        ArrayList<Movimiento> movimiento = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MOVIMIENTO);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                double monto = resultSet.getInt("monto");
                double resultado = resultSet.getDouble("resultado");
                int ventaCodigo = resultSet.getInt("venta_codigo");
                int compraCodigo = resultSet.getInt("compra_codigo");
                int cajaCodigo = resultSet.getInt("caja_codigo");

                movimiento.add(new Movimiento(codigo, monto, resultado, ventaCodigo, compraCodigo, cajaCodigo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movimiento;
    }

    /*
    LISTAR CODIGO
    Selecciona un solo Movimiento usando su codigo unico
     */
    public Movimiento listarCodigo(int codigo) {

        Movimiento movimiento = new Movimiento();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MOVIMIENTO_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                double monto = resultSet.getInt("monto");
                double resultado = resultSet.getDouble("resultado");
                int ventaCodigo = resultSet.getInt("venta_codigo");
                int compraCodigo = resultSet.getInt("compra_codigo");
                int cajaCodigo = resultSet.getInt("caja_codigo");

                movimiento = new Movimiento(codigo, monto, resultado, ventaCodigo, compraCodigo, cajaCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movimiento;
    }

    /*
    EDITAR
    No tiene sentido editar un movimiento 
     */
 /*
    AÑADIR
    Añade un movimiento a la base de datos con un objeto venta como base
     */
    public boolean añadir(Movimiento movimiento) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_MOVIMIENTO);
            double monto = movimiento.getMonto();
            double resultado = movimiento.getResultado();
            int venta = movimiento.getVentaCodigo();
            int caja = 1;
            preparedStatement.setDouble(1,monto);
            preparedStatement.setDouble(2, resultado);
            preparedStatement.setInt(3, venta);
            preparedStatement.setInt(4, caja);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /*
    ELIMINAR
    No tiene sentido eliminar movimientos
     */


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
