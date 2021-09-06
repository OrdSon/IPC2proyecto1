<%-- 
    Document   : listaClientes
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordson
--%>

<%@page import="Modelos.PuntoVenta"%>
<%@page import="DAO.PuntoVentaDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria sucursales</title>
    </head>
    <body>
        <h1>Lista de puntos de venta</h1>
        <a href="PuntoVentaServlet?accion=nuevo">Añadir punto de venta</a>
        <table border = "1">
            <thead>
                <tr>
                    <th>Codigo</th>
                    <th>Nombre</th>
                    <th>Direccion</th>
                    <th>Telefono</th>
                </tr>
            </thead>
            <%
                
                PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
                ArrayList<PuntoVenta> puntosVenta = puntoVentaDAO.listar();
                Iterator<PuntoVenta> iterator = puntosVenta.iterator();
                PuntoVenta puntoVenta = new PuntoVenta();
                while (iterator.hasNext()) {                        
                        puntoVenta = iterator.next();
                    
            %>
            <tbody>
                <tr>
                    <td><%=puntoVenta.getCodigo()%></td>
                    <td><%=puntoVenta.getNombre()%></td>
                    <td><%=puntoVenta.getTelefono()%></td>
                    <td><%=puntoVenta.getDireccion()%></td>
                    <td>
                        <!-- 
                        EDICION PASO 1
                        Se hace una referencia al objeto PuntoVentaServlet con dos atributos
                        atributo accion = editar, para que me redireccione a editarPuntoVenta.jsp
                        y atributo codigo, que obtiene el codigo del cliente para trabajar con el mas adelante-->
                        <a href="PuntoVentaServlet?accion=editar&codigo=<%=puntoVenta.getCodigo()%>">editar</a>
                        <!--
                        ELIMINACION PASO 1:
                        Se hace una referencia a PuntoVentaServlet con dos atributos
                        accion = eliminar para ejecutar las acciones
                        y codigo = codigo del punto de venta para trabajar con el mas adelante
                        -->
                        <a href="PuntoVentaServlet?accion=eliminar&codigo=<%=puntoVenta.getCodigo()%>">Eliminar</a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
