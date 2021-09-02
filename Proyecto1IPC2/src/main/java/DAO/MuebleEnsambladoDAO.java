/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Coincidencia;
import Modelos.Diseño;
import Modelos.MuebleEnsamblado;
import Modelos.PiezaAlmacenada;
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
public class MuebleEnsambladoDAO {

    Connection connection;
    DiseñoDAO diseñoDAO = new DiseñoDAO();
    DateManager dateManager = new DateManager();
    PiezaAlmacenadaDAO piezaAlmacenadaDAO = new PiezaAlmacenadaDAO();

    private final String SELECCIONAR_MUEBLE = "SELECT * FROM muebles_disponibles";
    private final String SELECCIONAR_MUEBLE_DESC = "SELECT * FROM muebles_disponibles ORDER BY fecha DESC";
    private final String SELECCIONAR_MUEBLE_ASC = "SELECT * FROM muebles_disponibles ORDER BY fecha ASC";
    private final String SELECCIONAR_DISPONIBLES = "SELECT * FROM mueble_venta";
    private final String SELECCIONAR_DISPONIBLE_MODELO = "SELECT * FROM muebles_disponibles WHERE modelo = ?";
    private final String SELECCIONAR_DISPONIBLE_CODIGO = "SELECT * FROM muebles_disponibles WHERE ensamble_codigo = ?";
    private final String SELECCIONAR_MUEBLE_CODIGO = "SELECT * FROM mueble_ensamblado WHERE codigo = ?";
    private final String INSERTAR_MUEBLE = "INSERT INTO mueble_ensamblado (empleado_codigo, punto_venta_codigo, mueble_modelo, fecha) VALUES (?,?,?,?)";
    private final String UPDATE_MUEBLE = "UPDATE mueble_ensamblado SET  empleado_codigo = ?, punto_venta_codigo = ?, mueble_modelo = ? WHERE codigo = ?";
    private final String ELIMINAR_MUEBLE = "DELETE FROM mueble_ensamblado WHERE codigo = ?";
    private final String SELECCIONAR_COINCIDENCIAS = "SELECT * FROM coincidencias WHERE modelo_mueble = ?";
    private final String SELECCIONAR_ULTIMO = "SELECT * FROM mueble_ensamblado ORDER BY codigo DESC LIMIT 1";
    private final String SELECCIONAR_ALGUNOS = "SELECT * FROM mueble_venta WHERE modelo = ? ORDER BY codigo LIMIT ?";
    private final String SELECCIONAR_CANTIDAD = "SELECT disponibles FROM mueble_venta WHERE modelo = ?";

    public MuebleEnsambladoDAO() {
        this.connection = Conexion.getConnection();
    }

    /*LISTAR
    Crea una lista de muebles y la exporta para su uso
     */
    public ArrayList<MuebleEnsamblado> listar(int orden) {
        String query;
        switch (orden) {
            case 1:
                query = SELECCIONAR_MUEBLE_DESC;
                break;
            case 2:
                query = SELECCIONAR_MUEBLE_ASC;
                break;
            default:
                query = SELECCIONAR_MUEBLE;
                break;
        }
        ArrayList<MuebleEnsamblado> muebles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                if (nombre == null) {
                    nombre = "";
                }
                double precio = resultSet.getDouble("precio");
                String modelo = resultSet.getString("modelo");
                double costo = resultSet.getDouble("costo");
                if (costo == 0) {
                    costo = resultSet.getDouble("costo_default");
                }
                int codigo = resultSet.getInt("codigo");
                int empleadoCodigo = resultSet.getInt("empleado_codigo");
                int puntoVenta = resultSet.getInt("punto_venta_codigo");
                Date fecha = resultSet.getDate("fecha");
                LocalDate localDate;
                if (fecha != null) {
                    localDate = dateManager.convertirALocalDate(fecha);
                }else{
                    localDate = null;
                }
                MuebleEnsamblado muebleEnsamblado = new MuebleEnsamblado(codigo, empleadoCodigo, puntoVenta, modelo, 0, precio, costo, localDate, nombre);
                muebles.add(muebleEnsamblado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return muebles;
    }

    public ArrayList<MuebleEnsamblado> listarAlgunos(String modelo, int cantidad) {

        ArrayList<MuebleEnsamblado> muebles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_ALGUNOS);
            preparedStatement.setString(1, modelo);
            preparedStatement.setInt(1, cantidad);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                int codigo = resultSet.getInt("codigo");
                double costo = resultSet.getDouble("costo");
                if (costo == 0) {
                    costo = resultSet.getDouble("costo_default");
                }
                muebles.add(new MuebleEnsamblado(codigo, modelo, nombre, precio, costo));
            }
            return muebles;
        } catch (SQLException ex) {
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Coincidencia> listarPorMueble(String modelo) {

        ArrayList<Coincidencia> coincidencias = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_COINCIDENCIAS);
            preparedStatement.setString(1, modelo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int pieza = resultSet.getInt("pieza");
                String tipo = resultSet.getString("tipo");
                int disponibles = resultSet.getInt("disponibles");
                double costo = resultSet.getInt("costo");
                int necesarias = resultSet.getInt("necesarias");

                coincidencias.add(new Coincidencia(pieza, tipo, disponibles, costo, necesarias));
            }
            return coincidencias;
        } catch (SQLException ex) {
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MuebleEnsamblado> listarDisponibles() {

        ArrayList<MuebleEnsamblado> muebles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DISPONIBLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                int empleadoCodigo = resultSet.getInt("empleado_codigo");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");
                String modelo = resultSet.getString("modelo");
                int cantidad = resultSet.getInt("disponibles");
                double costo = resultSet.getDouble("costo");
                double precio = resultSet.getDouble("precio");
                if (costo == 0) {
                    costo = resultSet.getDouble("costo_default");
                }
                muebles.add(new MuebleEnsamblado(codigo, empleadoCodigo, puntoVentaCodigo, modelo, cantidad, precio, costo));
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

    public MuebleEnsamblado listarDisponibeleCodigo(int codigo) {

        MuebleEnsamblado mueble = new MuebleEnsamblado();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DISPONIBLE_CODIGO);
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

    public MuebleEnsamblado listarDisponibleModelo(String modelo) {

        MuebleEnsamblado mueble;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_DISPONIBLE_MODELO);
            preparedStatement.setString(1, modelo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");
                Double precio = resultSet.getDouble("precio");
                Double costo = resultSet.getDouble("costo");
                if (costo == 0) {
                    costo = resultSet.getDouble("costo_default");
                }
                int empleadoCodigo = resultSet.getInt("empleado_codigo");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");

                mueble = new MuebleEnsamblado(codigo, empleadoCodigo, puntoVentaCodigo, modelo, 0, precio, costo);

                return mueble;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int seleccionarCantidad(String modelo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_CANTIDAD);
            preparedStatement.setString(1, modelo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int cantidad = resultSet.getInt("disponibles");
                return cantidad;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
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
    public boolean añadir(MuebleEnsamblado muebleEnsamblado) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_MUEBLE);
            //"INSERT INTO mueble_ensamblado (empleado_codigo, punto_venta_codigo, mueble_modelo) VALUES (?,?,?)"
            int empleado = muebleEnsamblado.getEmpleadoCodigo();
            String modelo = muebleEnsamblado.getMuebleModelo();
            int punto = 2;
            java.sql.Date fecha = dateManager.convertirADate(muebleEnsamblado.getFecha());
            preparedStatement.setInt(1, empleado);
            preparedStatement.setInt(2, punto);
            preparedStatement.setString(3, modelo);
            preparedStatement.setDate(4, fecha);

            preparedStatement.executeUpdate();
            setearMueble(muebleEnsamblado);
        } catch (SQLException ex) {
            Logger.getLogger(MuebleEnsambladoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
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

    public MuebleEnsamblado listarUltima() {

        MuebleEnsamblado muebleEnsamblado = new MuebleEnsamblado();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_ULTIMO);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                int empleadoCodigo = resultSet.getInt("empleado_codigo");
                int puntoVentaCodigo = resultSet.getInt("punto_venta_codigo");
                String modelo = resultSet.getString("mueble_modelo");
                muebleEnsamblado = new MuebleEnsamblado(codigo, empleadoCodigo, puntoVentaCodigo, modelo);
            }
            return muebleEnsamblado;
        } catch (SQLException ex) {
            Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private boolean setearMueble(MuebleEnsamblado muebleEnsamblado) {
        ArrayList<Diseño> diseños = diseñoDAO.listarPorModelo(muebleEnsamblado.getMuebleModelo());
        if (diseños == null) {
            return false;
        }
        try {
            for (int i = 0; i < diseños.size(); i++) {
                int pieza = diseños.get(i).getPieza();
                int cantidad = diseños.get(i).getCantidad();
                ArrayList<PiezaAlmacenada> piezasAlmacenadas = piezaAlmacenadaDAO.listarPiezas(pieza, cantidad);
                for (int j = 0; j < piezasAlmacenadas.size(); j++) {
                    piezaAlmacenadaDAO.setMueble(listarUltima(), piezasAlmacenadas.get(j));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
