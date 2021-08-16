/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.ClienteDAO;
import Modelos.Cliente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ordson
 */
public class ClienteServlet extends HttpServlet {

    String listar = "vistas/cliente/listaClientes.jsp";
    String añadir = "vistas/cliente/añadirCliente.jsp";
    String editar = "vistas/cliente/editarCliente.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            acceso = listar;
        } else if (accion.equalsIgnoreCase("nuevo")) {
            acceso = añadir;
        } else if (accion.equalsIgnoreCase("añadir")) {
            String nit = request.getParameter("txtNit");
            String nombre = request.getParameter("txtNombre");
            String telefono = request.getParameter("txtTelefono");
            String direccion = request.getParameter("txtDireccion");
            Cliente cliente = new Cliente(nit, nombre, telefono, direccion);
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.añadir(cliente);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoCliente"
              para poder identificar al cliente en la transaccion,
              luego se redirige a editarCliente.jsp
            */
        }else if(accion.equalsIgnoreCase("editar")){
            request.getSession().setAttribute("codigoCliente", request.getParameter("codigo"));
            acceso = editar;
            /*EDITAR PASO 4 
             Se reciben los campos de texto y se crea un objeto Cliente con esa informacion
             luego se envia este objeto como parametro al metodo clienteDAO.editar
             para ejecutar la transaccion
            */
        }else if (accion.equalsIgnoreCase("actualizar")) {
            System.out.println((String)request.getParameter("txtCodigo")+"  clienteServlet");
            int codigo = Integer.parseInt((String)request.getSession().getAttribute("codigoCliente"));
            String nit = request.getParameter("txtNit");
            String nombre = request.getParameter("txtNombre");
            String telefono = request.getParameter("txtTelefono");
            String direccion = request.getParameter("txtDireccion");
            Cliente cliente = new Cliente(codigo, nit, nombre, telefono, direccion);
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.editar(cliente);
            acceso = listar;
            /*ELIMINAR PASO 2:
              Se toma el atributo codigo de listaClientes.jsp
              y se pasa como parametro al metodo ClienteDAO.eliminar();
              y se pasa a eliminar directamente a la base de datos
              esta eliminacion debe ser validada mas tarde por un javaScript
            */
        }else if (accion.equalsIgnoreCase("eliminar")){
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
