/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Compra;
import Utilidades.Conexion;
import Utilidades.DateManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OrdSon
 */
public class CompraDAO {

    Connection connection;
    DateManager dateManager = new DateManager();
    private static final String SELECCIONAR_COMPRAS = "SELECT * FROM compra";
    private static final String SELECCIONAR_COMPRA_CODIGO = "SELECT * FROM compra WHERE codigo = ?";
    private static final String INSERTAR_COMPRA = "INSERT INTO compra (fecha, total, punto_venta_codigo, empleado_codigo, cliente_codigo) VALUES (?,?)";

    public CompraDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de objetos Compra y la exporta para su futuro uso
     */
    public ArrayList<Compra> listar() {

        ArrayList<Compra> compras = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_COMPRAS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                Date fechaSql = resultSet.getDate("fecha");
                double total = resultSet.getDouble("total");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");
                LocalDate fecha = dateManager.convertirALocalDate(fechaSql);
                compras.add(new Compra(codigo, fecha, total, puntoVentaCodigo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compras;
    }

    /*
    LISTAR CODIGO
    Selecciona una sola Compra usando su codigo unico
     */
    public Compra listarCodigo(int codigo) {

        Compra compra = new Compra();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_COMPRA_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                double total = resultSet.getInt("total");
                Date fechaSql = resultSet.getDate("fecha");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");
                LocalDate fecha = dateManager.convertirALocalDate(fechaSql);
                compra = new Compra(codigo, fecha, total, puntoVentaCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compra;
    }

    public boolean añadir(Compra compra) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_COMPRA);
            
            preparedStatement.setDate(1, dateManager.convertirADate(compra.getFecha()));
            preparedStatement.setDouble(2, compra.getTotal());
            preparedStatement.setInt(3, compra.getPuntoVentaCodigo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
