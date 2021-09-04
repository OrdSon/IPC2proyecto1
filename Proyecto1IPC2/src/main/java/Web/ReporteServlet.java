/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.ClienteDAO;
import DAO.EmpleadoDAO;
import DAO.VentaDAO;
import DAO.reporteDAO;
import Modelos.Cliente;
import Modelos.DetalleVenta;
import Modelos.Empleado;
import Modelos.MuebleVendido;
import Modelos.VentaRealizada;
import Utilidades.DateManager;
import java.io.IOException;
import java.sql.Date;
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
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    VentaDAO ventaDAO = new VentaDAO();
    reporteDAO reporteDAO = new reporteDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    DateManager dateManager = new DateManager();

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
        } else if (accion.equalsIgnoreCase("Buscar")) {
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
            if (!dpi.isEmpty()) {
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

        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(acceso);
        requestDispatcher.forward(request, response);
    }

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
            request.getSession().setAttribute("encabezadoVentas", "Empleado con mayor cantidad de ventas DPI: "+empleado.getDpi()+"  Nombre: "+empleado.getNombre());
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

}
