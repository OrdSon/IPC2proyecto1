/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.CompraDAO;
import DAO.PiezaAlmacenadaDAO;
import DAO.PiezaDAO;
import Modelos.Compra;
import Modelos.Pieza;
import Modelos.PiezaAlmacenada;
import Utilidades.SweetAlert;
import java.io.IOException;
import java.sql.SQLException;
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
public class CompraServlet extends HttpServlet {

    PiezaDAO piezaDAO = new PiezaDAO();
    PiezaAlmacenadaDAO piezaAlmacenadaDAO = new PiezaAlmacenadaDAO();
    CompraDAO compraDAO = new CompraDAO();
    SweetAlert sweetAlert = new SweetAlert();
    String BUSCAR_PIEZA = "vistas/piezaAlmacenada/listarPiezas.jsp";
    String listar = "vistas/compra/listaCompras.jsp";
    String añadir = "vistas/compra/añadirCompra.jsp";

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
            
            String txtCantidad = request.getParameter("txtCantidad");
            int cantidad = Integer.parseInt(txtCantidad);

            Compra compra = crearCompra(request);
            PiezaAlmacenada piezaAlmacenada = crearPieza(request, compra);
            try {
                compraDAO.comprarPiezas(compra, piezaAlmacenada, cantidad);
            } catch (SQLException e) {
                System.out.println("Error creando compras en el servlet");
            }

            acceso = BUSCAR_PIEZA;
        } else if (accion.equalsIgnoreCase("Buscar nombre")) {
            try {
                String nombre = request.getParameter("txtNombre");
                Pieza pieza;
                Pieza temporal = piezaDAO.listarNombre(nombre);
                if (temporal != null) {
                    pieza = temporal;
                } else {
                    pieza = new Pieza(0, "");
                }
                request.getSession().setAttribute("piezaActiva", pieza);
            } catch (NullPointerException e) {
            }
            acceso = BUSCAR_PIEZA;
        } else if (accion.equalsIgnoreCase("Buscar codigo")) {
            try {
                String txtCodigo = request.getParameter("txtModelo");
                int codigo = Integer.parseInt(txtCodigo);
                Pieza pieza;
                Pieza temporal = piezaDAO.listarCodigo(codigo);
                if (temporal != null) {
                    pieza = temporal;
                } else {
                    pieza = null;
                }
                request.getSession().setAttribute("piezaActiva", pieza);
            } catch (NullPointerException e) {
            }
            acceso = BUSCAR_PIEZA;
        }else if (accion.equalsIgnoreCase("desc")) {
            request.getSession().setAttribute("ordenPiezas",1);
            acceso = BUSCAR_PIEZA;
        }else if (accion.equalsIgnoreCase("asc")) {
            request.getSession().setAttribute("ordenPiezas",2);
            acceso = BUSCAR_PIEZA;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    private Compra crearCompra(HttpServletRequest request) {
        try {
            LocalDate fecha = LocalDate.now();
            String txtCantidad = request.getParameter("txtCantidad");
            String txtPrecio = request.getParameter("txtPrecio");
            double precio = Double.parseDouble(txtPrecio);
            int cantidad = Integer.parseInt(txtCantidad);
            double total = (precio * cantidad);
            Compra temporal = compraDAO.listarUltima();
            int codigo = (temporal.getCodigo() + 1);
            Compra compra = new Compra(codigo, fecha, total, 2);
            return compra;
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("Error en crear compra");
        }
        return null;
    }

    private PiezaAlmacenada crearPieza(HttpServletRequest request, Compra compra) {
        try {
            String txtPiezaCodigo = request.getParameter("txtModelo");
            String txtPrecio = request.getParameter("txtPrecio");
            String txtPiezaTipo = request.getParameter("txtNombre");
            int codigo = Integer.parseInt(txtPiezaCodigo);
            int codigoCompra = compra.getCodigo();
            double precio = Double.parseDouble(txtPrecio);
            PiezaAlmacenada piezaAlmacenada = new PiezaAlmacenada(precio, codigo, txtPiezaTipo, codigoCompra);
            return piezaAlmacenada;
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return null;
    }
}
