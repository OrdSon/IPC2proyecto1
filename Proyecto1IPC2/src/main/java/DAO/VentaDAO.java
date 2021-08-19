/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Venta;
import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.Date;
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
public class VentaDAO {

    Connection connection;

    private static final String SELECCIONAR_VENTAS = "SELECT * FROM venta";
    private static final String SELECCIONAR_VENTA_CODIGO = "SELECT * FROM venta WHERE codigo = ?";
    private static final String INSERTAR_VENTA = "INSERT INTO venta (total, fecha, punto_venta_codigo, empleado_codigo, cliente_codigo) VALUES (?,?)";
    private static final String ELIMINAR_VENTA = "DELETE FROM venta WHERE codigo = ?";

    public VentaDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de objetos Venta y la exporta para su futuro uso
     */
    public ArrayList<Venta> listar() {

        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_VENTAS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                double total = resultSet.getInt("total");
                Date fecha = resultSet.getDate("fecha");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");
                int empleadoCodigo = resultSet.getInt("empleado_codigo");
                int clienteCodigo = resultSet.getInt("cliente_codigo");

                ventas.add(new Venta(codigo, total, fecha, puntoVentaCodigo, empleadoCodigo, clienteCodigo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventas;
    }

    /*
    LISTAR CODIGO
    Selecciona una sola venta usando su codigo unico
     */
    public Venta listarCodigo(int codigo) {

        Venta venta = new Venta();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_VENTA_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                double total = resultSet.getInt("total");
                Date fecha = resultSet.getDate("fecha");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");
                int empleadoCodigo = resultSet.getInt("empleado_codigo");
                int clienteCodigo = resultSet.getInt("cliente_codigo");

                venta = new Venta(codigo, total, fecha, puntoVentaCodigo, empleadoCodigo, clienteCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venta;
    }

    /*
    EDITAR
    No tiene sentido editar una venta
     */
 /*
    AÑADIR
    Añade una venta a la base de datos con un objeto venta como base
     */
    public boolean añadir(Venta venta) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_VENTA);
            preparedStatement.setDouble(1, venta.getTotal());
            preparedStatement.setDate(1, venta.getFecha());
            preparedStatement.setDouble(1, venta.getPuntoVentaCodigo());
            preparedStatement.setDouble(1, venta.getEmpleadoCodigo());
            preparedStatement.setDouble(1, venta.getClienteCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /*
    ELIMINAR
    Toma el codigo de una venta y lo usa para encontrar al objetivo
    y eliminarla de la base de datos
     */
    public boolean eliminar(int codigo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_VENTA);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
