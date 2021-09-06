/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Empleado;
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
 * @author ordson
 */
public class EmpleadoDAO {

    Connection connection;
    DateManager dateManager = new DateManager();
    private final String SELECCIONAR_EMPLEADOS = "SELECT * FROM empleado";
    private final String SELECCIONAR_EMPLEADO_CODIGO = "SELECT * FROM empleado WHERE codigo = ?";
    private final String SELECCIONAR_EMPLEADO_DPI = "SELECT * FROM empleado WHERE dpi = ?";
    private final String SELECCIONAR_EMPLEADO_LIKE = "SELECT * FROM empleado WHERE dpi LIKE ? ESCAPE '!' LIMIT 1";
    private final String INSERTAR_EMPLEADO = "INSERT INTO empleado(nombre, area, contraseña, dpi, "
            + "telefono, direccion, fecha_nacimiento, salario, fecha_contratacion) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_EMPLEADO = "UPDATE empleado SET nombre = ?, area = ?, contraseña = ?, dpi = ?, telefono = ?, "
            + "direccion = ?, fecha_nacimiento = ?, salario = ?, fecha_contratacion  = ?WHERE codigo = ?";
    private final String CANCELAR_EMPLEADO = "UPDATE empleado SET area = 61 WHERE codigo = ?";
    private final String ELIMINAR_EMPLEADO = "DELETE FROM empleado WHERE codigo = ?";

    public EmpleadoDAO() {
        this.connection = Conexion.getConnection();
    }

    public ArrayList<Empleado> listar() {

        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_EMPLEADOS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");
                int area = resultSet.getInt("area");
                String contraseña = resultSet.getString("contraseña");
                String dpi = resultSet.getString("dpi");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                Date fechaNacimientoSql = resultSet.getDate("fecha_nacimiento");
                String salario = resultSet.getString("salario");
                Date fechaContratacionSql = resultSet.getDate("fecha_contratacion");

                LocalDate fechaNacimiento = dateManager.convertirALocalDate(fechaNacimientoSql);
                LocalDate fechaContratacion = dateManager.convertirALocalDate(fechaContratacionSql);

                empleados.add(new Empleado(codigo, nombre, area, contraseña, dpi, telefono, direccion, fechaNacimiento, salario, fechaContratacion));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleados;
    }

    public Empleado listarCodigo(int codigo) {

        Empleado empleado = new Empleado();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_EMPLEADO_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                String nombre = resultSet.getString("nombre");
                int area = resultSet.getInt("area");
                String contraseña = resultSet.getString("contraseña");
                String dpi = resultSet.getString("dpi");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                Date fechaNacimientoSql = resultSet.getDate("fecha_nacimiento");
                String salario = resultSet.getString("salario");
                Date fechaContratacionSql = resultSet.getDate("fecha_contratacion");

                LocalDate fechaNacimiento = dateManager.convertirALocalDate(fechaNacimientoSql);
                LocalDate fechaContratacion = dateManager.convertirALocalDate(fechaContratacionSql);

                empleado = new Empleado(codigo, nombre, area, contraseña, dpi, telefono, direccion, fechaNacimiento, salario, fechaContratacion);
            }
            return empleado;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return null;
    }

    public Empleado listarDPI(String dpi) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_EMPLEADO_DPI);
            preparedStatement.setString(1, dpi);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");
                int area = resultSet.getInt("area");
                String contraseña = resultSet.getString("contraseña");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                Date fechaNacimientoSql = resultSet.getDate("fecha_nacimiento");
                String salario = resultSet.getString("salario");
                Date fechaContratacionSql = resultSet.getDate("fecha_contratacion");

                LocalDate fechaNacimiento = dateManager.convertirALocalDate(fechaNacimientoSql);
                LocalDate fechaContratacion = dateManager.convertirALocalDate(fechaContratacionSql);

                return new Empleado(codigo, nombre, area, contraseña, dpi, telefono, direccion, fechaNacimiento, salario, fechaContratacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Empleado listarDPILike(String dpi) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_EMPLEADO_LIKE);
            dpi = dpi.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
            preparedStatement.setString(1, dpi + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                String dpiFull = resultSet.getString("dpi");
                String nombre = resultSet.getString("nombre");
                int area = resultSet.getInt("area");
                String contraseña = resultSet.getString("contraseña");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                Date fechaNacimientoSql = resultSet.getDate("fecha_nacimiento");
                String salario = resultSet.getString("salario");
                Date fechaContratacionSql = resultSet.getDate("fecha_contratacion");

                LocalDate fechaNacimiento = dateManager.convertirALocalDate(fechaNacimientoSql);
                LocalDate fechaContratacion = dateManager.convertirALocalDate(fechaContratacionSql);

                return new Empleado(codigo, nombre, area, contraseña, dpiFull, telefono, direccion, fechaNacimiento, salario, fechaContratacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean editar(Empleado empleado) {

        try {
            if (listarDPI(empleado.getDpi()) != null) {
                return false;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLEADO);
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setInt(2, empleado.getArea());
            preparedStatement.setString(3, empleado.getContraseña());
            preparedStatement.setString(4, empleado.getDpi());
            preparedStatement.setString(5, empleado.getTelefono());
            preparedStatement.setString(6, empleado.getDireccion());
            preparedStatement.setDate(7, dateManager.convertirADate(empleado.getFecha_nacimiento()));
            preparedStatement.setString(8, empleado.getSalario());
            preparedStatement.setDate(9, dateManager.convertirADate(empleado.getFecha_contratacion()));
            preparedStatement.setInt(10, empleado.getCodigo());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public boolean añadir(Empleado empleado) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_EMPLEADO);
            if (listarDPI(empleado.getDpi()) != null) {
                return false;
            }
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setInt(2, empleado.getArea());
            preparedStatement.setString(3, empleado.getContraseña());
            preparedStatement.setString(4, empleado.getDpi());
            preparedStatement.setString(5, empleado.getTelefono());
            preparedStatement.setString(6, empleado.getDireccion());
            preparedStatement.setDate(7, dateManager.convertirADate(empleado.getFecha_nacimiento()));
            preparedStatement.setString(8, empleado.getSalario());
            preparedStatement.setDate(9, dateManager.convertirADate(empleado.getFecha_contratacion()));

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean eliminar(int codigo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_EMPLEADO);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    public boolean cancelar(int codigo) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(CANCELAR_EMPLEADO);
            preparedStatement.setInt(1, codigo);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int verificarUsuario(String dpi, String contraseña) {
        Empleado temporal = listarDPI(dpi);
        if (temporal == null) {
            System.out.println("empleado inexistente");
            return -1;
        } else if (temporal.getContraseña().equals(contraseña)) {
            return temporal.getArea();
        }
        System.out.println("no hubo");
        return -1;
    }

}
