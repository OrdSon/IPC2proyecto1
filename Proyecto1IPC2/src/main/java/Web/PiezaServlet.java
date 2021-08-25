/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.PiezaDAO;
import Modelos.Pieza;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OrdSon
 */
public class PiezaServlet extends HttpServlet {
    String listar = "vistas/pieza/listaPiezas.jsp";
    String añadir = "vistas/pieza/añadirPiezas.jsp";
    String editar = "vistas/pieza/editarPiezas.jsp";

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

            String nombre = request.getParameter("txtNombre");

            Pieza pieza = new Pieza(nombre);
            PiezaDAO piezaDAO = new PiezaDAO();
            piezaDAO.añadir(pieza);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoPieza"
              para poder identificar la pieza en la transaccion,
              luego se redirige a editarPieza.jsp
             */
        } else if (accion.equalsIgnoreCase("editar")) {
            request.getSession().setAttribute("codigoPieza", request.getParameter("codigoPieza"));
            acceso = editar;
            /*EDITAR PASO 4 
             Se reciben los campos de texto y se crea un objeto pieza con esa informacion
             luego se envia este objeto como parametro al metodo piezaDAO.editar
             para ejecutar la transaccion
             */
        } else if (accion.equalsIgnoreCase("actualizar")) {

            int codigo = Integer.parseInt((String) request.getSession().getAttribute("codigoPieza"));

            String nombre = request.getParameter("txtNombre");

            Pieza pieza = new Pieza(nombre);
            PiezaDAO piezaDAO = new PiezaDAO();
            piezaDAO.editar(pieza);
            acceso = listar;
            /*ELIMINAR PASO 2:
              Se toma el atributo codigo de listapieza.jsp
              y se pasa como parametro al metodo piezaDAO.eliminar();
              y se pasa a eliminar directamente a la base de datos
              esta eliminacion debe ser validada mas tarde por un javaScript
             */
        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            PiezaDAO piezaDAO = new PiezaDAO();
            piezaDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

}
