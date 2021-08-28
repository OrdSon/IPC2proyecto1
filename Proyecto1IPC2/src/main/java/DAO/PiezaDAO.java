package DAO;

import Modelos.Pieza;
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
public class PiezaDAO {

    Connection connection;

    private static final String SELECCIONAR_PIEZA = "SELECT * FROM pieza";
    private static final String SELECCIONAR_PIEZA_CODIGO = "SELECT * FROM pieza WHERE codigo = ?";
    private static final String SELECCIONAR_PIEZA_NOMBRE = "SELECT * FROM pieza WHERE nombre LIKE ? ESCAPE '!'";
    private static final String INSERTAR_PIEZA = "INSERT INTO pieza (nombre) VALUES (?)";
    private static final String UPDATE_PIEZA = "UPDATE pieza SET nombre = ? WHERE codigo = ?";
    private static final String ELIMINAR_PIEZA = "DELETE FROM pieza WHERE codigo = ?";

    public PiezaDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de Pieza y la exporta para su uso
     */
    public ArrayList<Pieza> listar() {

        ArrayList<Pieza> piezas = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PIEZA);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");

                piezas.add(new Pieza(codigo, nombre));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /*
    LISTAR CODIGO
    Usa el codigo de Pieza para obtener un registro
     */
    public Pieza listarCodigo(int codigo) {

        Pieza pieza = new Pieza();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PIEZA_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                String nombre = resultSet.getString("nombre");

                pieza = new Pieza(nombre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pieza;
    }
    /*
    LISTAR NOMBRE
    Usa el codigo de Pieza para obtener un registro
     */
    public Pieza listarNombre(String cadena) {

        Pieza pieza;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_PIEZA_NOMBRE);
            cadena = cadena.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
            preparedStatement.setString(1, cadena+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");
                pieza = new Pieza(codigo, nombre);
                return pieza;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch(NullPointerException ex){
            
        }
        return null;
    }

    /*
    EDITAR
    Recibe una Pieza y la usa para editar un registro ya existente
     */
    public boolean editar(Pieza pieza) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PIEZA);

            preparedStatement.setString(1, pieza.getNombre());
            preparedStatement.setInt(2, pieza.getCodigo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    /*
    AÑADIR
    Usa un objeto Pieza para añadir un registro a la base de datos
     */
    public boolean añadir(Pieza pieza) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_PIEZA);
            preparedStatement.setString(1, pieza.getNombre());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /*
    ELIMINAR
    Toma el codigo de una Pieza y lo usa para encontrar al objetivo
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
                Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
