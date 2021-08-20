/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modelos.MuebleEnsamblado;
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
public class MuebleEnsambladoDAO {
    Connection connection;

    private static final String SELECCIONAR_MUEBLE = "SELECT * FROM mueble_ensamblado";
    private static final String SELECCIONAR_MUEBLE_CODIGO = "SELECT * FROM mueble_ensamblado WHERE codigo = ?";
    private static final String INSERTAR_MUEBLE = "INSERT INTO mueble_ensamblado (empleado_codigo, punto_venta_codigo, mueble_modelo) VALUES (?,?,?)";
    private static final String UPDATE_MUEBLE = "UPDATE mueble_ensamblado SET  empleado_codigo = ?, punto_venta_codigo = ?, mueble_modelo = ? WHERE codigo = ?";
    private static final String ELIMINAR_MUEBLE = "DELETE FROM mueble_ensamblado WHERE codigo = ?";

    public MuebleEnsambladoDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de muebles y la exporta para su uso
     */
    public ArrayList<MuebleEnsamblado> listar() {

        ArrayList<MuebleEnsamblado> muebles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MUEBLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                int empleadoCodigo = resultSet.getInt("empleado_codigo");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");
                String modelo = resultSet.getString("modelo");
                

                muebles.add(new MuebleEnsamblado(codigo, empleadoCodigo, puntoVentaCodigo, modelo) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return muebles;
    }

    /*
    LISTAR CODIGO
    Usa el codigo de mueble para obtener un registro
     */
    public MuebleEnsamblado listarCodigo(int codigo) {

        MuebleEnsamblado mueble = new MuebleEnsamblado();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MUEBLE_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int empleadoCodigo = resultSet.getInt("empleado_codigo");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");
                String modelo = resultSet.getString("modelo");

                mueble = new MuebleEnsamblado(codigo, empleadoCodigo, puntoVentaCodigo, modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mueble;
    }

    /*
    EDITAR
    Recibe un mueble y la usa para editar un registro ya existente
     */
    public boolean editar(MuebleEnsamblado mueble) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MUEBLE);

            preparedStatement.setInt(1, mueble.getEmpleadoCodigo());
            preparedStatement.setInt(2, mueble.getPuntoVentaCodigo());
            preparedStatement.setString(3, mueble.getMuebleModelo());
            preparedStatement.setInt(4, mueble.getCodigo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    /*
    AÑADIR
    Usa un objeto mueble para añadir un registro a la base de datos
     */
    public boolean añadir(MuebleEnsamblado mueble) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_MUEBLE);
            
            preparedStatement.setInt(1, mueble.getEmpleadoCodigo());
            preparedStatement.setInt(2, mueble.getPuntoVentaCodigo());
            preparedStatement.setString(3, mueble.getMuebleModelo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /*
    ELIMINAR
    Toma el codigo de una mueble y lo usa para encontrar al objetivo
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
                Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
