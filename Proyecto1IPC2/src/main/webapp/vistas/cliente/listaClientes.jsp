<%-- 
    Document   : listaClientes
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordson
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.ClienteDAO"%>
<%@page import="Modelos.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria clientes</title>
    </head>
    <body>
        <h1>Lista de clientes</h1>

        <div class="d-flex justify-content-center">

            <div class="card" style="height: 90%; width: 80%;">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>NIT</th>
                            <th>Nombre</th>
                            <th>Telefono</th>
                            <th>Direccion</th>
                        </tr>
                    </thead>
                    <%
                        ClienteDAO clienteDAO = new ClienteDAO();
                        ArrayList<Cliente> clientes = clienteDAO.listar();
                        Iterator<Cliente> iterator = clientes.iterator();
                        Cliente cliente = null;
                        while (iterator.hasNext()) {
                            cliente = iterator.next();

                    %>
                    <tbody>
                        <tr>
                            <td><%=cliente.getCodigo()%></td>
                            <td><%=cliente.getNit()%></td>
                            <td><%=cliente.getNombre()%></td>
                            <td><%=cliente.getTelefono()%></td>
                            <td><%=cliente.getDireccion()%></td>
                            <td>
                                <!-- 
                                EDICION PASO 1
                                Se hace una referencia al objeto ClienteServlet con dos atributos
                                atributo accion = editar, para que me redireccione a editarCliente.jsp
                                y atributo codigo, que obtiene el codigo del cliente para trabajar con el mas adelante-->
                                <a href="ClienteServlet?accion=editar&codigo=<%=cliente.getCodigo()%>" class="btn btn-light">editar</a>
                                <!--
                                ELIMINACION PASO 1:
                                Se hace una referencia a ClienteServlet con dos atributos
                                accion = eliminar para ejecutar las acciones
                                y codigo = codigo del cliente para trabajar con el mas adelante
                                -->
                                <a href="ClienteServlet?accion=eliminar&codigo=<%=cliente.getCodigo()%>" class="btn btn-warning">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <a href="ClienteServlet?accion=nuevo " class="btn btn-info">Añadir cliente</a>
        </div>
    </body>
</html>
