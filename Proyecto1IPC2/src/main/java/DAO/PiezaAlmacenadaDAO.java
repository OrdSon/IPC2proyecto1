/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.MuebleEnsamblado;
import Modelos.PiezaAlmacenada;
import Modelos.piezaComprada;
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
public class PiezaAlmacenadaDAO {
    
    Connection connection;
    
    private static final String SELECCIONAR_PIEZA = "SELECT * FROM piezas_listas";
    private static final String SELECCIONAR_PIEZA_CANTIDAD = "SELECT tipo, COUNT(costo) AS cantidad, costo FROM piezas_listas WHERE mueble IS NULL GROUP BY costo;";
    private static final String SELECCIONAR_PIEZA_CODIGO = "SELECT * FROM piezas_listas WHERE codigo = ? LIMIT 1";
    private static final String SELECCIONAR_PIEZAS_CODIGO = "SELECT * FROM pieza_almacenada WHERE pieza_codigo = ? LIMIT ?";
    private static final String INSERTAR_PIEZA = "INSERT INTO pieza_almacenada (costo, pieza_codigo, compra_codigo) VALUES (?,?,?)";
    private static final String ELIMINAR_PIEZA = "DELETE FROM pieza_almacenada WHERE codigo = ?";
    private static final String UPDATE_PIEZA = "UPDATE pieza_almacenada SET mueble_ensamblado_codigo = ? WHERE codigo = ?";

    public PiezaAlmacenadaDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de Pieza y la exporta para su uso
     */
    public ArrayList<PiezaAlmacenada> listar() {

        ArrayList<PiezaAlmacenada> piezas = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PIEZA);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                double costo = resultSet.getInt("costo");
                String tipo = resultSet.getString("tipo");
                int compraCodigo = resultSet.getInt("compra");
                
                
                piezas.add(new PiezaAlmacenada(costo, codigo, tipo, compraCodigo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }
    public ArrayList<piezaComprada> listarCompradas() {

        ArrayList<piezaComprada> piezas = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PIEZA_CANTIDAD);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                String tipo = resultSet.getString("tipo");
                int cantidad = resultSet.getInt("cantidad");
                double costo = resultSet.getDouble("costo");
                
                
                
                piezas.add(new piezaComprada(tipo, cantidad, costo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /*
    LISTAR CODIGO
    Usa el codigo de Pieza para obtener un registro
     */
    public PiezaAlmacenada listarCodigo(int codigo) {

        PiezaAlmacenada pieza = new PiezaAlmacenada();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PIEZA_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                 double costo = resultSet.getInt("costo");
                int piezaCodigo = resultSet.getInt("pieza_codigo");
                String tipo = resultSet.getString("tipo");
                int compraCodigo = resultSet.getInt("compra_codigo");
                int muebleEnsambladoCodigo = resultSet.getInt("mueble_ensamblado_codigo");

                pieza = new PiezaAlmacenada(codigo, costo, piezaCodigo,tipo, compraCodigo, muebleEnsambladoCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pieza;
    }
    public ArrayList<PiezaAlmacenada> listarPiezas(int piezaTipo, int limite) {
        ArrayList<PiezaAlmacenada> piezas = new ArrayList<>();
        PiezaAlmacenada pieza;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PIEZAS_CODIGO);
            preparedStatement.setInt(1, piezaTipo);
            preparedStatement.setInt(2, limite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                double costo = resultSet.getInt("costo");
                int piezaCodigo = resultSet.getInt("pieza_codigo");
                int muebleEnsambladoCodigo = resultSet.getInt("mueble_ensamblado_codigo");

                pieza = new PiezaAlmacenada(codigo, costo, piezaCodigo, muebleEnsambladoCodigo);
                piezas.add(pieza);
            }
            if (!piezas.isEmpty()) {
                return piezas;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en listarPiezas " + ex);
        }
        return null;
    }

    /*
    EDITAR (sigo pensando si tiene alguna aplicacion)
    Recibe una Pieza y la usa para editar un registro ya existente
     */
    
    public boolean editar(PiezaAlmacenada pieza) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PIEZA);

            preparedStatement.setInt(1, pieza.getMuebleEnsambladoCodigo());
            
            preparedStatement.setInt(2, pieza.getCodigo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
    public boolean setMueble(MuebleEnsamblado muebleEnsamblado, PiezaAlmacenada piezaAlmacenada) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PIEZA);
            preparedStatement.setInt(1, muebleEnsamblado.getCodigo());
            preparedStatement.setInt(2, piezaAlmacenada.getCodigo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
  

    /*
    AÑADIR
    Usa un objeto Pieza para añadir un registro a la base de datos
     */
    public boolean añadir(PiezaAlmacenada pieza) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_PIEZA);
            preparedStatement.setDouble(1, pieza.getCosto());
            preparedStatement.setInt(2, pieza.getPiezaCodigo());
            preparedStatement.setInt(3, pieza.getCompraCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
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
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_PIEZA);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(PiezaAlmacenadaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
