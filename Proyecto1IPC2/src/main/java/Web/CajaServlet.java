/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.CajaDAO;
import Modelos.Caja;
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
public class CajaServlet extends HttpServlet {

    String listar = "vistas/caja/listarCajas.jsp";
    String añadir = "vistas/caja/añadirCaja.jsp";
    String editar = "vistas/caja/editarCaja.jsp";

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

            String txtCapital = request.getParameter("txtCapital");
            String txtPuntoVenta = request.getParameter("puntoVenta");

            Double capital = Double.parseDouble(txtCapital);
            int puntoVenta = Integer.parseInt(txtPuntoVenta);

            Caja caja = new Caja(capital, puntoVenta);
            CajaDAO cajaDAO = new CajaDAO();
            cajaDAO.añadir(caja);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoCaja"
              para poder identificar al cliente en la transaccion,
              luego se redirige a editarCaja.jsp
             */
        } else if (accion.equalsIgnoreCase("editar")) {
            request.getSession().setAttribute("codigoCaja", request.getParameter("codigo"));
            acceso = editar;
            /*EDITAR PASO 4 
             Se reciben los campos de texto y se crea un objeto Caja con esa informacion
             luego se envia este objeto como parametro al metodo CajaDAO.editar
             para ejecutar la transaccion
             */
        } else if (accion.equalsIgnoreCase("actualizar")) {
            System.out.println((String) request.getParameter("txtCodigo") + "  clienteServlet");
            int codigo = Integer.parseInt((String) request.getSession().getAttribute("codigoCaja"));
            
            String txtCapital = request.getParameter("txtCapital");
            String txtPuntoVenta = request.getParameter("puntoVenta");

            Double capital = Double.parseDouble(txtCapital);
            int puntoVenta = Integer.parseInt(txtPuntoVenta);
            Caja caja = new Caja(codigo, capital, puntoVenta);
            CajaDAO cajaDAO = new CajaDAO();
            cajaDAO.editar(caja);
            acceso = listar;
            /*ELIMINAR PASO 2:
              Se toma el atributo codigo de listaCajas.jsp
              y se pasa como parametro al metodo cajaDAO.eliminar();
              y se pasa a eliminar directamente a la base de datos
              esta eliminacion debe ser validada mas tarde por un javaScript
             */
        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            CajaDAO cajaDAO = new CajaDAO();
            cajaDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

}
