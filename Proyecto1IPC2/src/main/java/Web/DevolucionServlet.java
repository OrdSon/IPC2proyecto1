/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.CajaDAO;
import DAO.DevolucionDAO;
import DAO.LoteVentaDAO;
import DAO.MovimientoDAO;
import DAO.MuebleEnsambladoDAO;
import Modelos.Caja;
import Modelos.Devolucion;
import Modelos.Movimiento;
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
    String ventas = "vistas/venta/listarVentasRealizadas.jsp";
    DevolucionDAO devolucionDAO = new DevolucionDAO();
    CajaDAO cajaDAO = new CajaDAO();
    MovimientoDAO movimientoDAO = new MovimientoDAO();
    MuebleEnsambladoDAO muebleEnsambladoDAO = new MuebleEnsambladoDAO();
    LoteVentaDAO loteVentaDAO = new LoteVentaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = ventas;
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("agregar")) {
            try {
                int codigo = Integer.parseInt(request.getParameter("muebleCodigo"));
                if (codigo != 0) {
                    int muebleCodigo = Integer.parseInt(request.getParameter("muebleCodigo"));
                    int ventaCodigo = Integer.parseInt(request.getParameter("ventaCodigo"));
                    double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
                    double total = (precioVenta / 3);
                    LocalDate hoy = LocalDate.now();
                    Caja caja = cajaDAO.listarCodigo(1);
                    double capital = caja.getCapital();
                    double resultado = (capital - total);
                    Caja cajaEditada = new Caja(1, resultado);
                    Movimiento movimiento = new Movimiento(total, resultado, ventaCodigo, ventaCodigo);
                    movimientoDAO.añadir(movimiento);
                    cajaDAO.editar(cajaEditada);
                    Devolucion devolucion = new Devolucion(hoy, total, muebleCodigo, ventaCodigo);
                    devolucionDAO.añadir(devolucion);
                    loteVentaDAO.eliminar(codigo);
                }
            } catch (NumberFormatException e) {
            }
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
