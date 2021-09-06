<%-- 
    Document   : Detalle
    Created on : 2/09/2021, 10:20:21 PM
    Author     : OrdSon
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelos.DetalleVenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("detalleActivo") != null) {
                ArrayList<DetalleVenta> detalles = (ArrayList<DetalleVenta>) request.getSession().getAttribute("detalleActivo");
                if (!detalles.isEmpty()) {


        %>


        <div class="text-center">
            <h1>Detalle de venta No.<%=detalles.get(0).getCodigoVenta()%></h1>
            <label class="h3">Cliente:<%=detalles.get(0).getNombreCliente()%></label> <br>
            <label class="h3">NIT:<%=detalles.get(0).getNit()%></label> <br>
            <label class="h4">Fecha:<%=detalles.get(0).getFecha()%></label> <br>

            <div class="d-flex justify-content-center">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Codigo de Producto</th>
                            <th>Modelo</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (int i = 0; i < detalles.size(); i++) {%>
                        <tr>
                            <td><%=detalles.get(i).getCodigoProducto()%></td>
                            <td><%=detalles.get(i).getModelo()%></td>
                            <td><%=detalles.get(i).getNombreProducto()%></td>
                            <td><%=detalles.get(i).getPrecio()%></td>
                            <td><a href="DevolucionServlet?accion=agregar&muebleCodigo=<%=detalles.get(i).getCodigoProducto()%>&ventaCodigo=<%=detalles.get(i).getCodigoVenta()%>&precioVenta=<%=detalles.get(i).getPrecio()%>">Devolver</a></td>
                        </tr>
                        <% }
                        %>

                    </tbody>
                </table>

            </div>
        </div>


        <%} else {%>
        <div class="d-flex justify-content-center">
            <label class="h1">INFORMACIÓN NO DISPONIBLE</label><br>
        </div>
        <%}
            }
        %>
        <div style="height: 5vh;">
            
        </div>
        <div class="d-flex justify-content-center">
            <a href="VentaServlet?accion=listarRealizadas" class="btn btn-primary">Volver</a> 
        </div>
    </body>
</html>
