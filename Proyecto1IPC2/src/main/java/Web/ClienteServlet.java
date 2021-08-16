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
 * @author ordso
 */
public class ClienteServlet extends HttpServlet {

    String listar = "vistas/cliente/listaClientes.jsp";
    String añadir = "vistas/cliente/añadirCliente.jsp";
    String editar = "";

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
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
