/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modelos.LoteVenta;
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
public class LoteVentaDAO {
        Connection connection;

    private static final String SELECCIONAR_MUEBLE = "SELECT * FROM lote_venta";
    private static final String SELECCIONAR_MUEBLE_CODIGO = "SELECT * FROM lote_venta WHERE codigo = ?";
    private static final String INSERTAR_MUEBLE = "INSERT INTO mueble (mueble_ensamblado_codigo, venta_codigo) VALUES (?,?)";
    private static final String ELIMINAR_MUEBLE = "DELETE FROM lote_venta WHERE codigo = ?";

    public LoteVentaDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de LoteVenta y la exporta para su uso
     */
    public ArrayList<LoteVenta> listar() {

        ArrayList<LoteVenta> LotesVenta = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MUEBLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                int muebleEnsambladoCodigo = resultSet.getInt("mueble_ensamblado_codigo");
                int ventaCodigo = resultSet.getInt("venta_codigo");
                

                LotesVenta.add(new LoteVenta(codigo, muebleEnsambladoCodigo, ventaCodigo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoteVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LotesVenta;
    }

    /*
    LISTAR CODIGO
    Usa el codigo de LoteVenta para obtener un registro
     */
    public LoteVenta listarCodigo(int codigo) {

        LoteVenta mueble = new LoteVenta();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MUEBLE_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int muebleEnsambladoCodigo = resultSet.getInt("mueble_ensamblado_codigo");
                int ventaCodigo = resultSet.getInt("venta_codigo");

                mueble = new LoteVenta(codigo, muebleEnsambladoCodigo, ventaCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoteVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mueble;
    }

    /*
    EDITAR
    no tiene sentido editar un lote de venta
     */


    /*
    AÑADIR
    Usa un objeto LoteVenta para añadir un registro a la base de datos
     */
    public boolean añadir(LoteVenta loteVenta) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_MUEBLE);

            preparedStatement.setInt(1, loteVenta.getMuebleEnsambladoCodigo());
            preparedStatement.setInt(2, loteVenta.getVenta_codigo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoteVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /*
    ELIMINAR
    Toma el codigo de un LoteVenta y lo usa para encontrar al objetivo
    y eliminarlo de la base de datos
     */
    public boolean eliminar(int codigo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_MUEBLE);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(LoteVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(LoteVentaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
