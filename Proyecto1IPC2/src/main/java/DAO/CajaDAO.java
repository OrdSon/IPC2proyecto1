
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
    private static final String UPDATE_CAJA = "UPDATE caja SET capital = ? WHERE codigo = ?";
    private static final String ELIMINAR_CAJA = "DELETE FROM caja WHERE codigo = ?";

    public CajaDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de cajas y la exporta para su uso
     */
    public ArrayList<Caja> listar() {

        ArrayList<Caja> cajas = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_CAJA);
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
    Usa el codigo de caja para obtener un registro
     */
    public Caja listarCodigo(int codigo) {

        Caja caja = new Caja();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_CAJA_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            int contador = 0;
            while (resultSet.next()) {
                double capital = resultSet.getDouble("capital");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");

                caja = new Caja(capital, puntoVentaCodigo);
                contador++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caja;
    }

    /*
    EDITAR
    Recibe una caja y la usa para editar un registro ya existente
     */
    public boolean editar(Caja caja) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAJA);

            preparedStatement.setDouble(1, caja.getCapital());
            preparedStatement.setInt(2, caja.getCodigo());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    /*
    AÑADIR
    Usa un objeto Caja para añadir un registro a la base de datos
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
    Toma el codigo de una caja y lo usa para encontrar al objetivo
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
