/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.CajaDAO;
import DAO.ClienteDAO;
import DAO.MovimientoDAO;
import DAO.MuebleDAO;
import DAO.MuebleEnsambladoDAO;
import DAO.VentaDAO;
import Modelos.Caja;
import Modelos.Cliente;
import Modelos.Empleado;
import Modelos.Movimiento;
import Modelos.Mueble;
import Modelos.MuebleEnsamblado;
import Modelos.Venta;
import java.io.IOException;
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
public class VentaServlet extends HttpServlet {

    ClienteDAO clienteDAO = new ClienteDAO();
    CajaDAO cajaDAO = new CajaDAO();
    MuebleDAO muebleDAO = new MuebleDAO();
    VentaDAO ventaDAO = new VentaDAO();
    MovimientoDAO movimientoDAO = new MovimientoDAO();
    MuebleEnsambladoDAO muebleEnsambladoDAO = new MuebleEnsambladoDAO();
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
        String acceso = listar;
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {

        } else if (accion.equalsIgnoreCase("nuevo")) {
            acceso = añadir;
        } else if (accion.equalsIgnoreCase("añadir")) {

        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            VentaDAO ventaDAO = new VentaDAO();
            ventaDAO.eliminar(codigo);
        } else if (accion.equalsIgnoreCase("Buscar")) {
            try {
                String nit = request.getParameter("txtNIT");
                Cliente cliente = clienteDAO.listarNit(nit);
                request.getSession().setAttribute("clienteVentaActiva", cliente);
            } catch (NullPointerException e) {
            }
        } else if (accion.equalsIgnoreCase("Buscar producto")) {
            try {
                String modelo = request.getParameter("txtCodigoProducto");
                Mueble mueble = muebleDAO.listarCodigo(modelo);
                request.getSession().setAttribute("muebleVentaActivo", mueble);
            } catch (NullPointerException e) {
            }
        } else if (accion.equalsIgnoreCase("Buscar por nombre")) {
            try {
                String modelo = request.getParameter("txtNombreProducto");
                Mueble mueble = muebleDAO.listarNombre(modelo);
                request.getSession().setAttribute("muebleVentaActivo", mueble);
            } catch (NullPointerException e) {
            }
        } else if (accion.equalsIgnoreCase("Agregar producto")) {
            ArrayList<MuebleEnsamblado> productos = (ArrayList<MuebleEnsamblado>) request.getSession().getAttribute("listaProductos");
            MuebleEnsamblado ensamblado = crearMueble(request);
            if (productos == null && ensamblado != null) {
                productos = new ArrayList<>();
                productos.add(ensamblado);
            } else if (productos != null && ensamblado != null) {
                productos.add(ensamblado);
            }
            request.getSession().setAttribute("listaProductos", productos);
        } else if (accion.equals("Ingresar venta")) {
            Venta venta = crearVenta(request);
            Caja caja = cajaDAO.listarCodigo(1);
            ArrayList<MuebleEnsamblado> muebles = getLista(request);
            try {
                ventaDAO.nuevaVenta(venta, caja, muebles);
            } catch (Exception e) {
            }
            
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    private  ArrayList<MuebleEnsamblado> getLista(HttpServletRequest request){
        try {
            ArrayList<MuebleEnsamblado> muebles = (ArrayList<MuebleEnsamblado>)request.getSession().getAttribute("listaProductos");
            return  muebles;
        } catch (Exception e) {
        }
        return null;
    }
    private Venta crearVenta(HttpServletRequest request) {
        try {
            Empleado empleado = (Empleado) request.getSession().getAttribute("empleadoActivo");
            double total = getTotal(request);
            if (total == -2 || request.getSession().getAttribute("codigoClienteActivo") == null) {
                return null;
            }
            if (empleado != null) {
                int codigo = (int)request.getSession().getAttribute("codigoClienteActivo");
                System.out.println(codigo+ " ESTE ES EL CODIGO!!");
                LocalDate hoy = LocalDate.now();
                int puntoVenta = 2;
                int empleadoCodigo = empleado.getCodigo();
                Cliente cliente = clienteDAO.listarCodigo(codigo);
                if (cliente!=null && cliente.getCodigo() >= 0) {
                    Venta venta = new Venta(total, hoy, puntoVenta, empleadoCodigo, codigo);
                    System.out.println("CODIGO EXTRAIDO DE LA VENTA CREADA  "+ codigo);

                    return venta;
                }
            }

        } catch (Exception e) {
            System.out.println(e+ "metodo crear venta");
            
        }
        return null;
    }
    
    private Movimiento crearMovimiento(){
        try {
            Caja caja = cajaDAO.listarCodigo(1);
            Venta venta = ventaDAO.listarUltima();
            if (caja != null && venta != null) {
                int ventaCodigo = venta.getCodigo();
                double monto = venta.getTotal();
                double capital = caja.getCapital();
                double resultado = (capital-monto);
                
                Movimiento movimiento = new Movimiento(monto, resultado,0, ventaCodigo);
                return movimiento;
//                if (movimientoDAO.añadir(movimiento)) {
//                    Caja cajaEditada = new Caja(1, resultado);
//                    if (cajaDAO.editar(cajaEditada)) {
//                        return movimiento;
//                    }
//                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private double getTotal(HttpServletRequest request) {
        try {
            ArrayList<MuebleEnsamblado> muebles = (ArrayList<MuebleEnsamblado>) request.getSession().getAttribute("listaProductos");
            if (muebles == null || muebles.isEmpty()) {
                return -1;
            }

            double total = 0;
            for (int i = 0; i < muebles.size(); i++) {
                MuebleEnsamblado temporal = muebles.get(i);
                int cantidad = temporal.getCantidad();
                double precio = temporal.getPrecio();
                total += (cantidad * precio);
            }

            if (total <= 0) {
                return -2;
            }

            return total;
        } catch (Exception e) {
            return -3;
        }

    }

    private MuebleEnsamblado crearMueble(HttpServletRequest request) {
        try {
            String modelo = request.getParameter("txtCodigoProducto");
            MuebleEnsamblado ensamblado = muebleEnsambladoDAO.listarDisponibleModelo(modelo);
            int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
            if (cantidad > muebleEnsambladoDAO.seleccionarCantidad(modelo)) {
                return null;
            }
            ensamblado.setCantidad(cantidad);
            return ensamblado;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
