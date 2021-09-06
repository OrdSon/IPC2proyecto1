/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.CajaDAO;
import DAO.ClienteDAO;
import DAO.DevolucionDAO;
import DAO.EmpleadoDAO;
import DAO.LoteVentaDAO;
import DAO.MovimientoDAO;
import DAO.VentaDAO;
import DAO.reporteDAO;
import Modelos.Caja;
import Modelos.Cliente;
import Modelos.DetalleDevolucion;
import Modelos.DetalleVenta;
import Modelos.Devolucion;
import Modelos.Empleado;
import Modelos.Movimiento;
import Modelos.MuebleVendido;
import Modelos.VentaRealizada;
import Reportes.ReporteDevoluciones;
import Reportes.ReporteGanancias;
import Reportes.ReporteMuebles;
import Reportes.ReporteVentas;
import Utilidades.DateManager;
import com.mysql.cj.jdbc.interceptors.SessionAssociationInterceptor;
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
public class ReporteServlet extends HttpServlet {

    String listar = "vistas/reportes/reporteVentas.jsp";
    String listarGanancias = "vistas/reportes/reporteGanancias.jsp";
    String listarMuebles = "vistas/reportes/reporteMuebles.jsp";
    String listarDevoluciones = "vistas/reportes/reporteDevolucion.jsp";
    CajaDAO cajaDAO = new CajaDAO();
    VentaDAO ventaDAO = new VentaDAO();
    reporteDAO reporteDAO = new reporteDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    DateManager dateManager = new DateManager();
    LoteVentaDAO loteVentaDAO = new LoteVentaDAO();
    DevolucionDAO devolucionDAO = new DevolucionDAO();
    ReporteVentas reporteVentas = new ReporteVentas();
    MovimientoDAO movimientoDAO = new MovimientoDAO();
    ReporteMuebles reporteMuebles = new ReporteMuebles();
    ReporteGanancias reporteGanancias = new ReporteGanancias();
    ReporteDevoluciones reporteDevoluciones = new ReporteDevoluciones();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = listar;
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            setReporte(request);
        } else if (accion.equalsIgnoreCase("Ver ventas por fecha")) {
            request.getSession().setAttribute("encabezadoVentas", "reporte de ventas por fecha");
            setReporteFecha(request, null);
        } else if (accion.equalsIgnoreCase("Ver ventas por cliente")) {
            request.getSession().setAttribute("encabezadoVentas", "reporte de ventas por cliente");
            String NIT = request.getParameter("txtNit");
            if (!NIT.isEmpty()) {
                setReporteFecha(request, NIT);
            }
        } else if (accion.equalsIgnoreCase("listarGanancias")) {
            setReporteGanancias(request);
            acceso = listarGanancias;
        } else if (accion.equalsIgnoreCase("Buscar empleado")) {
            Empleado empleado = empleadoDAO.listarDPILike(request.getParameter("txtDPI"));
            if (empleado != null) {
                request.getSession().setAttribute("empleadoReporteActivo", empleado);
            }
            acceso = listarGanancias;
        } else if (accion.equalsIgnoreCase("Ver ganancias por fecha")) {
            setReporteGananciasFecha(request, null);
            acceso = listarGanancias;
        } else if (accion.equalsIgnoreCase("Ver ventas del mejor")) {

            setReporteVendedor(request);

        } else if (accion.equals("Ver mejor empleado")) {
            request.getSession().setAttribute("encabezadoGanancias", "Reporte de empleado con mas ganancias");
            Empleado empleado = reporteDAO.listarMejor();
            if (empleado != null) {
                request.getSession().setAttribute("empleadoReporteActivo", empleado);
                setReporteGananciasFecha(request, empleado.getDpi());
            }
            acceso = listarGanancias;
        } else if (accion.equalsIgnoreCase("Ver ganancias por empleado")) {
            request.getSession().setAttribute("encabezadoGanancias", "Reporte de ganancias por empleado");
            String dpi = request.getParameter("txtDPI");
            if (dpi != null && !dpi.isEmpty()) {
                setReporteGananciasFecha(request, dpi);
            }

            acceso = listarGanancias;
        } else if (accion.equalsIgnoreCase("Buscar cliente")) {
            try {
                String nit = request.getParameter("txtNit");
                Cliente cliente = clienteDAO.listarNit(nit);
                request.getSession().setAttribute("clienteReporteVentasActivo", cliente);
            } catch (NullPointerException e) {
            }

        } else if (accion.equalsIgnoreCase("listarMuebles")) {
            setReporteMuebles(request, 3);
            acceso = listarMuebles;
        } else if (accion.equalsIgnoreCase("mejorMueble")) {
            setReporteMuebles(request, 1);
            acceso = listarMuebles;
        } else if (accion.equalsIgnoreCase("peorMueble")) {
            setReporteMuebles(request, 2);
            acceso = listarMuebles;
        } else if (accion.equalsIgnoreCase("exportar")) {
            reporteVentas.exportarReporte(response, reporteVentas.getReporteVentas(request), "Reporte de ventas");
        } else if (accion.equalsIgnoreCase("exportarMuebles")) {
            reporteMuebles.exportarReporte(response, reporteMuebles.getReporteMuebles(request), "Reporte de muebles");
        } else if (accion.equalsIgnoreCase("exportarGanancias")) {
            reporteGanancias.exportarReporte(response, reporteGanancias.getReporteGanancias(request), "Reporte de ganancias");
        } else if (accion.equalsIgnoreCase("agregarDevolucion")) {
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
            acceso = listarDevoluciones;
        } else if (accion.equalsIgnoreCase("listarDevoluciones") || accion.equalsIgnoreCase("Ver todas las devoluciones")) {
            ArrayList<DetalleDevolucion> devoluciones = devolucionDAO.listarDetalles();
            request.getSession().setAttribute("listaDevoluciones", devoluciones);
            acceso = listarDevoluciones;
        } else if (accion.equalsIgnoreCase("Buscar")) {
            try {
                String nit = request.getParameter("txtNit");
                Cliente cliente = clienteDAO.listarNit(nit);
                request.getSession().setAttribute("clienteDevolucionActivo", cliente);
            } catch (NullPointerException e) {
            }
            acceso = listarDevoluciones;
        } else if (accion.equalsIgnoreCase("Ver devoluciones por fecha")) {
            getDevolucionesFecha(request);
            acceso = listarDevoluciones;
        } else if (accion.equalsIgnoreCase("Ver devoluciones por cliente")) {
            getDevolucionesCliente(request);
            acceso = listarDevoluciones;
        } else if (accion.equals("exportarDevolucion")) {
            reporteDevoluciones.exportarReporte(response, reporteDevoluciones.getReporteDevoluciones(request), "Reporte de devoluciones");
            acceso = listarDevoluciones;
        }
 
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(acceso);
        requestDispatcher.forward(request, response);
    }
//************************************************************************

    private void setReporteGanancias(HttpServletRequest request) {
        request.getSession().setAttribute("encabezadoGanancias", "Reporte de ganancias");
        ArrayList<MuebleVendido> mueblesVendidos = reporteDAO.listarMueblesVendidos();
        if (mueblesVendidos != null) {
            request.getSession().setAttribute("reporteGanancias", mueblesVendidos);
        }
    }

    private void setReporteGananciasFecha(HttpServletRequest request, String dpi) {
        request.getSession().setAttribute("encabezadoGanancias", "Reporte de ganancias por fecha");
        String txtInicial = request.getParameter("inicial");
        String txtFinal = request.getParameter("final");
        LocalDate inicialDate = LocalDate.MIN;
        LocalDate finalDate = LocalDate.now();

        if (!txtInicial.isEmpty()) {
            inicialDate = LocalDate.parse(txtInicial);
        }
        if (!txtFinal.isEmpty()) {
            finalDate = LocalDate.parse(txtFinal);

        }
        Date start = dateManager.convertirADate(inicialDate);
        Date end = dateManager.convertirADate(finalDate);
        ArrayList<MuebleVendido> muebles = reporteDAO.listarMueblesVendidosFECHA(start, end, dpi);
        if (muebles != null) {
            request.getSession().setAttribute("reporteGanancias", muebles);
        }

    }

    private void setReporte(HttpServletRequest request) {
        try {
            ArrayList<VentaRealizada> ventasRealizadas = ventaDAO.listarVentasRealizadas();
            ArrayList<ArrayList<DetalleVenta>> detallesVenta;
            detallesVenta = new ArrayList<>();
            if (ventasRealizadas != null) {
                for (int i = 0; i < ventasRealizadas.size(); i++) {
                    int codigoVenta = ventasRealizadas.get(i).getCodigoVenta();
                    ArrayList<DetalleVenta> detalleTemporal = ventaDAO.listarDetalleVenta(codigoVenta);
                    if (detalleTemporal != null) {
                        detallesVenta.add(detalleTemporal);
                    } else {
                        System.out.println("NULL en detalleTemporal");
                        return;
                    }
                }
                request.getSession().setAttribute("ventasReporte", ventasRealizadas);
                request.getSession().setAttribute("detallesReporte", detallesVenta);
                request.getSession().setAttribute("encabezadoVentas", "Reporte de ventas");

            }

        } catch (Exception e) {
        }
    }

    private void setReporteFecha(HttpServletRequest request, String NIT) {
        try {
            //String NIT = request.getParameter("txtNIT");
            String inicial = request.getParameter("inicial");
            String ultima = request.getParameter("final");
            Date fechaInicial;
            Date fechaFinal;
            if (!inicial.isEmpty()) {
                LocalDate localDate = LocalDate.parse(inicial);
                fechaInicial = dateManager.convertirADate(localDate);
            } else {
                LocalDate localDate = LocalDate.MIN;
                fechaInicial = dateManager.convertirADate(localDate);
            }
            if (!ultima.isEmpty()) {
                LocalDate localDate = LocalDate.parse(ultima);
                fechaFinal = dateManager.convertirADate(localDate);
            } else {
                LocalDate localDate = LocalDate.now();
                fechaFinal = dateManager.convertirADate(localDate);
            }
            ArrayList<VentaRealizada> ventasRealizadas = ventaDAO.listarVentasRealizadasBetween(NIT, fechaInicial, fechaFinal);
            ArrayList<ArrayList<DetalleVenta>> detallesVenta;
            detallesVenta = new ArrayList<>();
            if (ventasRealizadas != null) {
                for (int i = 0; i < ventasRealizadas.size(); i++) {
                    int codigoVenta = ventasRealizadas.get(i).getCodigoVenta();
                    ArrayList<DetalleVenta> detalleTemporal = ventaDAO.listarDetalleVenta(codigoVenta);
                    if (detalleTemporal != null) {
                        detallesVenta.add(detalleTemporal);
                    } else {
                        System.out.println("NULL en detalleTemporal");
                        return;
                    }
                }
                request.getSession().setAttribute("ventasReporte", ventasRealizadas);
                request.getSession().setAttribute("detallesReporte", detallesVenta);
            }

        } catch (Exception e) {
        }
    }

    private void setReporteVendedor(HttpServletRequest request) {
        try {
            Empleado empleado = reporteDAO.listarMejorVendedor();
            if (empleado == null) {
                return;
            }
            request.getSession().setAttribute("encabezadoVentas", "Empleado con mayor cantidad de ventas DPI: " + empleado.getDpi() + "  Nombre: " + empleado.getNombre());
            request.getSession().setAttribute("empleadoReporteActivo", empleado);
            int codigo = empleado.getCodigo();
            ArrayList<VentaRealizada> ventasRealizadas = ventaDAO.listarVentasRealizadasEmpleado(codigo);
            ArrayList<ArrayList<DetalleVenta>> detallesVenta;
            detallesVenta = new ArrayList<>();
            if (ventasRealizadas != null) {
                for (int i = 0; i < ventasRealizadas.size(); i++) {
                    int codigoVenta = ventasRealizadas.get(i).getCodigoVenta();
                    ArrayList<DetalleVenta> detalleTemporal = ventaDAO.listarDetalleVenta(codigoVenta);
                    if (detalleTemporal != null) {
                        detallesVenta.add(detalleTemporal);
                    } else {
                        System.out.println("NULL en reporteVendedor");
                        return;
                    }
                }
                request.getSession().setAttribute("ventasReporte", ventasRealizadas);
                request.getSession().setAttribute("detallesReporte", detallesVenta);
            }

        } catch (Exception e) {
        }
    }

    private void setReporteMuebles(HttpServletRequest request, int eleccion) {
        ArrayList<DetalleVenta> detalleVentas;
        String modelo = "";
        switch (eleccion) {

            case 1:
                detalleVentas = reporteDAO.listarMejorMueble();
                if (!detalleVentas.isEmpty()) {
                    modelo = detalleVentas.get(0).getModelo();
                }
                request.getSession().setAttribute("reporteMuebles", detalleVentas);
                request.getSession().setAttribute("modeloReporteActivo", modelo);
                break;
            case 2:

                detalleVentas = reporteDAO.listarPeorMueble();
                if (!detalleVentas.isEmpty()) {
                    modelo = detalleVentas.get(0).getModelo();
                }
                request.getSession().setAttribute("reporteMuebles", detalleVentas);
                request.getSession().setAttribute("modeloReporteActivo", modelo);
                break;
            default:
                request.getSession().setAttribute("reporteMuebles", reporteDAO.listarDetalleVenta());

                break;
        }
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
        request.getSession().setAttribute("listaDevoluciones", detalleDevolucions);

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
        request.getSession().setAttribute("listaDevoluciones", detalleDevolucions);

    }
}
