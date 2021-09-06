package DAO;

import Modelos.DetalleDevolucion;
import Modelos.Devolucion;
import Utilidades.Conexion;
import Utilidades.DateManager;
import Utilidades.Fecha;
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
public class DevolucionDAO {

    Connection connection;
    DateManager dateManager = new DateManager();
    private final String SELECCIONAR_DEVOLUCION = "SELECT * FROM devolucion";
    private final String SELECCIONAR_DEVOLUCION_CODIGO = "SELECT * FROM devolucion WHERE codigo = ?";
    private final String INSERTAR_DEVOLUCION = "INSERT INTO devolucion (fecha, total, venta_codigo, mueble_devuelto) VALUES (?,?,?,?)";
    private final String SELECCIONAR_DETALLES = "SELECT * FROM detalle_devolucion";
    private final String SELECCIONAR_DETALLES_BETWEEN = "SELECT * FROM detalle_devolucion WHERE fecha BETWEEN ? AND ?";
    private final String SELECCIONAR_DETALLES_CLIENTE = "SELECT * FROM detalle_devolucion WHERE nit = ? AND fecha BETWEEN ? AND ?";
    private final String ELIMINAR_DEVOLUCION = "DELETE FROM devolucion WHERE codigo = ?";

    public DevolucionDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de devoluciones y la exporta para su uso
     */
    public ArrayList<Devolucion> listar() {

        ArrayList<Devolucion> devoluciones = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DEVOLUCION);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                Date fechaSql = resultSet.getDate("fecha");
                double total = resultSet.getDouble("total");
                int ventaCodigo = resultSet.getInt("venta_codigo");
                LocalDate fecha = dateManager.convertirALocalDate(fechaSql);
                devoluciones.add(new Devolucion(codigo, fecha, total, ventaCodigo, ventaCodigo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevolucionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return devoluciones;
    }

    public ArrayList<DetalleDevolucion> listarDetalles() {

        ArrayList<DetalleDevolucion> detalles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DETALLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nit = resultSet.getString("nit");
                String nombre = resultSet.getString("nombre");
                int producto = resultSet.getInt("producto");
                String modelo = resultSet.getString("modelo");
                String mueble = resultSet.getString("mueble");
                double total = resultSet.getDouble("total");
                Date date = resultSet.getDate("fecha");
                LocalDate fecha = null;
                if (date != null) {
                    fecha = dateManager.convertirALocalDate(date);
                }
                DetalleDevolucion detalleDevolucion = new DetalleDevolucion(nit, nombre, producto, modelo, mueble, total, fecha);
                detalles.add(detalleDevolucion);
            }
            return detalles;
        } catch (SQLException ex) {
            Logger.getLogger(DevolucionDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<DetalleDevolucion> listarDetallesBetween(Date inicio, Date fin, String nit) {
        ArrayList<DetalleDevolucion> detalles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement;
            
            if (nit != null && !nit.isEmpty()) {
                preparedStatement = connection.prepareStatement(SELECCIONAR_DETALLES_CLIENTE);
                preparedStatement.setString(1, nit);
                preparedStatement.setDate(2, inicio);
                preparedStatement.setDate(3, fin);
            } else {
                preparedStatement = connection.prepareStatement(SELECCIONAR_DETALLES_BETWEEN);
                preparedStatement.setDate(1, inicio);
                preparedStatement.setDate(2, fin);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nitFull = resultSet.getString("nit");
                String nombre = resultSet.getString("nombre");
                int producto = resultSet.getInt("producto");
                String modelo = resultSet.getString("modelo");
                String mueble = resultSet.getString("mueble");
                double total = resultSet.getDouble("total");
                Date date = resultSet.getDate("fecha");
                LocalDate fecha = null;
                if (date != null) {
                    fecha = dateManager.convertirALocalDate(date);
                }
                DetalleDevolucion detalleDevolucion = new DetalleDevolucion(nitFull, nombre, producto, modelo, mueble, total, fecha);
                detalles.add(detalleDevolucion);
            }
            return detalles;
        } catch (SQLException ex) {
            Logger.getLogger(DevolucionDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {

        }
        return null;
    }


    /*
    LISTAR CODIGO
    Usa el codigo de devolucion para obtener un registro
     */
    public Devolucion listarCodigo(int codigo) {

        Devolucion devolucion = new Devolucion();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DEVOLUCION_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Date fechaSql = resultSet.getDate("fecha");
                double total = resultSet.getDouble("total");
                int ventaCodigo = resultSet.getInt("venta_codigo");

                LocalDate fecha = dateManager.convertirALocalDate(fechaSql);

                devolucion = new Devolucion(codigo, fecha, total, ventaCodigo, ventaCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevolucionDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return devolucion;
    }

    /*
    EDITAR
    No tiene sentido editar devoluciones
     */
 /*
    AÑADIR
    Usa un objeto devolucion para añadir un registro a la base de datos
     */
    public boolean añadir(Devolucion devolucion) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_DEVOLUCION);
            preparedStatement.setDate(1, dateManager.convertirADate(devolucion.getFecha()));
            preparedStatement.setDouble(2, devolucion.getTotal());
            preparedStatement.setInt(3, devolucion.getVentaCodigo());
            preparedStatement.setInt(4, devolucion.getMuebleDevuelto());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DevolucionDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /*
    ELIMINAR
    Toma el codigo de una devolucion y lo usa para encontrar al objetivo
    y eliminarlo de la base de datos
     */
    public boolean eliminar(int codigo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_DEVOLUCION);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(DevolucionDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(DevolucionDAO.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
