
package DAO;

import Modelos.Cliente;
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
 * @author ordson
 */
public class ClienteDAO  {
    
    
    Connection connection;
    
    private static final String SELECCIONAR_CLIENTES = "SELECT * FROM cliente";
    private static final String SELECCIONAR_CLIENTE_CODIGO = "SELECT * FROM cliente WHERE codigo = ?";
    private static final String SELECCIONAR_CLIENTE_NIT = "SELECT * FROM cliente WHERE nit = ?";
    private static final String INSERTAR_CLIENTE = "INSERT INTO cliente (nit, nombre, telefono, direccion) VALUES (?,?,?,?) ";
    private static final String UPDATE_CLIENTE = "UPDATE cliente SET nit = ?, nombre = ?, telefono = ?, direccion = ? WHERE codigo = ?";
    private static final String ELIMINAR_CLIENTE = "DELETE FROM cliente WHERE codigo = ?";
    
    
    public ClienteDAO() {
        this.connection = Conexion.getConnection();
    }
    /*LISTAR
    crea una lista de objetos Cliente que almacena la informacion
    de todos los clientes en la base de datos, luego exporta esa lista para
    su utilizacion futura
    */
    public ArrayList<Cliente> listar(){
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_CLIENTES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {         
                
                int codigo = resultSet.getInt("codigo");
                String nit = resultSet.getString("nit");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                
                clientes.add( new Cliente(codigo,nit, nombre, telefono, direccion));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    
    /*
    LISTAR CODIGO
    Usa el codigo de un cliente para encontrarlo y exportar un objeto Cliente
    con sus datos
    */
    public Cliente listarCodigo(int codigo){
        
        Cliente cliente = new Cliente();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_CLIENTE_CODIGO);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {         
                

                String nit = resultSet.getString("nit");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                
                cliente = new Cliente(nit, nombre, telefono, direccion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    /*
    EDITAR
    recibe un cliente como atributo, en este caso sera un cliente cuyos datos
    sean diferentes a los encontrados en la base de datos, salvo por el codigo
    con dicho codigo y con los nuevos datos, se procede a editar el registro
    */
    public boolean editar(Cliente cliente){
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENTE);
            cliente.print();
            preparedStatement.setString(1, cliente.getNit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getTelefono());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.setInt(5, cliente.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            
            System.out.println(ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    /*
    AÑADIR
    Recibe un cliente como parametro y usa la informacion que almacena
    para crear un nuevo cliente en la base de datos
    */
    public boolean añadir(Cliente cliente){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_CLIENTE);
            preparedStatement.setString(1, cliente.getNit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getTelefono());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    /*
    ELIMINAR
    Toma el codigo de un cliente y lo usa para encontrar al objetivo
    y eliminarlo de la base de datos
    */
    public boolean eliminar(int codigo){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ELIMINAR_CLIENTE);
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
}
