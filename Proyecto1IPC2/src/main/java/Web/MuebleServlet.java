/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.MuebleDAO;
import Modelos.Mueble;
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
public class MuebleServlet extends HttpServlet {

    String listar = "vistas/mueble/listarMuebles.jsp";
    String añadir = "vistas/mueble/añadirMueble.jsp";
    String editar = "vistas/mueble/editarMueble.jsp";

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
            String modelo = request.getParameter("txtModelo");
            String nombre = request.getParameter("txtNombre");
            String txtPrecio = request.getParameter("txtPrecio");
            String txtCosto = request.getParameter("txtCosto");
            
            double precio = Double.parseDouble(txtPrecio);
            double costo = Double.parseDouble(txtCosto);
            
            Mueble mueble = new Mueble(modelo, nombre, precio, costo);
            MuebleDAO muebleDAO = new MuebleDAO();
            muebleDAO.añadir(mueble);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoMueble"
              para poder identificar al mueble en la transaccion,
              luego se redirige a editarMueble.jsp
             */
        } else if (accion.equalsIgnoreCase("editar")) {
            request.getSession().setAttribute("codigoMueble", request.getParameter("codigo"));
            acceso = editar;
            /*EDITAR PASO 4 
             Se reciben los campos de texto y se crea un objeto Mueble con esa informacion
             luego se envia este objeto como parametro al metodo muebleDAO.editar
             para ejecutar la transaccion
             */
        } else if (accion.equalsIgnoreCase("actualizar")) {
            System.out.println((String) request.getParameter("txtCodigo") + "  muebleServlet");
            
            String modelo = (String) request.getSession().getAttribute("modeloMueble");
            String nombre = request.getParameter("txtNombre");
            String txtPrecio = request.getParameter("txtPrecio");
            String txtCosto = request.getParameter("txtCosto");
            
            double precio = Double.parseDouble(txtPrecio);
            double costo = Double.parseDouble(txtCosto);
            
            Mueble mueble = new Mueble(modelo, nombre, precio, costo);
            MuebleDAO muebleDAO = new MuebleDAO();
            muebleDAO.editar(mueble);
            acceso = listar;
            /*ELIMINAR PASO 2:
              Se toma el atributo codigo de listaMuebles.jsp
              y se pasa como parametro al metodo MuebleDAO.eliminar();
              y se pasa a eliminar directamente a la base de datos
              esta eliminacion debe ser validada mas tarde por un javaScript
             */
        } else if (accion.equalsIgnoreCase("eliminar")) {
            String modelo = request.getParameter("codigo");
            MuebleDAO muebleDAO = new MuebleDAO();
            muebleDAO.eliminar(modelo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}
