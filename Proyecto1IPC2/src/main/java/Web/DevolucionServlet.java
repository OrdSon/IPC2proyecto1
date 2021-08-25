/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.DevolucionDAO;
import Modelos.Devolucion;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OrdSon
 */
public class DevolucionServlet extends HttpServlet {

    String listar = "vistas/devolucion/listarDevoluciones.jsp";
    String añadir = "vistas/devolucion/añadirDevoluciones.jsp";

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
            String txtFecha = request.getParameter("txtFecha");
            String txtTotal = request.getParameter("txtTotal");
            String txtMueble = request.getParameter("txtMueble");
            String txtVenta = request.getParameter("txtVenta");
            
            LocalDate fecha = LocalDate.parse(txtFecha);
            double total = Double.parseDouble(txtTotal);
            int mueble = Integer.parseInt(txtMueble);
            int venta = Integer.parseInt(txtVenta);
            
            Devolucion devolucion = new Devolucion(fecha, total, mueble, venta);
            DevolucionDAO devolucionDAO = new DevolucionDAO();
            devolucionDAO.añadir(devolucion);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoCliente"
              para poder identificar al cliente en la transaccion,
              luego se redirige a editarCliente.jsp
             */
        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            DevolucionDAO devolucionDAO = new DevolucionDAO();
            devolucionDAO.eliminar(codigo);
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
