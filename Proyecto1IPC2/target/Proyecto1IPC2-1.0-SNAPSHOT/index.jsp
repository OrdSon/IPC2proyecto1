<%-- 
    Document   : index
    Created on : 14/08/2021, 06:48:02 PM
    Author     : ordson
--%>

<%@page import="java.sql.Connection"%>
<%@page import="Utilidades.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
            /*ESTABLECE LA CONEXION CON LA BASE DE DATOS*/
            Conexion conexion = new Conexion();
        %>
        <h1>Hello World! from jsp</h1>
        
        
        
        <a href="ClienteServlet?accion=listar">Mostrar clientes</a><br>
        <a href="PuntoVentaServlet?accion=listar">Mostrar puntos de venta</a><br>
        <a href="EmpleadoServlet?accion=listar">Mostrar empleados</a>
    </body>
</html>
