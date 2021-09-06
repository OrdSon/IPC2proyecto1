/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.DetalleVenta;
import Modelos.Empleado;
import Modelos.MuebleVendido;
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
public class reporteDAO {

    Connection connection;
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    DateManager dateManager = new DateManager();
    VentaDAO ventaDAO = new VentaDAO();
    private final String SELECCIONAR_MUEBLES = "SELECT * FROM mueble_vendido";
    private final String SELECCIONAR_MEJOR = "SELECT dpi, empleado, precio, costo_default, sum(precio-if(costo is null, costo_default, costo)) as ganancia FROM mueble_vendido group by dpi ORDER BY ganancia DESC LIMIT 1";
    private final String SELECCIONAR_MEJOR_VENDEDOR = "SELECT empleado, count(*) as cuenta FROM venta_realizada group by empleado order by cuenta desc limit 1";
    private final String SELECCIONAR_MUEBLES_BETWEEN = "SELECT * FROM mueble_vendido WHERE fecha BETWEEN ? AND ?";
    private final String SELECCIONAR_MUEBLES_EMPLEADO = "SELECT * FROM mueble_vendido WHERE dpi = ? AND fecha BETWEEN ? AND ?";
    private static final String SELECCIONAR_DETALLES_VENTA_MODELO = "SELECT * FROM detalle_venta WHERE modelo = ?";
    private static final String SELECCIONAR_MEJOR_MUEBLE = "SELECT count(*) as cantidad, modelo FROM detalle_venta GROUP BY modelo ORDER BY cantidad DESC LIMIT 1";
    private static final String SELECCIONAR_PEOR_MUEBLE = "SELECT count(*) as cantidad, modelo FROM detalle_venta GROUP BY modelo ORDER BY cantidad ASC LIMIT 1";
    private static final String SELECCIONAR_DETALLES_VENTA = "SELECT * FROM detalle_venta";

    public reporteDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de cajas y la exporta para su uso
     */
    public ArrayList<MuebleVendido> listarMueblesVendidos() {

        ArrayList<MuebleVendido> muebles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_MUEBLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int empleadoCodigo = resultSet.getInt("codigo");
                String empleadoDpi = resultSet.getString("dpi");
                String empleadoNombre = resultSet.getString("empleado");
                String modelo = resultSet.getString("modelo");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                double costo = resultSet.getDouble("costo");
                if (costo == 0) {
                    costo = resultSet.getDouble("costo_default");
                }
                int productoCodigo = resultSet.getInt("producto_codigo");
                int ventaCodigo = resultSet.getInt("venta_codigo");
                Date date = resultSet.getDate("fecha");
                LocalDate localDate = null;
                if (date != null) {
                    localDate = dateManager.convertirALocalDate(date);
                }
                muebles.add(new MuebleVendido(empleadoCodigo, empleadoDpi, empleadoNombre,
                        modelo, nombre, precio, costo, productoCodigo, ventaCodigo, localDate));
            }
            return muebles;
        } catch (SQLException ex) {
            Logger.getLogger(reporteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<MuebleVendido> listarMueblesVendidosFECHA(Date inicio, Date fin, String dpi) {
        String query = SELECCIONAR_MUEBLES_BETWEEN;
        if (dpi != null) {
            query = SELECCIONAR_MUEBLES_EMPLEADO;
        }
        ArrayList<MuebleVendido> muebles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (dpi == null) {
                preparedStatement.setDate(1, inicio);
                preparedStatement.setDate(2, fin);
            } else {
                preparedStatement.setString(1, dpi);
                preparedStatement.setDate(2, inicio);
                preparedStatement.setDate(3, fin);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int empleadoCodigo = resultSet.getInt("codigo");
                String empleadoDpi = resultSet.getString("dpi");
                String empleadoNombre = resultSet.getString("empleado");
                String modelo = resultSet.getString("modelo");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                double costo = resultSet.getDouble("costo");
                if (costo == 0) {
                    costo = resultSet.getDouble("costo_default");
                }
                int productoCodigo = resultSet.getInt("producto_codigo");
                int ventaCodigo = resultSet.getInt("venta_codigo");
                Date date = resultSet.getDate("fecha");
                LocalDate localDate = null;
                if (date != null) {
                    localDate = dateManager.convertirALocalDate(date);
                }
                muebles.add(new MuebleVendido(empleadoCodigo, empleadoDpi, empleadoNombre,
                        modelo, nombre, precio, costo, productoCodigo, ventaCodigo, localDate));
            }
            return muebles;
        } catch (SQLException ex) {
            Logger.getLogger(reporteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return null;
    }

    public Empleado listarMejor() {
        try {
            PreparedStatement ps = connection.prepareStatement(SELECCIONAR_MEJOR);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dpi = rs.getString("dpi");
                Empleado empleado = empleadoDAO.listarDPI(dpi);
                return empleado;
            }
        } catch (SQLException ex) {
            Logger.getLogger(reporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Empleado listarMejorVendedor() {
        try {
            PreparedStatement ps = connection.prepareStatement(SELECCIONAR_MEJOR_VENDEDOR);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("empleado");
                Empleado empleado = empleadoDAO.listarCodigo(codigo);
                return empleado;
            }
        } catch (SQLException ex) {
            Logger.getLogger(reporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<DetalleVenta> listarMejorMueble() {
        try {
            PreparedStatement ps = connection.prepareStatement(SELECCIONAR_MEJOR_MUEBLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String modelo = rs.getString("modelo");
                ArrayList<DetalleVenta> detallesVentas = listarDetalleVenta(modelo);
                return detallesVentas;
            }
            
        } catch (SQLException e) {
            
        }
        return null;
    }

    public ArrayList<DetalleVenta> listarPeorMueble() {
        try {
            PreparedStatement ps = connection.prepareStatement(SELECCIONAR_PEOR_MUEBLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String modelo = rs.getString("modelo");
                ArrayList<DetalleVenta> detallesVentas = listarDetalleVenta(modelo);
                return detallesVentas;
            }
            
        } catch (SQLException e) {
            
        }
        return null;
    }
    
    public ArrayList<DetalleVenta> listarDetalleVenta(String modelo) {

        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DETALLES_VENTA_MODELO);
            preparedStatement.setString(1, modelo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int codigoVenta = resultSet.getInt("codigo_venta");
                double total = resultSet.getDouble("total");
                Date fecha = resultSet.getDate("fecha");
                int puntoVenta = resultSet.getInt("punto_venta_codigo");
                String nombreProducto = resultSet.getString("nombre_producto");
                double precio = resultSet.getDouble("precio");
                String nit = resultSet.getString("nit");
                String nombreCliente = resultSet.getString("nombre");
                int codigoProducto = resultSet.getInt("codigo_producto");

                LocalDate localDate;
                if (fecha != null) {
                    localDate = dateManager.convertirALocalDate(fecha);
                } else {
                    localDate = null;
                }

                detalles.add(new DetalleVenta(codigoVenta, total, localDate, puntoVenta, modelo, nombreProducto, precio, nit, nombreCliente, codigoProducto));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalles;
    }

    public ArrayList<DetalleVenta> listarDetalleVenta() {

        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DETALLES_VENTA);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int codigoVenta = resultSet.getInt("codigo_venta");
                double total = resultSet.getDouble("total");
                Date fecha = resultSet.getDate("fecha");
                int puntoVenta = resultSet.getInt("punto_venta_codigo");
                String modelo = resultSet.getString("modelo");
                String nombreProducto = resultSet.getString("nombre_producto");
                double precio = resultSet.getDouble("precio");
                String nit = resultSet.getString("nit");
                String nombreCliente = resultSet.getString("nombre");
                int codigoProducto = resultSet.getInt("codigo_producto");

                LocalDate localDate;
                if (fecha != null) {
                    localDate = dateManager.convertirALocalDate(fecha);
                } else {
                    localDate = null;
                }

                detalles.add(new DetalleVenta(codigoVenta, total, localDate, puntoVenta, modelo, nombreProducto, precio, nit, nombreCliente, codigoProducto));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalles;
    }
}
