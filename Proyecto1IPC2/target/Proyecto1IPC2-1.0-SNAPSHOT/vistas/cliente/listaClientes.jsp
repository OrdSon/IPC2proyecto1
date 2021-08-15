<%-- 
    Document   : listaClientes
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordso
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.ClienteDAO"%>
<%@page import="Modelos.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de clientes</h1>
        <table border = "1">
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
                    <td><%=cliente.getNit()%></td>
                    <td><%=cliente.getNombre()%></td>
                    <td><%=cliente.getTelefono()%></td>
                    <td><%=cliente.getDireccion()%></td>
                    <td>
                        <a>editar</a>
                        <a>remover</a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
