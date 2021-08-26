/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;


import DAO.VentaDAO;
import Modelos.Venta;
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
public class VentaServlet extends HttpServlet {

    String listar = "vistas/venta/listarVentas.jsp";
    String añadir = "vistas/venta/añadirVenta.jsp";
    
    
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

            String txtTotal = request.getParameter("txtCapital");
            String txtFecha = (String)request.getSession().getAttribute("fecha");
            String txtPuntoVenta = request.getParameter("txtPuntoVenta");
            String txtEmpleado = request.getParameter("txtEmpleado");
            String txtCliente = request.getParameter("txtCliente");
            
            double total = Double.parseDouble(txtTotal);
            LocalDate fecha = LocalDate.parse(txtFecha);
            int puntoVenta = Integer.parseInt(txtPuntoVenta);
            int empleadoCodigo = Integer.parseInt(txtEmpleado);
            int clienteCodigo = Integer.parseInt(txtCliente);
            
            Venta caja = new Venta(total, fecha, puntoVenta, empleadoCodigo, clienteCodigo);
            VentaDAO ventaDAO = new VentaDAO();
            ventaDAO.añadir(caja);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoCaja"
              para poder identificar al cliente en la transaccion,
              luego se redirige a editarCaja.jsp
             */
        }else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            VentaDAO ventaDAO = new VentaDAO();
            ventaDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

}
