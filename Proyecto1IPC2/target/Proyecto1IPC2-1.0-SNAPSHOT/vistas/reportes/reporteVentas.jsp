<%-- 
    Document   : listarVentasRealizadas
    Created on : 2/09/2021, 07:10:11 PM
    Author     : OrdSon
--%>

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
        <h1 class="text-center"> Reporte de ventas</h1>
        <div style="height: 50px"></div>

        <!--<a href="DiseñoServlet?accion=nuevo ">Añadir diseño</a> -->

        <%
            String encabezado = "";
            if (request.getSession().getAttribute("encabezadoVentas") != null) {
                encabezado = (String) request.getSession().getAttribute("encabezadoVentas");
            }%>
            <div class="d-flex justify-content-center">
                <label><%=encabezado %></label>
            </div>
        <div class="d-flex justify-content-center">


            <%
                Cliente clienteTemp = (Cliente) request.getSession().getAttribute("clienteReporteVentasActivo");
                String nitCliente = "";
                String nombreCliente = "";

                if (clienteTemp != null) {
                    nombreCliente = clienteTemp.getNombre();
                    nitCliente = clienteTemp.getNit() + "";
                    request.getSession().setAttribute("codigoClienteActivo", clienteTemp.getCodigo());
                }

            %>
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="ReporteServlet">
                        <div>
                            <label>Buscar cliente</label>
                            <div >
                                <label>NIT:</label> 
                                <input type ="text" name = "txtNit" class="form-control" value="<%=nitCliente%>">      
                                <input type = "submit" name = "accion" value = "Buscar cliente" class="btn btn-outline-primary"><br>

                            </div>
                            <div >

                                <label>Nombre:</label> 
                                <input type ="text" name = "txtNombreCliente" class="form-control" value="<%=nombreCliente%>">
                            </div>
                        </div>
                        <div>
                            <label class="h2">Fecha:</label>
                            <div >
                                <label>Desde:</label> 
                                <input type ="date" name = "inicial" class="form-control">
                                <label>Hasta:</label>
                                <input type ="date" name = "final" class="form-control">      
                            </div>

                        </div>

                        <input type = "submit" name = "accion" value = "Ver ventas por cliente" class="btn btn-info btn-block"><br>
                        <br>
                        <input type = "submit" name = "accion" value = "Ver ventas por fecha" class="btn btn-info btn-block"><br>
                        <br>
                        <input type = "submit" name = "accion" value = "Ver ventas del mejor" class="btn btn-info btn-block"><br>
                        <br>
                        <a href="ReporteServlet?accion=listar   " class="btn btn-info btn-block">Ver todas</a><br>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-6" style="position: relative; height: 500px;  overflow: auto; display: block;">
                <%if (request.getSession().getAttribute("ventasReporte") != null) {
                        ArrayList<VentaRealizada> ventas = (ArrayList<VentaRealizada>) request.getSession().getAttribute("ventasReporte");
                        ArrayList<ArrayList<DetalleVenta>> detalles = (ArrayList<ArrayList<DetalleVenta>>) request.getSession().getAttribute("detallesReporte");
                        for (int i = 0; i < ventas.size(); i++) {
                            VentaRealizada ventaTemp = ventas.get(i);%>
                <table class="table table-success table-striped">
                    <thead>
                        <tr>
                            <th>Codigo de venta</th>
                            <th>Total</th>
                            <th>NIT</th>
                            <th>Cliente</th>
                            <th>Fecha</th>
                        </tr>

                    </thead>
                    <tbody>
                        <tr>    
                            <td><%=ventaTemp.getCodigoVenta()%></td>
                            <td><%=ventaTemp.getTotal()%></td>
                            <td><%=ventaTemp.getNIT()%></td>
                            <td><%=ventaTemp.getNombreCliente()%></td>
                            <td><%=ventaTemp.getFecha()%></td>
                        </tr>
                    </tbody>
                </table>
                <%for (int j = 0; j < detalles.get(i).size(); j++) {
                        DetalleVenta detalleTemp = detalles.get(i).get(j);%>

                <%if (j == 0) { %>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Codigo de Producto</th>
                            <th>Modelo</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                        </tr>
                    </thead> 
                    <%}
                    %>
                    <tbody>
                        <tr>    
                            <td><%=detalleTemp.getCodigoProducto()%></td>
                            <td><%=detalleTemp.getModelo()%></td>
                            <td><%=detalleTemp.getNombreProducto()%></td>
                            <td><%=detalleTemp.getPrecio()%></td>
                        </tr>
                    </tbody>
                    <%if (j == detalles.size() - 1) {%>
                    </table>
                        <%}%>
                        <%}
                                }
                            }
                        %>
                    
                        </div>  
        </div>
                        </div>

                        </body>
                        </html>
