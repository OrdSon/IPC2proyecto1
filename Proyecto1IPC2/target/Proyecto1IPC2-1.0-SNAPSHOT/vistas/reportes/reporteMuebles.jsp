<%-- 
    Document   : listarVentasRealizadas
    Created on : 2/09/2021, 07:10:11 PM
    Author     : OrdSon
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="Modelos.MuebleVendido"%>
<%@page import="Modelos.Empleado"%>
<%@page import="Modelos.DetalleVenta"%>
<%@page import="Modelos.Cliente"%>
<%@page import="Modelos.VentaRealizada"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.VentaDAO"%>
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
        <h1 class="text-center"> Reporte de muebles</h1>
        <div style="height: 50px"></div>

        <!--<a href="DiseñoServlet?accion=nuevo ">Añadir diseño</a> -->

        <div class="d-flex justify-content-center">


            <%
                String modelo = (String) request.getSession().getAttribute("modeloReporteActivo");
                if (modelo == null) {
                    modelo = "";
                }


            %>
            <div class="card col-sm-2">
                <div class="card-body">
                    <form action="ReporteServlet">

                        <div class="justify-content-center">
                            <label class="text-center">Elegir reporte:</label><br>
                            <div class="list-group d-flex justify-content-center">
                                <a href="ReporteServlet?accion=listarMuebles" class="list-group-item list-group-item-primary">Muebles vendidos</a>
                                <a href="ReporteServlet?accion=mejorMueble" class="list-group-item list-group-item-secondary">Mueble más vendido</a>
                                <a href="ReporteServlet?accion=peorMueble" class="list-group-item list-group-item-primary">Mueble menos vendido</a>
                                <a href="ReporteServlet?accion=exportarMuebles" class="list-group-item list-group-item-info">Exportar tabla</a>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-8" style="position: relative; height: 500px;  overflow: auto; display: block;">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Codigo de venta</th>
                            <th>Fecha</th>
                            <th>Codigo de producto</th>
                            <th>Modelo</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>NIT del cliente</th>
                            <th>Nombre del cliente</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%if (request.getSession().getAttribute("reporteMuebles")!=null) {
                            ArrayList<DetalleVenta> detallesVenta = (ArrayList<DetalleVenta>) request.getSession().getAttribute("reporteMuebles");
                                for (int i = 0; i < detallesVenta.size(); i++) {
                                        DetalleVenta temporal = detallesVenta.get(i);%>
                                        
                                        <tr>
                                            <td><%=temporal.getCodigoVenta() %></td>
                                            <td><%=temporal.getFecha()%></td>
                                            <td><%=temporal.getCodigoProducto() %></td>
                                            <td><%=temporal.getModelo()%></td>
                                            <td><%=temporal.getNombreProducto()%></td>
                                            <td><%=temporal.getPrecio()%></td>
                                            <td><%=temporal.getNit()%></td>
                                            <td><%=temporal.getNombreCliente()%></td>
                                        </tr>
                                        
                                    <%}
                            
                            }%>
                    </tbody>
                </table>

            </div>  

        </div>
        <div class="d-flex justify-content-center">
            
        </div>
    </body>
</html>
