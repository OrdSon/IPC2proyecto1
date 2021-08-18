<%-- 
    Document   : listaEmpleados
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordson
--%>

<%@page import="Modelos.Empleado"%>
<%@page import="DAO.EmpleadoDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria clientes</title>
    </head>
    <body>
        <h1>Lista de empleados</h1>
        <a href="EmpleadoServlet?accion=nuevo">Añadir Empleado</a>
        <table border = "1">
            <thead>
                <tr>
                    <th>Codigo</th>
                    <th>Nombre</th>
                    <th>Area</th>
                    <th>DPI</th>
                    <th>Telefono</th>
                    <th>Direccion</th>
                    <th>Salario</th>
                    <th>fecha_nacimiento</th>
                    <th>fecha_contratacion</th>
                </tr>
            </thead>
            <%
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                ArrayList<Empleado> clientes = empleadoDAO.listar();
                Iterator<Empleado> iterator = clientes.iterator();
                Empleado empleado = null;
                while (iterator.hasNext()) {                        
                        empleado = iterator.next();
                    
            %>
            <tbody>
                <tr>
                    <td><%=empleado.getCodigo()%></td>
                    <td><%=empleado.getNombre()%></td>
                    <td><%=empleado.getArea()%></td>
                    <td><%=empleado.getDpi()%></td>
                    <td><%=empleado.getTelefono()%></td>
                    <td><%=empleado.getDireccion()%></td>
                    <td><%=empleado.getSalario()%></td>
                    <td><%=empleado.getFecha_nacimiento()%></td>
                    <td><%=empleado.getFecha_contratacion()%></td>
                    
                    <td>

                        <a href="EmpleadoServlet?accion=editar&codigo=<%=empleado.getCodigo()%>">editar</a>

                        <a href="EmpleadoServlet?accion=eliminar&codigo=<%=empleado.getCodigo()%>">Eliminar</a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
