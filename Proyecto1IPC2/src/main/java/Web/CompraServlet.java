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
public class CompraServlet extends HttpServlet {
    PiezaDAO piezaDAO = new PiezaDAO();
    PiezaAlmacenadaDAO piezaAlmacenadaDAO = new PiezaAlmacenadaDAO();
    CompraDAO compraDAO = new CompraDAO();
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
            LocalDate fecha = LocalDate.now();
            String txtPiezaCodigo = request.getParameter("txtModelo");
            String txtPiezaTipo = request.getParameter("txtNombre");
            String txtCantidad = request.getParameter("txtCantidad");
            String txtPrecio = request.getParameter("txtPrecio");
            
            int codigo = Integer.parseInt(txtPiezaCodigo);
            double precio = Double.parseDouble(txtPrecio);
            int cantidad = Integer.parseInt(txtCantidad);
            double total=(precio*cantidad);
            //TEMPORAL puse el punto de venta como 2 para no tener que elegirlo por el momento
            Compra compra = new Compra(fecha, total, 2);
            
            compraDAO.añadir(compra);
            
            Compra temporal = compraDAO.listarUltima();
            int compraCodigo = temporal.getCodigo();
            for (int i = 0; i < cantidad; i++) {
                PiezaAlmacenada pieza = new PiezaAlmacenada(precio, codigo, txtPiezaTipo, compraCodigo);
                piezaAlmacenadaDAO.añadir(pieza);
            }
            acceso = BUSCAR_PIEZA;
        }else if(accion.equalsIgnoreCase("Buscar nombre")){
            try {
                String nombre = request.getParameter("txtNombre");
                Pieza pieza;
                Pieza temporal = piezaDAO.listarNombre(nombre);
                if (temporal != null) {
                    pieza = temporal;
                }else{
                    pieza = new Pieza(0, "");
                }
                request.getSession().setAttribute("piezaActiva", pieza);
            } catch (NullPointerException e) {
            }
            acceso = BUSCAR_PIEZA;
        }else if(accion.equalsIgnoreCase("Buscar codigo")){
            try {
                String txtCodigo = request.getParameter("txtModelo");
                int codigo = Integer.parseInt(txtCodigo);
                Pieza pieza;
                Pieza temporal = piezaDAO.listarCodigo(codigo);
                if (temporal != null) {
                    pieza = temporal;
                }else{
                    pieza = null;
                }
                request.getSession().setAttribute("piezaActiva", pieza);
            } catch (NullPointerException e) {
            }
            acceso = BUSCAR_PIEZA;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

}
