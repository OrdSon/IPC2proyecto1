<%-- 
    Document   : listarVentasRealizadas
    Created on : 2/09/2021, 07:10:11 PM
    Author     : OrdSon
--%>

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
        <h1 class="text-center"> Nueva venta</h1>
        <div style="height: 50px"></div>

        <!--<a href="DiseñoServlet?accion=nuevo ">Añadir diseño</a> -->
        <div class="d-flex justify-content-center">
            <%
                Cliente clienteTemp = (Cliente) request.getSession().getAttribute("clienteVentaActiva");
                boolean busqueda = false;
                if (request.getSession().getAttribute("busqueda") != null) {
                    busqueda = (boolean) request.getSession().getAttribute("busqueda");
                }

                String codigoProducto = "";
                String nombreProducto = "";
                String nitCliente = "";

                String nombreCliente = "";

                if (clienteTemp != null) {
                    nombreCliente = clienteTemp.getNombre();
                    nitCliente = clienteTemp.getNit() + "";
                    request.getSession().setAttribute("codigoClienteActivo", clienteTemp.getCodigo());
                    System.out.println("CODIGO DE CLIENTE!!" + clienteTemp.getCodigo());

                }

            %>
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="VentaServlet">
                        <div>
                            <label>Buscar cliente</label>
                            <div >
                                <label>NIT:</label> 
                                <input type ="text" name = "txtNIT" class="form-control" value="<%=nitCliente%>">      
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

                    <input type = "submit" name = "accion" value = "Ver ventas" class="btn btn-success"><br>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-6" style="position: relative; height: 500px;  overflow: auto; display: block;">

                <%
                    if (request.getSession().getAttribute("ventasRealizadas")!=null) {
                %>
                <label class="h3">Ventas realizadas del cliente <%=nombreCliente%></label>
                <%} else { %>
                <label class="h3">Ventas realizadas</label>
                <%}%>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Codigo</th>
                            <th scope="col">Total</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Nit</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <%VentaDAO ventaDAO = new VentaDAO();
                        ArrayList<VentaRealizada> ventasRealizadas = ventaDAO.listarVentasRealizadas();
                        if (request.getSession().getAttribute("ventasRealizadas")!=null) {
                                ventasRealizadas = (ArrayList<VentaRealizada>)request.getSession().getAttribute("ventasRealizadas");
                            }
                        if (ventasRealizadas != null) {
                            for (int i = 0; i < ventasRealizadas.size(); i++) {
                                VentaRealizada ventaRealizada = ventasRealizadas.get(i);
                    %>

                    <tr>
                        <td scope="row"><%=ventaRealizada.getCodigoVenta()%></td>
                        <td scope="row"><%=ventaRealizada.getTotal()%></td>
                        <td scope="row"><%=ventaRealizada.getFecha()%></td>
                        <td scope="row"><%=ventaRealizada.getNIT()%></td>
                        <td scope="row"><%=ventaRealizada.getNombreCliente()%></td>
                        <td scope="row"><a href="VentaServlet?accion=ver&codigoVentaRealizada=<%=ventaRealizada.getCodigoVenta()%>" class="btn btn-outline-primary ">Ver detalles</a> </td>
                    </tr>

                    <% }

                        }
                    %>

                </table>
                    <a href="VentaServlet?accion=setNull" class="btn btn-success">Ver todas</a>
            </div>
        </div>

    </body>
</html>
