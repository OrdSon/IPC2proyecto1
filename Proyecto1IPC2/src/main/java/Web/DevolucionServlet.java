/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.CajaDAO;
import DAO.ClienteDAO;
import DAO.DevolucionDAO;
import DAO.LoteVentaDAO;
import DAO.MovimientoDAO;
import DAO.MuebleEnsambladoDAO;
import Modelos.Caja;
import Modelos.Cliente;
import Modelos.DetalleDevolucion;
import Modelos.Devolucion;
import Modelos.Movimiento;
import Reportes.ReporteDevoluciones;
import Utilidades.DateManager;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    DateManager dateManager = new DateManager();
    CajaDAO cajaDAO = new CajaDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    MovimientoDAO movimientoDAO = new MovimientoDAO();
    MuebleEnsambladoDAO muebleEnsambladoDAO = new MuebleEnsambladoDAO();
    LoteVentaDAO loteVentaDAO = new LoteVentaDAO();
    ReporteDevoluciones reporte = new ReporteDevoluciones();

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
        } else if (accion.equalsIgnoreCase("listar") || accion.equalsIgnoreCase("Ver todas las devoluciones")) {
            ArrayList<DetalleDevolucion> devoluciones = devolucionDAO.listarDetalles();
            request.setAttribute("listaDevoluciones", devoluciones);
            acceso = listar;
        } else if (accion.equalsIgnoreCase("Buscar cliente")) {
            try {
                String nit = request.getParameter("txtNit");
                Cliente cliente = clienteDAO.listarNit(nit);
                request.setAttribute("clienteDevolucionActivo", cliente);
            } catch (NullPointerException e) {
            }
            acceso = listar;
        } else if (accion.equalsIgnoreCase("Ver devoluciones por fecha")) {
            getDevolucionesFecha(request);
            acceso = listar;
        } else if (accion.equalsIgnoreCase("Ver devoluciones por cliente")) {
            getDevolucionesCliente(request);
            acceso = listar;
        }else if (accion.equals("exportar")) {
            reporte.exportarReporte(response, reporte.getReporteDevoluciones(request), "Reporte de devoluciones");
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void getDevolucionesFecha(HttpServletRequest request) {

        String fechaInicio = request.getParameter("inicial");
        String fechaFinal = request.getParameter("final");
        Date inicio;
        Date fin;

        if (fechaInicio.isEmpty()) {
            inicio = dateManager.convertirADate(LocalDate.MIN);
        } else {
            try {
                inicio = dateManager.formatear(fechaInicio);
            } catch (ParseException e) {
                inicio = dateManager.convertirADate(LocalDate.MIN);
            }
        }
        if (fechaFinal.isEmpty()) {
            fin = dateManager.convertirADate(LocalDate.now());
        } else {
            try {
                fin = dateManager.formatear(fechaFinal);
            } catch (ParseException e) {
                fin = dateManager.convertirADate(LocalDate.now());
            }
        }
        ArrayList<DetalleDevolucion> detalleDevolucions = devolucionDAO.listarDetallesBetween(inicio, fin, null);
        request.setAttribute("listaDevoluciones", detalleDevolucions);

    }

    private void getDevolucionesCliente(HttpServletRequest request) {
        String nit = request.getParameter("txtNit");
        if (nit == null || nit.isEmpty()) {
            return;
        }
        String fechaInicio = request.getParameter("inicial");
        String fechaFinal = request.getParameter("final");
        Date inicio;
        Date fin;

        if (fechaInicio.isEmpty()) {
            inicio = dateManager.convertirADate(LocalDate.MIN);
        } else {
            try {
                inicio = dateManager.formatear(fechaInicio);
            } catch (ParseException e) {
                inicio = dateManager.convertirADate(LocalDate.MIN);
            }
        }
        if (fechaFinal.isEmpty()) {
            fin = dateManager.convertirADate(LocalDate.now());
        } else {
            try {
                fin = dateManager.formatear(fechaFinal);
            } catch (ParseException e) {
                fin = dateManager.convertirADate(LocalDate.now());
            }
        }
        ArrayList<DetalleDevolucion> detalleDevolucions = devolucionDAO.listarDetallesBetween(inicio, fin, nit);
        request.setAttribute("listaDevoluciones", detalleDevolucions);

    }
}
